/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.GestionConnexion;
import Metier.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author utilisateur
 */
public class UtilisateurDAO {
    private final Connection laConnection;

    public UtilisateurDAO() {
        laConnection = GestionConnexion.getLaConnection();
    }
    
    public ArrayList<Utilisateur> listUtilisateur() throws SQLException{
        ArrayList<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();
        Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT * FROM utilisateur;";
        resultat = transmission.executeQuery(sql);
        while(resultat.next()){
            Utilisateur u = new Utilisateur(resultat.getInt("Id_Utilisateur"), resultat.getString("Mail_Utilisateur"), resultat.getString("Mdp_Utilisateur"), resultat.getString("Adresse_Utilisateur"), resultat.getString("Cp_Utilisateur"), resultat.getString("Ville_Utilisateur"), resultat.getString("Telephone_Utilisateur"), resultat.getString("Nom_Utilisateur"), resultat.getString("Prenom_Utilisateur"), resultat.getString("Statut_Utilisateur"));
            listUtilisateurs.add(u);
        }
        return listUtilisateurs;
    }
    public Utilisateur readOneUtilisateur(int id) throws SQLException{
        Utilisateur oneUtilisateur = null;
        Statement transmission;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT * FROM utilisateur WHERE Id_Utilisateur = "+id+";";
        resultat = transmission.executeQuery(sql);
        while(resultat.next()){
            oneUtilisateur = new Utilisateur(resultat.getInt("Id_Utilisateur"), resultat.getString("Mail_Utilisateur"), resultat.getString("Mdp_Utilisateur"), resultat.getString("Adresse_Utilisateur"), resultat.getString("Cp_Utilisateur"), resultat.getString("Ville_Utilisateur"), resultat.getString("Telephone_Utilisateur"), resultat.getString("Nom_Utilisateur"), resultat.getString("Prenom_Utilisateur"), resultat.getString("Statut_Utilisateur"));
        }
        return oneUtilisateur;
    }
    
    
    
    public void addCommercial(Utilisateur u) throws SQLException{
        Statement transmission;
        transmission = laConnection.createStatement();
        String sql = "INSERT INTO utilisateur (Mail_Utilisateur, Mdp_Utilisateur, Adresse_Utilisateur, Cp_Utilisateur, Ville_Utilisateur, Telephone_Utilisateur, Nom_Utilisateur, Prenom_Utilisateur, Statut_Utilisateur) VALUES ('"+u.getMail_Utilisateur()+"','"+u.getMdp_Utilisateur()+"',"+u.getAdresse_Utilisateur()+",'"+u.getCp_Utilisateur()+"','"+u.getVille_Utilisateur()+"','"+u.getTelephone_Utilisateur()+"','"+u.getNom_Utilisateur()+"','"+u.getPrenom_Utilisateur()+"','"+u.getStatut_Utilisateur()+");";
        
        int res = transmission.executeUpdate(sql);
        
        if(res==1){
            System.out.println("Insertion OK");
        } else {
            System.out.println("Echec de l'insertion");
        }
    }
    public boolean Connecter(String Mail, String Mdp) throws SQLException {
        Utilisateur oneUtilisateur = null;
        Statement transmission;
        boolean reponse = false;
        transmission = laConnection.createStatement();
        ResultSet resultat;
        String sql = "SELECT * FROM utilisateur WHERE Mail_Utilisateur = '" + Mail + "' AND Mdp_Utilisateur='" + Mdp + "' AND `Statut_Utilisateur`='Comptable';";
        resultat = transmission.executeQuery(sql);
        if (resultat.next()) {
            oneUtilisateur = new Utilisateur(resultat.getInt("Id_Utilisateur"), resultat.getString("Mail_Utilisateur"), resultat.getString("Mdp_Utilisateur"), resultat.getString("Adresse_Utilisateur"), resultat.getString("Cp_Utilisateur"), resultat.getString("Ville_Utilisateur"), resultat.getString("Telephone_Utilisateur"), resultat.getString("Nom_Utilisateur"), resultat.getString("Prenom_Utilisateur"), resultat.getString("Statut_Utilisateur"));
            reponse = true;
        }
        return reponse;

    }
    public void addUtilisateur(Utilisateur u) throws SQLException {
        Statement transmission;
        transmission = laConnection.createStatement();
        String sql = "INSERT INTO utilisateur(Mail_Utilisateur,Mdp_Utilisateur,Adresse_Utilisateur,Cp_Utilisateur, Ville_Utilisateur,Telephone_Utilisateur, Nom_Utilisateur,Prenom_Utilisateur, Statut_Utilisateur) values ('" + u.getMail_Utilisateur() + "','" + u.getMdp_Utilisateur() + "','" + u.getAdresse_Utilisateur() + "','" + u.getCp_Utilisateur() + "','" + u.getVille_Utilisateur() + "','" + u.getTelephone_Utilisateur() + "','" + u.getNom_Utilisateur() + "','" + u.getPrenom_Utilisateur() + "','" + u.getStatut_Utilisateur() + "')";

        transmission.executeUpdate(sql);

    }
}
