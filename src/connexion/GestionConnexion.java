/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pierrick.pabijan
 */

public class GestionConnexion {

    private static final String url = "jdbc:mysql://mysql-moralesmarie.alwaysdata.net/moralesmarie_spendmanager";
    private static final String login = "157880";
    private static final String mdp = "test";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static Connection laConnection = null;

    public GestionConnexion() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("Problème de driver" + ex.getMessage());
        }

        try {
            laConnection = DriverManager.getConnection(url, login, mdp);
        } catch (SQLException ex) {
            System.out.println("Problème de BDD " + ex.getMessage());
        }
    }

    public static Connection getLaConnection() {
        if (laConnection == null) {
            new GestionConnexion();
        }
        return laConnection;
    }
    
    public static void liberer() throws SQLException {
        laConnection.close();
        laConnection = null;
    }
}
