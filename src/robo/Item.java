
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robo;

import com.sun.glass.events.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Qualidade
 */
public class Item {
    /*
     codigo
     cfop
     unidade
     quantidade
     valor total
     cst
     aliq icms
     mva
     aliq str
     ipi
     */

    private String codigo, cfop, unidade, qnt, valorTotal, orig, cst, aliqIcms, mva, aliqStr, ipi, id,valorIcms,valorIPI,valorSTR;

    private Robot robot;

    private Scanner s;

    public Item() {
        try {
            this.robot = new Robot();
            s = new Scanner(System.in);
        } catch (AWTException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getCodigo() {
        return Robo.getInstancia().codigos().getProperty(id);
    }

    public void setCodigo(String codigo) {
        System.out.println("Codigo: " + codigo);
        this.codigo = codigo;
    }

    public String getCfop() {
        return cfop;
    }

    private String getFator(){
        return Robo.getInstancia().getFator().getProperty(getUnidade());
    }
    
    public void setCfop(String cfop) {
        if (cfop.startsWith("5")) {
            if (cfop.endsWith("102") || cfop.endsWith("101")) {
                this.cfop = "1102";
            } else {
                this.cfop = "1403";
            }
        } else if (cfop.startsWith("6")) {
            if (cfop.endsWith("102") || cfop.endsWith("101")) {
                this.cfop = "2102";
            } else {
                this.cfop = "2403";
            }
        }

    }

    public String getUnidade() {
        if (Robo.getInstancia().isConverte()) {
            return Robo.getInstancia().getConv().getProperty(unidade);
        } else {
            return unidade;
        }
    }

    public void setUnidade(String unidade) {
        System.out.println("Unidade: " + unidade);
        this.unidade = unidade;
    }

    public String getQnt() {
        return qnt;
    }

    public void setQnt(String qnt) {
        System.out.println("Quantidade: " + qnt);
        this.qnt = qnt.replace(".", ",");
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        System.out.println("Valor Total: " + valorTotal);
        this.valorTotal = valorTotal.replace(".", ",");
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        if (cst.length() == 2) {
            this.cst = cst;
        } else {
            System.out.println(cst);
            if (cst.startsWith("20")) {
                this.cst = "10";
            } else {
                this.cst = "00";
            }
        }

    }

    public String getOrig() {
        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public String getAliqIcms() {
        return aliqIcms;
    }

    public void setAliqIcms(String aliqIcms) {
        System.out.println("Aliq. Icms: " + aliqIcms);
        if (aliqIcms != null) {
            this.aliqIcms = aliqIcms.replace(".", ",");
        } else {
            this.aliqIcms = "0,0";
        }
    }

    public String getMva() {
        return mva;
    }

    public void setMva(String mva) {
        System.out.println("MVA: " + mva);
        if (mva != null) {
            this.mva = mva.replace(".", ",");
        } else {
            this.mva = "0,0";
        }
    }

    public String getAliqStr() {
        return aliqStr;
    }

    public void setAliqStr(String aliqStr) {
        System.out.println("Aliq. Str: " + aliqStr);
        if (aliqStr != null) {
            this.aliqStr = aliqStr.replace(".", ",");
        } else {
            this.aliqStr = "0,0";
        }
    }

    public String getIpi() {
        return ipi;
    }

    public void setIpi(String ipi) {
        System.out.println("Aliq. IPI: " + ipi);
        if (ipi != null) {
            this.ipi = ipi;
        } else {
            this.ipi = "0.0";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        System.out.println("Id: " + id);
        this.id = id;
    }

    public void carregar(){
        codigo = getCodigo();
        unidade = getUnidade();
    }

    public String getValorIcms() {
        return valorIcms;
    }

    public void setValorIcms(String valorIcms) {
         if (valorIcms != null) {
            this.valorIcms = valorIcms.replace(".", ",");
        } else {
            this.valorIcms = "0,0";
        }
        
    }

    public String getValorIPI() {
        return valorIPI;
    }

    public void setValorIPI(String valorIPI) {
        if (valorIPI != null) {
            this.valorIPI = valorIPI.replace(".", ",");
        } else {
            this.valorIPI = "0,0";
        }
    }

    public String getValorSTR() {
        return valorSTR;
    }

    public void setValorSTR(String valorSTR) {
        if (valorSTR != null) {
            this.valorSTR = valorSTR.replace(".", ",");
        } else {
            this.valorSTR = "0,0";
        }
    }
    
    
    
    public void digitar() {
        System.out.println("Item " + id + "/" + Robo.getInstancia().getMain().getNota().getQntItem());
        System.out.println("Continuando em 5");
        robot.delay(1000);
        System.out.println("Continuando em 4");
        robot.delay(1000);
        System.out.println("Continuando em 3");
        robot.delay(1000);
        System.out.println("Continuando em 2");
        robot.delay(1000);
        System.out.println("Continuando em 1");
        robot.delay(1000);

        digitar(codigo);
        tab();
        tab();
        tab();
        digitar(orig);
        digitar(cst);

        tab();
        
        digitar(unidade);
        
        tab();
        
        digitar(qnt);
        tab();
        tab();
        digitar(valorTotal);
        tab();
        digitar(cfop);
        tab();
        digitar(mva);

        for (int i = 0; i < 7; i++) {
            tab();
        }
        //IMPLEMENTAR
        digitar(getFator());
         for (int i = 0; i < 4; i++) {
            tab();
        }
        
        digitar("ICMS");
        for (int i = 0; i < 4; i++) {
            tab();

        }
        digitar(aliqIcms);
        tab();
        digitar(valorIcms);
        for (int i = 0; i < 3; i++) {
            tab();

        }
        digitar("IPI");
        for (int i = 0; i < 4; i++) {
            tab();
        }
        digitar(ipi.replace(".", ","));
        tab();
        digitar(valorIPI);       
        for (int i = 0; i < 3; i++) {
            tab();

        }
        digitar("STR");
        tab();
        float strr = Float.valueOf(ipi);
        strr += 100.00f;
        digitar(String.valueOf(strr).replace(".", ","));
        for (int i = 0; i < 3; i++) {
            tab();
        }
        digitar(aliqStr);
        tab();
        digitar(valorSTR);
        for(int i = 0; i < 3; i++){
            tab();
        }
        digitar(" ");
        for(int i = 0; i < 8; i++){
            tab();
        }
        digitar(" ");
        for(int i = 0; i < 8; i++){
            tab();
        }
        digitar(" ");
        robot.keyPress(KeyEvent.VK_ALT);
        robot.delay(1);
        robot.keyPress(KeyEvent.VK_0);
        robot.delay(500);
        robot.keyRelease(KeyEvent.VK_0);
        robot.delay(1);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.delay(500);

        System.out.println("Inclua item e aperte Enter...");
        sair(s.nextLine());

    }

    private void digitar(String s) {
        if (s == null) {
            return;
        }
        char[] cha = s.toCharArray();
        for (char c : cha) {
            try {
                robot.keyPress((int) c);
                robot.delay(1);
                robot.keyRelease((int) c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sair(String arg0) {
        if (arg0.equals("-exit")) {
            Robo.getInstancia().sair();
        }
    }

    private void tab() {
        try {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.delay(1);
            robot.keyRelease(KeyEvent.VK_TAB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

}
