/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robo;

import com.sun.glass.events.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Qualidade
 */
public class Nota {
    /*
     serie
     numero
     data
     valor total
     valor desconto
     valor total itens
     valor base str
     valor frete
     valor icms
     valor ipi
     valor str
     vencimento dupli
     valor dupli
     */

    private String serie, numero, data, valorTotal, valorDesconto, valorItens, valorBase, valorFrete, valorIcms, valorIpi, valorStr, chave;
    private ArrayList<Duplicata> duplicatas = new ArrayList<>();
    private ArrayList<Item> itens = new ArrayList<>();
    private Robot robot;
    private Scanner s;

    public Nota() {
        try {
            robot = new Robot();
            s = new Scanner(System.in);
        } catch (AWTException ex) {
            Logger.getLogger(Nota.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        System.out.println("Serie: " + serie);
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        System.out.println("Numero: " + numero);
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        System.out.println("Data: " + data);
        this.data = data;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        System.out.println("Valor Total: " + valorTotal);
        this.valorTotal = valorTotal.replace(".", ",");
    }

    public String getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(String valorDesconto) {
        System.out.println("Valor Desconto: " + valorDesconto);
        this.valorDesconto = valorDesconto.replace(".", ",");
    }

    public String getValorItens() {
        return valorItens;
    }

    public void setValorItens(String valorItens) {
        System.out.println("Valor Itens: " + valorItens);
        this.valorItens = valorItens.replace(".", ",");
    }

    public String getValorBase() {
        return valorBase;
    }

    public void setValorBase(String valorBase) {
        System.out.println("Valor Base Str: " + valorBase);
        this.valorBase = valorBase.replace(".", ",");
    }

    public String getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(String valorFrete) {
        System.out.println("Valor Frete: " + valorFrete);
        this.valorFrete = valorFrete.replace(".", ",");
    }

    public String getValorIcms() {
        return valorIcms;
    }

    public void setValorIcms(String valorIcms) {
        System.out.println("Valor Icms: " + valorIcms);
        this.valorIcms = valorIcms.replace(".", ",");
    }

    public String getValorIpi() {
        return valorIpi;
    }

    public void setValorIpi(String valorIpi) {
        System.out.println("Valor Ipi: " + valorIpi);
        this.valorIpi = valorIpi.replace(".", ",");
    }

    public String getValorStr() {
        return valorStr;
    }

    public void setValorStr(String valorStr) {
        System.out.println("Valor Str: " + valorStr);
        this.valorStr = valorStr.replace(".", ",");
    }

    public ArrayList<Duplicata> getDuplicatas() {
        return duplicatas;
    }

    public void setDuplicatas(ArrayList<Duplicata> duplicatas) {
        this.duplicatas = duplicatas;
    }

    public void addDuplicata(Duplicata arg0) {
        this.duplicatas.add(arg0);
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }

    public void addItem(Item arg0) {
        this.itens.add(arg0);
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        System.out.println("Chave de acesso: " + chave);
        this.chave = chave;
    }

    public int getQntItem(){
        return itens.size();
    }
    
    public void digitar() {
        System.out.println("Iniciando o programa em 5 segundos");
        robot.delay(1000);
        System.out.println("Iniciando o programa em 4 segundos");
        robot.delay(1000);
        System.out.println("Iniciando o programa em 3 segundos");
        robot.delay(1000);
        System.out.println("Iniciando o programa em 2 segundos");
        robot.delay(1000);
        System.out.println("Iniciando o programa em 1 segundos");
        robot.delay(1000);

        tab();

        //recupera o numero da serie da nfe
        digitar(serie);

        tab();

        //recupera o numero da nfe
        digitar(numero);

        tab();

        tab();

        tab();

        //recupera a data de emisao
        //imprime("Data: ", ide, "dEmi", name);
        digitar(data);

        tab();

        //imprime o dia atual
        Date dia = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        digitar(sdf.format(dia));

        tab();

        tab();

        //recupera o valor total da nfe
        digitar(valorTotal);

        for (int i = 0; i < 13; i++) {
            tab();
        }
        //recupera o valor do desconto vDesc
        digitar(valorDesconto);

        tab();

        tab();

        //recupera o valor total dos itens
        digitar(valorItens);

        tab();

        tab();

        //recupera o valor da base de calculo
        digitar(valorBase);

        tab();

        tab();

        //recupera o valor do frete
        digitar(valorFrete);

        System.out.println("Inclua a nota e clique em \"Valor Informado\" no Campo de ICMS");
        contagem();

//                for (int i = 0; i <= 37; i++) {
//                    tab();
//                }
        //recupera o valor do ICMS
        digitar(valorIcms);

        tab();

        tab();

        tab();

        //recupera o valor do IPI
        digitar(valorIpi);

        tab();

        tab();

        tab();

        //recupera o valor do STR
        digitar(valorStr);

        for (int i = 0; i <= 10; i++) {
            tab();
        }

        Iterator dup = duplicatas.iterator();

        while (dup.hasNext()) {
            Duplicata temp = (Duplicata) dup.next();
            temp.digitar();
        }

        robot.keyPress(KeyEvent.VK_ALT);
        robot.delay(1);
        robot.keyPress(KeyEvent.VK_9);
        robot.delay(500);
        robot.keyRelease(KeyEvent.VK_9);
        robot.delay(1);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.delay(1500);

        robot.keyPress(KeyEvent.VK_ALT);
        robot.delay(1);
        robot.keyPress(KeyEvent.VK_6);
        robot.delay(500);
        robot.keyRelease(KeyEvent.VK_6);
        robot.delay(1);
        robot.keyRelease(KeyEvent.VK_ALT);

        System.out.println("Aperte Enter para incluir itens...");
        sair(s.nextLine());

        Iterator ite = itens.iterator();

        while (ite.hasNext()) {
            Item temp = (Item) ite.next();
            temp.digitar();
        }

        System.out.println("*************Aguardando para incluir Chave de Acesso*************");
        contagem();
        digitar(chave);
    }

    private void contagem() {
        System.out.println("Aperte Enter.");
        sair(s.nextLine());
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
