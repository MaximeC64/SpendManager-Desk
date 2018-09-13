/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.GestionConnexion;
import Metier.NoteFrais;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author utilisateur
 */
public class NoteFraisDAO {
    private final Connection laConnection;

    public NoteFraisDAO() {
        laConnection = GestionConnexion.getLaConnection();
    }
    
    public ArrayList<NoteFrais> listNoteFrais() throws SQLException{
        ArrayList<NoteFrais> listNotefrais = new ArrayList<NoteFrais>();
        Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT * FROM notefrais;";
        resultat = transmission.executeQuery(sql);
        while(resultat.next()){
            NoteFrais nf = new NoteFrais(resultat.getInt("Id_Notefrais"), resultat.getString("Date_Notefrais"), resultat.getString("DateSoumission_Notefrais"), resultat.getInt("Id_Utilisateur"), resultat.getInt("Id_Client"));
            listNotefrais.add(nf);
        }
        return listNotefrais;
    }
    public ArrayList<NoteFrais> listNoteFraisById(int id) throws SQLException{
        ArrayList<NoteFrais> listNotefrais = new ArrayList<NoteFrais>();
        Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT * FROM notefrais WHERE Id_Utilisateur = "+id+";";
        resultat = transmission.executeQuery(sql);
        while(resultat.next()){
            NoteFrais nf = new NoteFrais(resultat.getInt("Id_Notefrais"), resultat.getString("Date_Notefrais"), resultat.getString("DateSoumission_Notefrais"), resultat.getInt("Id_Utilisateur"), resultat.getInt("Id_Client"));
            listNotefrais.add(nf);
        }
        return listNotefrais;
    }
    
    public int totalNoteFraisByIdnote(int id) throws SQLException{
        int total=0;
        Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT COUNT(`Id_Depense`) as total FROM depense WHERE Id_Notefrais=(SELECT `Id_Notefrais` FROM `notefrais` WHERE `Id_Utilisateur`="+id+");";
        resultat = transmission.executeQuery(sql);
        while(resultat.next()){
            total = resultat.getInt("total");
        }
        return total;
    }
    
    public void updateNoteFrais(NoteFrais nf) throws SQLException{
        Statement transmission;
        transmission = laConnection.createStatement();
        String sql = "UPDATE notefrais SET Date_Notefrais = '"+nf.getDate_Notefrais()+"', DateSoumission_Notefrais = '"+nf.getDateSoumission_Notefrais()+"', Id_Utilisateur = '"+nf.getId_Utilisateur()+"', Id_Client = '"+nf.getId_Client()+"' WHERE Notefrais.Id_Notefrais = '"+nf.getId_Notefrais()+"';";
        
        int res = transmission.executeUpdate(sql);
        
        if(res==1){
            System.out.println("Modification OK");
        } else {
            System.out.println("Echec de la modification");
        }
    }
    public ArrayList<NoteFrais> listNoteFraisMois(int mois, int annee) throws SQLException{
        ArrayList<NoteFrais> listNotefrais = new ArrayList<NoteFrais>();
        Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT * FROM notefrais WHERE (SELECT MONTH( Date_Notefrais ))="+ mois +"  and (SELECT YEAR( Date_Notefrais ))="+ annee +";";
        resultat = transmission.executeQuery(sql);
        while(resultat.next()){
            NoteFrais nf = new NoteFrais(resultat.getInt("Id_Notefrais"), resultat.getString("Date_Notefrais"), resultat.getString("DateSoumission_Notefrais"), resultat.getInt("Id_Utilisateur"), resultat.getInt("Id_Client"));
            listNotefrais.add(nf);
        }
        return listNotefrais;
    }
    
}

