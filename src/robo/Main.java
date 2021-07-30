/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robo;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Qualidade
 */
public class Main {

    private File xml;
    private Document doc = null;
    private Element ele = null;
    private SAXBuilder builder = new SAXBuilder();

    private Element impo, icms, valor, linhaa, ide, total, cobr, linh, produ, ipi, ipiTrib;

    private List icmst, children, itens;

    private Nota nota = new Nota();

    public Main() {
        System.out.println("Iniciando o programa....");
        
    }

    public Nota getNota() {
        return nota;
    }

    
    
    public void iniciar(File xml) {
        this.xml= xml;
        try {
            doc = builder.build(xml);
            ele = (Element) doc.getRootElement();
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        List linhas = ele.getChildren();
        Iterator itera = linhas.iterator();
        Namespace name = Namespace.getNamespace("http://www.portalfiscal.inf.br/nfe");
        while (itera.hasNext()) {
            try {
                Element linha = (Element) itera.next();
                if (!linha.getName().equals("NFe")) {
                    if (linha.getName().equals("protNFe")) {
                        Element chave = linha.getChild("infProt", name);
                        String chaveAcesso = chave.getChildText("chNFe", name);
                        nota.setChave(chaveAcesso);
                    }
                    continue;
                }
                valor = linha.getChild("infNFe", name);
                ide = valor.getChild("ide", name);
                total = valor.getChild("total", name).getChild("ICMSTot", name);
                cobr = valor.getChild("cobr", name);
                nota.setSerie(ide.getChildText("serie", name));
                nota.setNumero(ide.getChildText("nNF", name));
                try{
                String[] dias = ide.getChildText("dEmi", name).split("-");
                nota.setData(dias[2] + dias[1] + dias[0]);
                }catch(Exception e){
                    
                }  
                try{
                nota.setValorTotal(total.getChildText("vNF", name));
                nota.setValorDesconto(total.getChildText("vDesc", name));
                nota.setValorItens(total.getChildText("vProd", name));
                nota.setValorBase(total.getChildText("vBCST", name));
                nota.setValorFrete(total.getChildText("vFrete", name));
                }catch(Exception e){
                    continue;
                }
                try{
                nota.setValorIcms(total.getChildText("vICMS", name));
                nota.setValorIpi(total.getChildText("vIPI", name));
                nota.setValorStr(total.getChildText("vST", name));
                }catch(Exception e){
                    continue;
                }
                try {
                    children = cobr.getChildren("dup", name);
                    Iterator ite = children.iterator();
                    while (ite.hasNext()) {
                        Element dup = (Element) ite.next();
                        nota.addDuplicata(new Duplicata(dup.getChildText("dVenc", name), dup.getChildText("vDup", name)));
                    }
                } catch (Exception e) {
                    System.out.println("Sem duplicadas");
                }
                itens = valor.getChildren("det", name);
                Iterator iterat = itens.iterator();

                while (iterat.hasNext()) {
                    linh = (Element) iterat.next();
                    Item item = new Item();
                    List atrib = linh.getAttributes();
                    Iterator atri = atrib.iterator();
                    while(atri.hasNext()){
                        Attribute a = (Attribute) atri.next();
                        item.setId(a.getValue());
                    }
                    
                    produ = linh.getChild("prod", name);
                    try {
                        //recupera a tag imposto
                        impo = linh.getChild("imposto", name);
                        //recupera a tag ICMS
                        icms = impo.getChild("ICMS", name);
                        //recupera a tag filha
                        icmst = icms.getChildren();

                        Iterator it = icmst.iterator();
                        while (it.hasNext()) {
                            linhaa = (Element) it.next();
                            if(linhaa.getChildText("CST", name) != null){
                                item.setCst(linhaa.getChildText("CST", name));
                            }else{
                                item.setCst(linhaa.getChildText("CSOSN", name));
                            }
                            item.setOrig(linhaa.getChildText("orig", name));
                            item.setMva(linhaa.getChildText("pMVAST", name));
                            item.setAliqIcms(linhaa.getChildText("pICMS", name));
                            item.setValorIcms(linhaa.getChildText("vICMS", name));
                            item.setAliqStr(linhaa.getChildText("pICMSST", name));
                            item.setValorSTR(linhaa.getChildText("vICMSST", name));
                        }

                    } catch (Exception e) {
                        System.out.println("Sem Imposto(ICMS/ICMSSTR).");
                        
                    }
                    try {
                        //recupera a tag ipi
                        ipi = impo.getChild("IPI", name);
                        ipiTrib = ipi.getChild("IPITrib", name);
                        item.setIpi(ipiTrib.getChildText("pIPI", name));
                        item.setValorIPI(ipiTrib.getChildText("vIPI",name));
                    } catch (Exception e) {
                        System.out.println("Sem IPI");
                        item.setIpi("0.0");
                    }
                    item.setCodigo(produ.getChildText("cProd", name));
                    item.setUnidade(produ.getChildText("uCom", name));
                    item.setQnt(produ.getChildText("qCom", name));
                    item.setValorTotal(produ.getChildText("vProd", name));
                    item.setCfop(produ.getChildText("CFOP", name));
                    item.carregar();
                    nota.addItem(item);
                }

            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

        }
        nota.digitar();
        Robo.getInstancia().setResultado(2);
    }
}
