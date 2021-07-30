/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Qualidade
 */
public class Teste {

    public static void main(String[] args) {
        try {
            Properties pro = new Properties();
            pro.setProperty("%O", "1");
            pro.store(new FileOutputStream(new File("Teste.txt")), " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
