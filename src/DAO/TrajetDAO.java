/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.GestionConnexion;
import Metier.Trajet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author pierrick.pabijan
 */
public class TrajetDAO {
    private final Connection laConnection;

    public  TrajetDAO() {
        laConnection = GestionConnexion.getLaConnection();
    }
    
    public ArrayList<Trajet> listTrajet() throws SQLException{
        ArrayList<Trajet> listTrajets = new ArrayList<Trajet>();
        Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT * FROM `trajet`,`depense` WHERE `trajet`.`Id_Depense`=`depense`.`Id_Depense`";

        resultat = transmission.executeQuery(sql);
        while(resultat.next()){
            Trajet t = new Trajet(resultat.getString("Duree_Trajet"),resultat.getString("VilleDepart_Trajet"),resultat.getString("VilleArrivee_Trajet"),resultat.getString("DateAller_Trajet"),resultat.getString("DateRetour_Trajet"),resultat.getFloat("Kilometre_Trajet"),resultat.getInt("Id_Depense"),resultat.getString("DatePaiement_Depense"),resultat.getString("Libelle_Depense"),resultat.getString("Commentaire_Depense"),resultat.getFloat("MontantRemboursement_Depense"),resultat.getInt("Id_Notefrais"));
            listTrajets.add(t);
        }
        return listTrajets;
    }
    
}