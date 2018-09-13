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
public class Tableau {
    String salarie;
    String client;
    String date;
    String total;
    String Statut;

    public Tableau(String salarie, String client, String date, String total, String Statut) {
        this.salarie = salarie;
        this.client = client;
        this.date = date;
        this.total = total;
        this.Statut = Statut;
    }


    public String getSalarie() {
        return salarie;
    }

    public void setSalarie(String salarie) {
        this.salarie = salarie;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
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
    
}
