/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor;

import java.awt.FileDialog;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author SamirAndres
 */
public class Metodos {

    public boolean EsMp3(String ruta) {
        String ext = "";
        boolean enc = false;
        int tam = ruta.length();
        for (int i = 0; i < tam; i++) {
            if (ruta.charAt(i) == '.') {
                enc = true;
                ext = "";
            } else if (enc) {
                ext += ruta.charAt(i);
            }
        }
        return "mp3".equalsIgnoreCase(ext);
    }

    public String FormatoReloj(long T_segundo) {
        long Segundo = T_segundo % 60;
        long Minuto = T_segundo / 60;
        String TSeg = "" + Segundo;
        String TMin = "" + Minuto;
        if (Segundo < 10) {
            TSeg = "0" + Segundo;
        }
        if (Minuto < 10) {
            TMin = "0" + Minuto;
        }
        return TMin + ":" + TSeg;
    }

    public static void CrearCarpeta() {
        File Folder = new File(Ruta());
        if (!Folder.exists()) {
            Folder.mkdir();
        }
    }

    public static String Ruta() {
        return System.getProperty("user.home") + "/MusicMp3";
    }
}
