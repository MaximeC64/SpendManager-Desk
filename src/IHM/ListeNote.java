/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import DAO.ClientDAO;
import DAO.DepenseDAO;
import DAO.NoteFraisDAO;
import DAO.UtilisateurDAO;
import DAO.ValidationDAO;
import Metier.Client;
import Metier.Depense;
import Metier.NoteFrais;
import Metier.Tableau;
import Metier.Utilisateur;
import Metier.Validation;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pierrick.pabijan
 */
public class ListeNote extends javax.swing.JFrame {

    static Locale locale = Locale.getDefault();
    static Date actuelle = new Date();
    static DateFormat dateFormat = new SimpleDateFormat("MM");
    static DateFormat dateFormat2 = new SimpleDateFormat("yyyy");
    ArrayList a2 = new ArrayList();
    int nombre = 0;
    int nombre2 = 0;
    

    public static String Mois() {
        String dat = dateFormat.format(actuelle);
        return dat;
        
    }

    public static String annee() {
        String dat = dateFormat2.format(actuelle);
        return dat;
    }
    int mois = Integer.parseInt(Mois());
    int annee = Integer.parseInt(annee());
    String Mois;
    int Annee;

    public ListeNote() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icone.png"));
       getContentPane().setBackground(new Color(13, 110, 189));
        this.setTitle("Liste mensuelle note de frais");
        
        if (mois == 1) {
            Mois = "Janvier";
        } else if (mois == 2) {
            Mois = "Fevrier";
        } else if (mois == 3) {
            Mois = "Mars";
        } else if (mois == 4) {
            Mois = "Avril";
        } else if (mois == 5) {
            Mois = "Mai";
        } else if (mois == 6) {
            Mois = "Juin";
        } else if (mois == 7) {
            Mois = "Juillet";
        } else if (mois == 8) {
            Mois = "Août";
        } else if (mois == 9) {
            Mois = "Septembre";
        } else if (mois == 10) {
            Mois = "Octobre";
        } else if (mois == 11) {
            Mois = "Novembre";
        } else if (mois == 12) {
            Mois = "Decembre";
        } else if (mois == 13) {
            mois = 1;
            Mois = "Janvier";
            Annee = Annee + 1;
        } else if (mois == 0) {
            mois = 12;
            Mois = "Decembre";
            annee = annee - 1;
        };

        jLabel_Mois.setText(Mois + " " + annee);

        DefaultTableModel dm = (DefaultTableModel) jTable_Frais.getModel();
        dm.setRowCount(0);
        NoteFraisDAO uneNoteFraisDAO = new NoteFraisDAO();
        UtilisateurDAO unUtilisateurDAO = new UtilisateurDAO();
        ClientDAO unClientDAO = new ClientDAO();
        ValidationDAO unValiderDAO = new ValidationDAO();
        DepenseDAO uneDepenseDAO = new DepenseDAO();
        try {
            ArrayList<NoteFrais> tableau = uneNoteFraisDAO.listNoteFraisMois(mois, annee);
            ArrayList<Utilisateur> tableau2 = unUtilisateurDAO.listUtilisateur();
            ArrayList<Client> tableau3 = unClientDAO.listClient();
            ArrayList<Validation> tableau4 = unValiderDAO.listValidation();

            for (int i = 0; i < tableau.size(); i++) {

                NoteFrais result = tableau.get(i);

                String Date_Note = result.getDate_Notefrais();
                System.out.println("date:" + Date_Note);

                int Id_Notefrais = result.getId_Notefrais();
                String id_Notefrais = "" + Id_Notefrais;
                int Salarie_int = result.getId_Utilisateur();
                int Client_int = result.getId_Client();
                //chercher le nom
                String Total = "" + uneDepenseDAO.montantTotalNote(Id_Notefrais);
                String Nom_Salarie = "";
                String Prenom_Salarie = "";
                String Nom_Client = "";
                String Prenom_Client = "";
                String Statut = "";
                nombre2 = uneDepenseDAO.nbreDepenseByIdNotefrais(Id_Notefrais);
                System.out.println("nombre2 :" + nombre2);
                for (int j = 0; j < tableau2.size(); j++) {
                    Utilisateur result2 = tableau2.get(j);
                    if (result2.getId_Utilisateur() == Salarie_int) {
                        Nom_Salarie = result2.getNom_Utilisateur();
                        Prenom_Salarie = result2.getPrenom_Utilisateur();
                    }
                }
                for (int k = 0; k < tableau3.size(); k++) {
                    Client result3 = tableau3.get(k);
                    if (result3.getId_Client() == Client_int) {
                        Nom_Client = result3.getNom_Client();
                        Prenom_Client = result3.getPrenom_Client();
                    }
                }

                if (tableau4.size() == 0) {
                    Statut = "En attente";
                }else{
                for (int l = 0; l < tableau4.size(); l++) {
                    Validation result4 = tableau4.get(l);

                    if (result4.getId_Notefrais() == Id_Notefrais) {
                        nombre =nombre+1;
                        System.out.println("nombre :" + nombre);
                        if (nombre == nombre2) {
                            Statut = "Valider";
                        } else {
                            Statut = "En attente";
                        }
                    }
                }}
                String a[] = {Prenom_Salarie + " " + Nom_Salarie, Nom_Client + " " + Prenom_Client, Date_Note, Total+"€", Statut, id_Notefrais};
                
                Tableau tabl= new Tableau(Prenom_Salarie + " " + Nom_Salarie, Nom_Client + " " + Prenom_Client, Date_Note, Total+"€", Statut);
                a2.add(tabl);
                dm.addRow(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeNote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_Titre = new javax.swing.JLabel();
        jLabel_Mois = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Frais = new javax.swing.JTable();
        jButton_Prec = new javax.swing.JButton();
        jButton_Annuler = new javax.swing.JButton();
        jButton_Suiv = new javax.swing.JButton();
        jButton_Edit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setName("Frame_Note"); // NOI18N

        jLabel_Titre.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel_Titre.setForeground(java.awt.Color.white);
        jLabel_Titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Titre.setText("Liste Note de Frais");
        jLabel_Titre.setFocusable(false);
        jLabel_Titre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel_Mois.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Mois.setForeground(java.awt.Color.white);
        jLabel_Mois.setText("Mois en cours");

        jTable_Frais.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable_Frais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Salarié", "Client", "Date", "Valeur", "Statut", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Frais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_FraisMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Frais);
        if (jTable_Frais.getColumnModel().getColumnCount() > 0) {
            jTable_Frais.getColumnModel().getColumn(5).setMinWidth(0);
            jTable_Frais.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        jButton_Prec.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Prec.setText("Mois precedent");
        jButton_Prec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PrecActionPerformed(evt);
            }
        });

        jButton_Annuler.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Annuler.setText("Annuler");
        jButton_Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AnnulerActionPerformed(evt);
            }
        });

        jButton_Suiv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Suiv.setText("Mois suivant");
        jButton_Suiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SuivActionPerformed(evt);
            }
        });

        jButton_Edit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Edit.setText("Editer liste mensuelle");
        jButton_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_Edit)
                        .addGap(200, 200, 200)
                        .addComponent(jButton_Annuler))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton_Prec)
                            .addGap(212, 212, 212)
                            .addComponent(jLabel_Mois)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Suiv))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel_Titre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel_Titre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Mois)
                    .addComponent(jButton_Prec)
                    .addComponent(jButton_Suiv))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Annuler)
                    .addComponent(jButton_Edit))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_PrecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PrecActionPerformed
       a2.clear();
        mois = mois - 1;
        if (mois == 1) {
            Mois = "Janvier";
        } else if (mois == 2) {
            Mois = "Fevrier";
        } else if (mois == 3) {
            Mois = "Mars";
        } else if (mois == 4) {
            Mois = "Avril";
        } else if (mois == 5) {
            Mois = "mais";
        } else if (mois == 6) {
            Mois = "Juin";
        } else if (mois == 7) {
            Mois = "Juillet";
        } else if (mois == 8) {
            Mois = "Aout";
        } else if (mois == 9) {
            Mois = "Septembre";
        } else if (mois == 10) {
            Mois = "Octobre";
        } else if (mois == 11) {
            Mois = "Novembre";
        } else if (mois == 12) {
            Mois = "Decembre";
        } else if (mois == 13) {
            mois = 1;
            Mois = "Janvier";
            Annee = Annee + 1;
        } else if (mois == 0) {
            mois = 12;
            Mois = "Decembre";
            annee = annee - 1;
        };

        jLabel_Mois.setText(Mois + " " + annee);

        DefaultTableModel dm = (DefaultTableModel) jTable_Frais.getModel();
        dm.setRowCount(0);
        NoteFraisDAO uneNoteFraisDAO = new NoteFraisDAO();
        UtilisateurDAO unUtilisateurDAO = new UtilisateurDAO();
        ClientDAO unClientDAO = new ClientDAO();
        ValidationDAO unValiderDAO = new ValidationDAO();
        DepenseDAO uneDepenseDAO = new DepenseDAO();
        try {
            ArrayList<NoteFrais> tableau = uneNoteFraisDAO.listNoteFraisMois(mois, annee);
            ArrayList<Utilisateur> tableau2 = unUtilisateurDAO.listUtilisateur();
            ArrayList<Client> tableau3 = unClientDAO.listClient();
            ArrayList<Validation> tableau4 = unValiderDAO.listValidation();

            for (int i = 0; i < tableau.size(); i++) {

                NoteFrais result = tableau.get(i);

                String Date_Note = result.getDate_Notefrais();
                System.out.println("date:" + Date_Note);

                int Id_Notefrais = result.getId_Notefrais();
                String id_Notefrais = "" + Id_Notefrais;
                int Salarie_int = result.getId_Utilisateur();
                int Client_int = result.getId_Client();
                //chercher le nom
                String Total = "" + uneDepenseDAO.montantTotalNote(Id_Notefrais);
                String Nom_Salarie = "";
                String Prenom_Salarie = "";
                String Nom_Client = "";
                String Prenom_Client = "";
                String Statut = "";
                for (int j = 0; j < tableau2.size(); j++) {
                    Utilisateur result2 = tableau2.get(j);
                    if (result2.getId_Utilisateur() == Salarie_int) {
                        Nom_Salarie = result2.getNom_Utilisateur();
                        Prenom_Salarie = result2.getPrenom_Utilisateur();
                    }
                }
                for (int k = 0; k < tableau3.size(); k++) {
                    Client result3 = tableau3.get(k);
                    if (result3.getId_Client() == Client_int) {
                        Nom_Client = result3.getNom_Client();
                        Prenom_Client = result3.getPrenom_Client();
                    }
                }

                if (tableau4.size() == 0) {
                    Statut = "En attente";
                }else{
                for (int l = 0; l < tableau4.size(); l++) {
                    Validation result4 = tableau4.get(l);
                    if (result4.getId_Notefrais() == Id_Notefrais) {
                        Statut = "Valider";
                    } else {
                        Statut = "En attente";
                    }
                }}
                String a[] = {Prenom_Salarie + " " + Nom_Salarie, Nom_Client + " " + Prenom_Client, Date_Note, Total+"€", Statut, id_Notefrais};
                dm.addRow(a);
                Tableau tabl= new Tableau(Prenom_Salarie + " " + Nom_Salarie, Nom_Client + " " + Prenom_Client, Date_Note, Total+"€", Statut);
                a2.add(tabl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeNote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_PrecActionPerformed

    private void jButton_SuivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SuivActionPerformed
       a2.clear();
        mois = mois + 1;
        if (mois == 1) {
            Mois = "Janvier";
        } else if (mois == 2) {
            Mois = "Fevrier";
        } else if (mois == 3) {
            Mois = "Mars";
        } else if (mois == 4) {
            Mois = "Avril";
        } else if (mois == 5) {
            Mois = "mais";
        } else if (mois == 6) {
            Mois = "Juin";
        } else if (mois == 7) {
            Mois = "Juillet";
        } else if (mois == 8) {
            Mois = "Aout";
        } else if (mois == 9) {
            Mois = "Septembre";
        } else if (mois == 10) {
            Mois = "Octobre";
        } else if (mois == 11) {
            Mois = "Novembre";
        } else if (mois == 12) {
            Mois = "Decembre";
        } else if (mois == 13) {
            mois = 1;
            Mois = "Janvier";
            annee = annee + 1;
        } else if (mois == 0) {
            mois = 12;
            Mois = "Decembre";
            annee = annee - 1;
        };
        jLabel_Mois.setText(Mois + " " + annee);

        DefaultTableModel dm = (DefaultTableModel) jTable_Frais.getModel();
        dm.setRowCount(0);
        NoteFraisDAO uneNoteFraisDAO = new NoteFraisDAO();
        UtilisateurDAO unUtilisateurDAO = new UtilisateurDAO();
        ClientDAO unClientDAO = new ClientDAO();
        ValidationDAO unValiderDAO = new ValidationDAO();
        DepenseDAO uneDepenseDAO = new DepenseDAO();
        try {
            ArrayList<NoteFrais> tableau = uneNoteFraisDAO.listNoteFraisMois(mois, annee);
            ArrayList<Utilisateur> tableau2 = unUtilisateurDAO.listUtilisateur();
            ArrayList<Client> tableau3 = unClientDAO.listClient();
            ArrayList<Validation> tableau4 = unValiderDAO.listValidation();

            for (int i = 0; i < tableau.size(); i++) {

                NoteFrais result = tableau.get(i);

                String Date_Note = result.getDate_Notefrais();
                System.out.println("date:" + Date_Note);

                int Id_Notefrais = result.getId_Notefrais();
                String id_Notefrais = "" + Id_Notefrais;
                int Salarie_int = result.getId_Utilisateur();
                int Client_int = result.getId_Client();
                //chercher le nom
                String Total = "" + uneDepenseDAO.montantTotalNote(Id_Notefrais);
                String Nom_Salarie = "";
                String Prenom_Salarie = "";
                String Nom_Client = "";
                String Prenom_Client = "";
                String Statut = "";
                for (int j = 0; j < tableau2.size(); j++) {
                    Utilisateur result2 = tableau2.get(j);
                    if (result2.getId_Utilisateur() == Salarie_int) {
                        Nom_Salarie = result2.getNom_Utilisateur();
                        Prenom_Salarie = result2.getPrenom_Utilisateur();
                    }
                }
                for (int k = 0; k < tableau3.size(); k++) {
                    Client result3 = tableau3.get(k);
                    if (result3.getId_Client() == Client_int) {
                        Nom_Client = result3.getNom_Client();
                        Prenom_Client = result3.getPrenom_Client();
                    }
                }

                if (tableau4.size() == 0) {
                    Statut = "En attente";
                }else{
                for (int l = 0; l < tableau4.size(); l++) {
                    Validation result4 = tableau4.get(l);

                    if (result4.getId_Notefrais() == Id_Notefrais) {
                        Statut = "Valider";
                    } else {
                        Statut = "En attente";
                    }
                }}
                String a[] = {Prenom_Salarie + " " + Nom_Salarie, Nom_Client + " " + Prenom_Client, Date_Note, Total+"€", Statut, id_Notefrais};
                dm.addRow(a);
                Tableau tabl= new Tableau(Prenom_Salarie + " " + Nom_Salarie, Nom_Client + " " + Prenom_Client, Date_Note, Total+"€", Statut);
                a2.add(tabl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeNote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_SuivActionPerformed

    private void jButton_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EditActionPerformed
String Date=(Mois + "-" + annee); 
pdf2 pdf1= new pdf2();
pdf1.pdf(Date,a2);
       /* try {
            Telechargements uneFenetre = new Telechargements(Date);
            uneFenetre.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ListeNote.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }//GEN-LAST:event_jButton_EditActionPerformed

    private void jTable_FraisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_FraisMouseClicked

        int ligneSelectionne = jTable_Frais.getSelectedRow();
//on récupére la valeur de la première colonne de la ligne sélectionné
        String Id_Jtable1 = (String) jTable_Frais.getValueAt(ligneSelectionne, 5);
        int id_Jtable = Integer.parseInt(Id_Jtable1);
        System.out.println(id_Jtable);
        int retour= 1;
        DetailNote uneFenetre = new DetailNote(id_Jtable,retour);
        uneFenetre.setVisible(true);
        ListeNote.this.setVisible(false);
    }//GEN-LAST:event_jTable_FraisMouseClicked

    private void jButton_AnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AnnulerActionPerformed
       Accueil uneFenetre = new Accueil();
        uneFenetre.setVisible(true);
        ListeNote.this.setVisible(false);
    }//GEN-LAST:event_jButton_AnnulerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListeNote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListeNote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListeNote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListeNote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListeNote().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Annuler;
    private javax.swing.JButton jButton_Edit;
    private javax.swing.JButton jButton_Prec;
    private javax.swing.JButton jButton_Suiv;
    private javax.swing.JLabel jLabel_Mois;
    private javax.swing.JLabel jLabel_Titre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Frais;
    // End of variables declaration//GEN-END:variables

    private Vector get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
