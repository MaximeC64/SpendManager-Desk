/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.GestionConnexion;
import Metier.Depense;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author pierrick.pabijan
 */
public class DepenseDAO {
private final Connection laConnection;

    public  DepenseDAO() {
        laConnection = GestionConnexion.getLaConnection();
    }
    
    public ArrayList<Depense> listDepense() throws SQLException{
        ArrayList<Depense> listDepense = new ArrayList<Depense>();
        Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT * FROM depense;";
        resultat = transmission.executeQuery(sql);
        while(resultat.next()){
            Depense d = new Depense(resultat.getInt("Id_Depense"),resultat.getString("DatePaiement_Depense"),resultat.getString("Libelle_Depense"),resultat.getString("Commentaire_Depense"), resultat.getFloat("MontantRemboursement_Depense"),resultat.getInt("Id_Notefrais"));
            listDepense.add(d);
        }
        return listDepense;
    }
    
    public float montantTotalNote(int id) throws SQLException{
        float total=0;
    Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT SUM(`MontantRemboursement_Depense`) as total FROM `depense` WHERE `Id_Notefrais`="+ id ;
        resultat = transmission.executeQuery(sql);
        while(resultat.next()){
        total= resultat.getFloat("total");
        }
        return total;
        
    }
    public void Commentaire(String commentaire, int id) throws SQLException{
        Statement transmission;
        transmission = laConnection.createStatement();
        String sql = "UPDATE `depense` SET `Commentaire_Depense`='"+commentaire+"' WHERE `Id_Depense`="+id+"";
        transmission.executeUpdate(sql);
    }
    
public int nbreDepenseByIdNotefrais(int id)throws SQLException{
        float quantite=0;
        Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT COUNT(`Id_Depense`)as quantite FROM `depense` WHERE `Id_Notefrais`="+id;
        resultat=transmission.executeQuery(sql);
    while(resultat.next()){
        quantite = resultat.getFloat("quantite");
        }
        return (int) quantite;
    }

public ArrayList<Depense> listDepenseByIdNote(int id) throws SQLException{
        ArrayList<Depense> listDepense = new ArrayList<Depense>();
        Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT * FROM depense WHERE Id_Notefrais = "+id+";";
        resultat = transmission.executeQuery(sql);
        while(resultat.next()){
            Depense d = new Depense(resultat.getInt("Id_Depense"),resultat.getString("DatePaiement_Depense"),resultat.getString("Libelle_Depense"),resultat.getString("Commentaire_Depense"), resultat.getFloat("MontantRemboursement_Depense"),resultat.getInt("Id_Notefrais"));
            listDepense.add(d);
        }
        return listDepense;
    }
}
