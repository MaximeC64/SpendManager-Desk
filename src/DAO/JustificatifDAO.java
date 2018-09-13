/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.GestionConnexion;
import Metier.Justificatif;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author utilisateur
 */
public class JustificatifDAO {
    private final Connection laConnection;

    public JustificatifDAO() {
        laConnection = GestionConnexion.getLaConnection();
    }
    
    public ArrayList<Justificatif> listJustificatif() throws SQLException{
        ArrayList<Justificatif> listJustificatifs = new ArrayList<Justificatif>();
        Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT * FROM justificatif;";
        resultat = transmission.executeQuery(sql);
        while(resultat.next()){
            Justificatif j = new Justificatif(resultat.getInt("Id_Justificatif"), resultat.getString("Titre_Justificatif"), resultat.getString("Url_Justificatif"),resultat.getInt("Id_Depense"),resultat.getInt("Id_Notefrais"));
            listJustificatifs.add(j);
        }
        return listJustificatifs;
    }
    public Justificatif readOneJustificatif(int id) throws SQLException{
        Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT * FROM justificatif WHERE id = "+id+";";
        resultat = transmission.executeQuery(sql);
        resultat.next();
        Justificatif j = new Justificatif(resultat.getInt("Id_Justificatif"), resultat.getString("Titre_Justificatif"), resultat.getString("Url_Justificatif"),resultat.getInt("Id_Depense"),resultat.getInt("Id_Notefrais"));
        return j;
    }
}
