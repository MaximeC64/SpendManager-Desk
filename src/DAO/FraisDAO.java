/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.GestionConnexion;
import Metier.Frais;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author pierrick.pabijan
 */
public class FraisDAO {
    
    private final Connection laConnection;

    public FraisDAO() {
        laConnection = GestionConnexion.getLaConnection();
    }
    
    public ArrayList<Frais> listFrais() throws SQLException{
        ArrayList<Frais> listfrais2 = new ArrayList<Frais>();
        Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT * FROM frais, depense WHERE frais.Id_Depense = depense.Id_Depense";
        resultat = transmission.executeQuery(sql);
        while(resultat.next()){
            Frais nf = new Frais(resultat.getString("Date_frais"),resultat.getInt("Id_Depense"),resultat.getString("DatePaiement_Depense"),resultat.getString("Libelle_Depense"),resultat.getString("Commentaire_Depense"),resultat.getFloat("MontantRemboursement_Depense"),resultat.getInt("Id_Notefrais"));
           
            listfrais2.add(nf);
        }
        return listfrais2;
    }
    
}
