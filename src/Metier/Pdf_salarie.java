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
public class Pdf_salarie {
    String nom;
    String date;
    String total;
    String Statut;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatut() {
        return Statut;
    }

    public void setStatut(String Statut) {
        this.Statut = Statut;
    }

    public Pdf_salarie(String nom, String date, String total, String Statut) {
        this.nom = nom;
        this.date = date;
        this.total = total;
        this.Statut = Statut;
    }
}
