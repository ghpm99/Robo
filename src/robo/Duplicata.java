/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robo;

import com.sun.glass.events.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;

/**
 *
 * @author Qualidade
 */
public class Duplicata {

    private String vencimento, valor;
    private Robot robot;

    public Duplicata(String vencimento, String valor) {
        try {
            String[] vencs = vencimento.split("-");
            System.out.println("Vencimento: " + vencs[2] + vencs[1] + vencs[0] + " Valor :" + valor);
            this.vencimento = vencs[2] + vencs[1] + vencs[0];
            this.valor = valor.replace(".", ",");
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public String getVencimento() {
        return vencimento;
    }

    public String getValor() {
        return valor;
    }

    public void digitar() {
        digitar(vencimento);
        tab();
        digitar(valor);
        tab();
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
