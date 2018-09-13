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
public class Client {	
protected int Id_Client;
protected String Nom_Client;
protected String Prenom_Client;

    public int getId_Client() {
        return Id_Client;
    }

    public void setId_Client(int Id_Client) {
        this.Id_Client = Id_Client;
    }

    public String getNom_Client() {
        return Nom_Client;
    }

    public void setNom_Client(String Nom_Client) {
        this.Nom_Client = Nom_Client;
    }

    public String getPrenom_Client() {
        return Prenom_Client;
    }

    public void setPrenom_Client(String Prenom_Client) {
        this.Prenom_Client = Prenom_Client;
    }

    public Client(int Id_Client, String Nom_Client, String Prenom_Client) {
        this.Id_Client = Id_Client;
        this.Nom_Client = Nom_Client;
        this.Prenom_Client = Prenom_Client;
    }
}
