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
public class Validation {
    private String Etat_Validation;
    private String Date_Validation;
    private int Id_Utilisateur;
    private int Id_Depense;
    private int Id_Notefrais;
//    private Utilisateur unUtilisateur;
//    private Depense uneDepense; 
//    private NoteFrais uneNotefrais;

    public Validation(String Etat_Validation, String Date_Validation, int Id_Utilisateur, int Id_Depense, int Id_Notefrais) {
        this.Etat_Validation = Etat_Validation;
        this.Date_Validation = Date_Validation;
        this.Id_Utilisateur = Id_Utilisateur;
        this.Id_Depense = Id_Depense;
        this.Id_Notefrais = Id_Notefrais;
    }

    public String getEtat_Validation() {
        return Etat_Validation;
    }
    public void setEtat_Validation(String Etat_Validation) {
        this.Etat_Validation = Etat_Validation;
    }
    public String getDate_Validation() {
        return Date_Validation;
    }
    public void setDate_Validation(String Date_Validation) {
        this.Date_Validation = Date_Validation;
    }
    public int getId_Utilisateur() {
        return Id_Utilisateur;
    }
    public void setId_Utilisateur(int Id_Utilisateur) {
        this.Id_Utilisateur = Id_Utilisateur;
    }
    public int getId_Depense() {
        return Id_Depense;
    }
    public void setId_Depense(int Id_Depense) {
        this.Id_Depense = Id_Depense;
    }
    public int getId_Notefrais() {
        return Id_Notefrais;
    }
    public void setId_Notefrais(int Id_Notefrais) {
        this.Id_Notefrais = Id_Notefrais;
    }
}