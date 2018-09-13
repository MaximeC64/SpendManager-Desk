/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

/**
 *
 * @author pierrick.pabijan
 */
public class Pdf_note {
    private String date;
    private static String out;

    public Pdf_note(String date) {
        this.date = date;
        this.out="NoteMensuelle" + date + ".pdf";
    }

    public static String getOut() {
        return out;
    }

    public String getDate() {
        return date;
    }
    
}
