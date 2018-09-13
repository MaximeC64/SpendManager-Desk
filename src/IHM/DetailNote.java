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
import Metier.Utilisateur;
import Metier.Validation;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pierrick.pabijan
 */
public class DetailNote extends javax.swing.JFrame {

    NoteFraisDAO uneNoteFraisDAO = new NoteFraisDAO();
    UtilisateurDAO unUtilisateurDAO = new UtilisateurDAO();
    ClientDAO unClientDAO = new ClientDAO();
    ValidationDAO unValiderDAO = new ValidationDAO();
    DepenseDAO uneDepenseDAO = new DepenseDAO();
    int Id = 0;
    int value = 0;
    int Retour =0; 
    int Id_salarie=0;

    /**
     * Creates new form DetailNote
     */
    public DetailNote(int id, int retour) {
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icone.png"));
       getContentPane().setBackground(new Color(13, 110, 189));
        
        this.setLocationRelativeTo(null);
        this.setTitle("Détail note de frais " + id);
        jLabel_Titre2.setText("" + id);
        Id = id;
        Retour=retour;
        int nombre = 0;
        int nombre2 = 0;

        DefaultTableModel dm = (DefaultTableModel) jTable_Frais.getModel();
        dm.setRowCount(0);
        try {
            ArrayList<NoteFrais> tableau = uneNoteFraisDAO.listNoteFrais();
            ArrayList<Utilisateur> tableau2 = unUtilisateurDAO.listUtilisateur();
            ArrayList<Client> tableau3 = unClientDAO.listClient();
            ArrayList<Validation> tableau4 = unValiderDAO.listValidation();
            ArrayList<Depense> tableau5 = uneDepenseDAO.listDepense();

            for (int i = 0; i < tableau.size(); i++) {
                NoteFrais result = tableau.get(i);

                if (result.getId_Notefrais() == id) {
                    String Id_Notefrais = "";
                    int Salarie_int = 0;
                    int Client_int = 0;
                    String Total = "";
                    String Nom_Salarie = "";
                    String Prenom_Salarie = "";
                    String Nom_Client = "";
                    String Prenom_Client = "";
                    String Libelle = "";
                    String nom_Client = "";
                    String nom_Salarie = "";
                    String Date_Depense = "";
                    String Montant_Depense = "";
                    String Id_Depense = "";
                    String Statut = "";
                    int Id_Depense2 = 0;
                    Salarie_int = result.getId_Utilisateur();
                    Client_int = result.getId_Client();
                    //chercher le nom
                    Total = "" + uneDepenseDAO.montantTotalNote(id);
                    jTextField_Total.setText("" + Total+"€");

                    for (int j = 0; j < tableau2.size(); j++) {
                        Utilisateur result2 = tableau2.get(j);
                        if (result2.getId_Utilisateur() == Salarie_int) {
                            Nom_Salarie = result2.getNom_Utilisateur();
                            System.out.println("nom:" + Nom_Salarie);
                            Prenom_Salarie = result2.getPrenom_Utilisateur();
                            System.out.println("prenom:" + Prenom_Salarie);
                            Id_salarie=result2.getId_Utilisateur();
                        }
                    }
                    for (int k = 0; k < tableau3.size(); k++) {
                        Client result3 = tableau3.get(k);
                        if (result3.getId_Client() == Client_int) {
                            Nom_Client = result3.getNom_Client();
                            System.out.println("nom:" + Nom_Client);
                            Prenom_Client = result3.getPrenom_Client();
                            System.out.println("prenom:" + Prenom_Client);
                        }
                    }

                    for (int m = 0; m < tableau5.size(); m++) {
                        Depense result5 = tableau5.get(m);
                        Id_Depense = "" + result5.getId_Depense();
                        Id_Depense2 = result5.getId_Depense();
                        nombre2 = uneDepenseDAO.nbreDepenseByIdNotefrais(Id);
                        if (result5.getId_Notefrais() == Id) {
                            Libelle = result5.getLibelle_Depense();
                            Date_Depense = "" + result5.getDatePaiement_Depense();
                            Montant_Depense = "" + result5.getMontantRemboursement_Depense();
                            System.out.println("libelle:" + Libelle);
                            System.out.println("Id depense:" + Id_Depense);
                            Statut = "En attente";
                            for (int l = 0; l < tableau4.size(); l++) {
                                Validation result4 = tableau4.get(l);
                                if (result4.getId_Depense() == Id_Depense2) {
                                    Statut = "Validé";
                                }
                            }
                            String a[] = {Libelle, Montant_Depense+"€", Statut, Id_Depense};
                            dm.addRow(a);
                        }

                    }

                    nom_Client = Nom_Client + " " + Prenom_Client;
                    nom_Salarie = Prenom_Salarie + " " + Nom_Salarie;

                    jLabel_Client2.setText(nom_Client);
                    jLabel_Salarie2.setText(nom_Salarie);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeNote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private DetailNote() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        jLabel_Titre2 = new javax.swing.JLabel();
        jLabel_Salarie = new javax.swing.JLabel();
        jLabel_Salarie2 = new javax.swing.JLabel();
        jLabel_Client = new javax.swing.JLabel();
        jLabel_Client2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Frais = new javax.swing.JTable();
        jTextField_Total = new javax.swing.JTextField();
        jLabel_Total = new javax.swing.JLabel();
        jButton_Valider = new javax.swing.JButton();
        jButton_Annuler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setName("Frame_Note"); // NOI18N

        jLabel_Titre.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel_Titre.setForeground(java.awt.Color.white);
        jLabel_Titre.setText("Note de frais n°");

        jLabel_Titre2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel_Titre2.setForeground(java.awt.Color.white);
        jLabel_Titre2.setText("default");

        jLabel_Salarie.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Salarie.setForeground(java.awt.Color.white);
        jLabel_Salarie.setText("Salarié:");

        jLabel_Salarie2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_Salarie2.setForeground(java.awt.Color.white);
        jLabel_Salarie2.setText("default");

        jLabel_Client.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Client.setForeground(java.awt.Color.white);
        jLabel_Client.setText("Client:");

        jLabel_Client2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_Client2.setForeground(java.awt.Color.white);
        jLabel_Client2.setText("default");

        jTable_Frais.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable_Frais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Label", "Valeur", "Statut", "Id_depense"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
            jTable_Frais.getColumnModel().getColumn(3).setMinWidth(0);
            jTable_Frais.getColumnModel().getColumn(3).setPreferredWidth(0);
            jTable_Frais.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        jTextField_Total.setEditable(false);
        jTextField_Total.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_Total.setText("default");
        jTextField_Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_TotalActionPerformed(evt);
            }
        });

        jLabel_Total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Total.setForeground(java.awt.Color.white);
        jLabel_Total.setText("Total:");

        jButton_Valider.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Valider.setText("Valider");
        jButton_Valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ValiderActionPerformed(evt);
            }
        });

        jButton_Annuler.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Annuler.setText("Annuler");
        jButton_Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AnnulerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_Client)
                                        .addComponent(jLabel_Salarie))
                                    .addGap(36, 36, 36)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel_Client2)
                                        .addComponent(jLabel_Salarie2)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton_Annuler)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Valider))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel_Total)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel_Titre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Titre2)))
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Titre)
                    .addComponent(jLabel_Titre2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_Salarie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Client)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Total))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Valider)
                            .addComponent(jButton_Annuler)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_Salarie2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Client2)))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_TotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_TotalActionPerformed

    private void jButton_ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ValiderActionPerformed

        ListeNote uneFenetre = new ListeNote();
        uneFenetre.setVisible(true);
        DetailNote.this.setVisible(false);

    }//GEN-LAST:event_jButton_ValiderActionPerformed

    private void jButton_AnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AnnulerActionPerformed
        if(Retour==1){
        ListeNote uneFenetre = new ListeNote();
        uneFenetre.setVisible(true);
        DetailNote.this.setVisible(false);
        }else if (Retour==2){
        
            try {
                DetailSalarie uneFenetre = new DetailSalarie(Id_salarie);
                uneFenetre.setVisible(true);
                DetailNote.this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(DetailNote.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }

    }//GEN-LAST:event_jButton_AnnulerActionPerformed

    private void jTable_FraisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_FraisMouseClicked
        int ligneSelectionne = jTable_Frais.getSelectedRow();
//on récupére la valeur de la première colonne de la ligne sélectionné
        String Id_Jtable1 = (String) jTable_Frais.getValueAt(ligneSelectionne, 3);
        int id_Jtable = Integer.parseInt(Id_Jtable1);
        System.out.println(id_Jtable);
        DetailDepense uneFenetre;
        try {
            uneFenetre = new DetailDepense(id_Jtable, Id, Retour);
            uneFenetre.setVisible(true);
            DetailNote.this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(DetailNote.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTable_FraisMouseClicked

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
            java.util.logging.Logger.getLogger(DetailNote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailNote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailNote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailNote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetailNote().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Annuler;
    private javax.swing.JButton jButton_Valider;
    private javax.swing.JLabel jLabel_Client;
    private javax.swing.JLabel jLabel_Client2;
    private javax.swing.JLabel jLabel_Salarie;
    private javax.swing.JLabel jLabel_Salarie2;
    private javax.swing.JLabel jLabel_Titre;
    private javax.swing.JLabel jLabel_Titre2;
    private javax.swing.JLabel jLabel_Total;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Frais;
    private javax.swing.JTextField jTextField_Total;
    // End of variables declaration//GEN-END:variables
}
