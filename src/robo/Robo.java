/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author Qualidade
 */
public class Robo {

    private File config = new File("config.ini"), xml = null, codigos = null;
    private Properties pro = new Properties(), cod = new Properties(), conv = new Properties(),fator = new Properties();
    private Scanner s = new Scanner(System.in);
    private int resultado = 0, itens;
    private String[] comando;
    private static Robo instancia;
    private boolean simples, converte, interno;
    private Main main = new Main();

    public boolean isInterno() {
        return interno;
    }

    public static Robo getInstancia() {
        if (instancia == null) {
            instancia = new Robo();
        }
        return instancia;
    }

    public Main getMain() {
        return main;
    }
    
    public String[] getComando() {
        return comando;
    }

    public void setComando(String[] comando) {
        if (comando.length == 0) {
            comando = new String[]{"-h"};
        }
        this.comando = comando;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here          
        getInstancia().setComando(args);
        getInstancia().carregar();
        getInstancia().iniciar();
        getInstancia().sair();
    }

    public void ajuda() {
        System.out.println("Comandos...\n-all  \n-xml [caminho] \n-config [caminho] \n-clear \n-renew \n-pause \n-start \n-exit");
        setComando(s.nextLine().split(" "));
        iniciar();
    }

    private void all() {
        System.out.println("Caminho=" + xml.getAbsolutePath()
                + "\nTotal Itens=" + itens
                + "\nSimples=" + simples
                + "\nConv. Unidades=" + converte
                + "\nCaminho codigos=" + codigos.getAbsolutePath()
                + "\nCFOP interno=" + interno);
        setComando(s.nextLine().split(" "));
        iniciar();
    }

    private void xml() {
        xml = new File(getComando()[1]);
        setComando(s.nextLine().split(" "));
        iniciar();
    }

    private void config() {

    }

    private void clear() {

    }

    private void renew() {
        carregar();
        setComando(s.nextLine().split(" "));
        iniciar();
    }

    private void pause() {

    }

    private void start() {
        main.iniciar(xml);

    }

    private void carregar() {
        try {
            pro.load(new FileInputStream(config));
            xml = new File(pro.getProperty("Caminho"));
            codigos = new File(pro.getProperty("Caminho codigos"));
            itens = Integer.valueOf(pro.getProperty("Total Itens"));
            simples = Boolean.valueOf(pro.getProperty("Simples?"));
            converte = Boolean.valueOf(pro.getProperty("Conv. Unidades?"));
            interno = Boolean.valueOf(pro.getProperty("interno"));
            cod.load(new FileInputStream(codigos));
            if (converte) {
                conv.load(new FileInputStream(new File("conv.conv")));
                fator.load(new FileInputStream(new File("fator.conv")));
            }
        } catch (IOException e) {
            e.printStackTrace();
            resultado = 1;
            sair();
        }
    }

    private void iniciar() {
        resultado = 0;
        switch (getComando()[0]) {
            case "-h":
                System.out.println("ajuda");
                ajuda();
                break;
            case "-all":
                all();
                break;
            case "-xml":
                xml();
                break;
            case "-config":
                config();
                break;
            case "-clear":
                clear();
                break;
            case "-renew":
                renew();
                break;
            case "-pause":
                pause();
                break;
            case "-start":
                start();
                break;
            case "-exit":
                resultado = 2;
                sair();
                break;
        }

    }

    protected void sair() {
        switch (resultado) {
            case 0:
                System.out.println("Fechamento inesperado...");
                System.exit(1);
                break;
            case 1:
                System.out.println("Erro....");
                System.exit(1);
                break;
            case 2:
                System.out.println("saindo....");
                System.exit(0);
                break;
        }
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public Properties codigos() {
        return cod;
    }

    public Properties getConv() {
        return conv;
    }

    public boolean isConverte() {
        return converte;
    }

    public Properties getFator() {
        return fator;
    }

    
    
}
