/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

/**
 *
 * @author utilisateur
 */
public class Utilisateur {
    private int Id_Utilisateur;
    private String Mail_Utilisateur; 
    private String Mdp_Utilisateur; 
    private String Adresse_Utilisateur; 
    private String Cp_Utilisateur;
    private String Ville_Utilisateur; 
    private String Telephone_Utilisateur; 
    private String Nom_Utilisateur;
    private String Prenom_Utilisateur;
    private String Statut_Utilisateur;

    public Utilisateur(int Id_Utilisateur, String Mail_Utilisateur, String Mdp_Utilisateur, String Adresse_Utilisateur, String Cp_Utilisateur, String Ville_Utilisateur, String Telephone_Utilisateur, String Nom_Utilisateur, String Prenom_Utilisateur, String Statut_Utilisateur) {
        this.Id_Utilisateur = Id_Utilisateur;
        this.Mail_Utilisateur = Mail_Utilisateur;
        this.Mdp_Utilisateur = Mdp_Utilisateur;
        this.Adresse_Utilisateur = Adresse_Utilisateur;
        this.Cp_Utilisateur = Cp_Utilisateur;
        this.Ville_Utilisateur = Ville_Utilisateur;
        this.Telephone_Utilisateur = Telephone_Utilisateur;
        this.Nom_Utilisateur = Nom_Utilisateur;
        this.Prenom_Utilisateur = Prenom_Utilisateur;
        this.Statut_Utilisateur = Statut_Utilisateur;
    }
    public Utilisateur(String Mail_Utilisateur, String Mdp_Utilisateur, String Adresse_Utilisateur, String Cp_Utilisateur, String Ville_Utilisateur, String Telephone_Utilisateur, String Nom_Utilisateur, String Prenom_Utilisateur, String Statut_Utilisateur) {
      
        this.Mail_Utilisateur = Mail_Utilisateur;
        this.Mdp_Utilisateur = Mdp_Utilisateur;
        this.Adresse_Utilisateur = Adresse_Utilisateur;
        this.Cp_Utilisateur = Cp_Utilisateur;
        this.Ville_Utilisateur = Ville_Utilisateur;
        this.Telephone_Utilisateur = Telephone_Utilisateur;
        this.Nom_Utilisateur = Nom_Utilisateur;
        this.Prenom_Utilisateur = Prenom_Utilisateur;
        this.Statut_Utilisateur = Statut_Utilisateur;
    }

    public int getId_Utilisateur() {
        return Id_Utilisateur;
    }
    public void setId_Utilisateur(int Id_Utilisateur) {
        this.Id_Utilisateur = Id_Utilisateur;
    }
    public String getMail_Utilisateur() {
        return Mail_Utilisateur;
    }
    public void setMail_Utilisateur(String Mail_Utilisateur) {
        this.Mail_Utilisateur = Mail_Utilisateur;
    }
    public String getMdp_Utilisateur() {
        return Mdp_Utilisateur;
    }
    public void setMdp_Utilisateur(String Mdp_Utilisateur) {
        this.Mdp_Utilisateur = Mdp_Utilisateur;
    }
    public String getAdresse_Utilisateur() {
        return Adresse_Utilisateur;
    }
    public void setAdresse_Utilisateur(String Adresse_Utilisateur) {
        this.Adresse_Utilisateur = Adresse_Utilisateur;
    }
    public String getCp_Utilisateur() {
        return Cp_Utilisateur;
    }
    public void setCp_Utilisateur(String Cp_Utilisateur) {
        this.Cp_Utilisateur = Cp_Utilisateur;
    }
    public String getVille_Utilisateur() {
        return Ville_Utilisateur;
    }
    public void setVille_Utilisateur(String Ville_Utilisateur) {
        this.Ville_Utilisateur = Ville_Utilisateur;
    }
    public String getTelephone_Utilisateur() {
        return Telephone_Utilisateur;
    }
    public void setTelephone_Utilisateur(String Telephone_Utilisateur) {
        this.Telephone_Utilisateur = Telephone_Utilisateur;
    }
    public String getNom_Utilisateur() {
        return Nom_Utilisateur;
    }
    public void setNom_Utilisateur(String Nom_Utilisateur) {
        this.Nom_Utilisateur = Nom_Utilisateur;
    }
    public String getPrenom_Utilisateur() {
        return Prenom_Utilisateur;
    }
    public void setPrenom_Utilisateur(String Prenom_Utilisateur) {
        this.Prenom_Utilisateur = Prenom_Utilisateur;
    }
    public String getStatut_Utilisateur() {
        return Statut_Utilisateur;
    }
    public void setStatut_Utilisateur(String Statut_Utilisateur) {
        this.Statut_Utilisateur = Statut_Utilisateur;
    }

    

    
}
