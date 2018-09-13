/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import DAO.ClientDAO;
import DAO.DepenseDAO;
import DAO.FraisDAO;
import DAO.NoteFraisDAO;
import DAO.TrajetDAO;
import DAO.UtilisateurDAO;
import DAO.ValidationDAO;
import Metier.Client;
import Metier.Depense;
import Metier.Frais;
import Metier.NoteFrais;
import Metier.Trajet;
import Metier.Utilisateur;
import Metier.Validation;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pierrick.pabijan
 */
public class DetailDepense extends javax.swing.JFrame {
    static Locale locale = Locale.getDefault();
    static Date actuelle = new Date();
    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public static String date() {
        String dat = dateFormat.format(actuelle);
        return dat;
    }

    TrajetDAO unTrajetDAO = new TrajetDAO();
    DepenseDAO uneDepenseDAO = new DepenseDAO();
    ValidationDAO unValiderDAO = new ValidationDAO();
    NoteFraisDAO uneNoteFraisDAO = new NoteFraisDAO();
    FraisDAO unFraisDAO= new FraisDAO();
    ClientDAO unClientDAO = new ClientDAO();
    UtilisateurDAO unUtilisateurDAO = new UtilisateurDAO();
    
    int Id = 0;
    int Id_Note = 0;
    int value = 0;
    int Id_Utilisateur=0;
    int Retour=0;
    int Id_Client=0;
    int IdUtilisateur=0;
    String NomSalarie="";
    String Date_Frais="";
    String nomClient="";
    /**
     * Creates new form DetailNote
     */
    public DetailDepense(int id_depense, int id_note, int retour) throws SQLException {
        
        
        initComponents();
        this.setTitle("Détail dépense n° " + id_depense);
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icone.png"));
        getContentPane().setBackground(new Color(13, 110, 189));
        Retour = retour;
        Id = id_depense;
        Id_Note = id_note;
        jLabel_Titre2.setText("" + id_depense);
        
        String commentaire;
        
        ArrayList<Trajet> tableau = unTrajetDAO.listTrajet();
        ArrayList<NoteFrais> tableau2 = uneNoteFraisDAO.listNoteFrais();
        ArrayList<Frais> tableau5 = unFraisDAO.listFrais();
        ArrayList<Client> tableau6 = unClientDAO.listClient();
        ArrayList<Utilisateur> tableau7 = unUtilisateurDAO.listUtilisateur();
        
        
        for (int h = 0; h < tableau5.size(); h++) {
            Frais result5 = tableau5.get(h);
            if (result5.getId_Depense() == Id) {
              Date_Frais=result5.getDate_Frais();
            }
        }
        for (int k = 0; k < tableau2.size(); k++) {
            NoteFrais result2 = tableau2.get(k);
            if (result2.getId_Notefrais() == Id_Note) {
              Id_Utilisateur=result2.getId_Client();
              IdUtilisateur=result2.getId_Utilisateur();
              
            }
        }
        for (int f = 0; f < tableau7.size(); f++) {
            Utilisateur result7 = tableau7.get(f);
            if (result7.getId_Utilisateur() == IdUtilisateur) {
              NomSalarie=result7.getNom_Utilisateur()+" "+result7.getPrenom_Utilisateur();
            }
        }
        for (int g = 0; g < tableau6.size(); g++) {
            Client result6 = tableau6.get(g);
            if (result6.getId_Client() == Id_Utilisateur) {
              nomClient=result6.getNom_Client()+" "+result6.getPrenom_Client();
              
              
            }
        }
        for (int i = 0; i < tableau.size(); i++) {
            Trajet result = tableau.get(i);
            if (result.getId_Depense() == Id) {
                commentaire = result.getCommentaire_Depense();
                jTextArea_Commentaire.setText(commentaire);
                jLabel_Libelle2.setText(result.getLibelle_Depense());
                jLabel_Valeur2.setText("" + result.getMontantRemboursement_Depense()+"€");
                jLabel_depart2.setText(result.getVilleDepart_Trajet());
                jLabel_arrive2.setText(result.getVilleArrivee_Trajet());
                jLabel_km2.setText("" + result.getKilometre_Trajet());
                jLabel_aller2.setText(result.getDateAller_Trajet());
                jLabel_retour2.setText(result.getDateRetour_Trajet());
                jLabel_duree2.setText(""+result.getDuree_Trajet());
                jLabel_date.setText("");
                jLabel_date1.setText("");
                jLabel_salarie2.setText(NomSalarie);
                jLabel_client2.setText(nomClient);
                

            } else {
                ArrayList<Depense> tableau3 = uneDepenseDAO.listDepense();
                for (int j = 0; j < tableau3.size(); j++) {
                    Depense result3 = tableau3.get(j);
                    if (result3.getId_Depense() == Id) {
                        commentaire = result3.getCommentaire_Depense();
                        jTextArea_Commentaire.setText(commentaire);
                        jLabel_Libelle2.setText(result3.getLibelle_Depense());
                        jLabel_Valeur2.setText("" + result3.getMontantRemboursement_Depense()+"€");
                        jLabel_depart.setText("");
                        jLabel_arrive.setText("");
                        jLabel_km.setText("");
                        jLabel_depart2.setText("");
                        jLabel_arrive2.setText("");
                        jLabel_km2.setText("");
                        jLabel_aller.setText("");
                        jLabel_aller2.setText("");
                        jLabel_retour.setText("");
                        jLabel_retour2.setText("");
                        jLabel_duree.setText("");
                        jLabel_duree2.setText("");
                        jLabel_date.setText(Date_Frais);
                        jLabel_client2.setText(nomClient);
                        jLabel_salarie2.setText(NomSalarie);

                    }
                }
            }

        }
        ArrayList<Validation> tableau4 = unValiderDAO.listValidation();
        for (int l = 0; l < tableau4.size(); l++) {
            Validation result4 = tableau4.get(l);
            if (result4.getId_Depense() == Id) {
             jRadioButton_Oui.setVisible(false);
             jLabel_Valider.setVisible(false);
            }
        }

    }

    private DetailDepense() {
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
        jLabel_Valeur = new javax.swing.JLabel();
        jLabel_Valeur2 = new javax.swing.JLabel();
        jLabel_Libelle = new javax.swing.JLabel();
        jLabel_Libelle2 = new javax.swing.JLabel();
        jRadioButton_Oui = new javax.swing.JRadioButton();
        jLabel_Valider = new javax.swing.JLabel();
        jLabel_Commentaire = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_Commentaire = new javax.swing.JTextArea();
        jButton_Valider = new javax.swing.JButton();
        jButton_Annuler = new javax.swing.JButton();
        jLabel_Justificatif1 = new javax.swing.JLabel();
        jLabel_Justificatif2 = new javax.swing.JLabel();
        jLabel_depart2 = new javax.swing.JLabel();
        jLabel_depart = new javax.swing.JLabel();
        jLabel_arrive = new javax.swing.JLabel();
        jLabel_arrive2 = new javax.swing.JLabel();
        jLabel_km = new javax.swing.JLabel();
        jLabel_km2 = new javax.swing.JLabel();
        jLabel_duree2 = new javax.swing.JLabel();
        jLabel_duree = new javax.swing.JLabel();
        jLabel_aller = new javax.swing.JLabel();
        jLabel_aller2 = new javax.swing.JLabel();
        jLabel_retour = new javax.swing.JLabel();
        jLabel_retour2 = new javax.swing.JLabel();
        jLabel_date = new javax.swing.JLabel();
        jLabel_date1 = new javax.swing.JLabel();
        jLabel_salarie2 = new javax.swing.JLabel();
        jLabel_salarie = new javax.swing.JLabel();
        jLabel_client2 = new javax.swing.JLabel();
        jLabel_client = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setName("Frame_Note"); // NOI18N

        jLabel_Titre.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel_Titre.setForeground(java.awt.Color.white);
        jLabel_Titre.setText("Detail dépense n°");

        jLabel_Titre2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel_Titre2.setForeground(java.awt.Color.white);
        jLabel_Titre2.setText("default");

        jLabel_Valeur.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Valeur.setForeground(java.awt.Color.white);
        jLabel_Valeur.setText("Valeur:");

        jLabel_Valeur2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_Valeur2.setForeground(java.awt.Color.white);
        jLabel_Valeur2.setText("default");

        jLabel_Libelle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Libelle.setForeground(java.awt.Color.white);
        jLabel_Libelle.setText("Libelle:");

        jLabel_Libelle2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_Libelle2.setForeground(java.awt.Color.white);
        jLabel_Libelle2.setText("default");

        jRadioButton_Oui.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton_Oui.setForeground(java.awt.Color.white);
        jRadioButton_Oui.setText("oui");
        jRadioButton_Oui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_OuiActionPerformed(evt);
            }
        });

        jLabel_Valider.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Valider.setForeground(java.awt.Color.white);
        jLabel_Valider.setText("Valider:");

        jLabel_Commentaire.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Commentaire.setForeground(java.awt.Color.white);
        jLabel_Commentaire.setText("Commentaire:");

        jTextArea_Commentaire.setColumns(20);
        jTextArea_Commentaire.setRows(5);
        jScrollPane2.setViewportView(jTextArea_Commentaire);

        jButton_Valider.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Valider.setText("Valider");
        jButton_Valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ValiderActionPerformed(evt);
            }
        });

        jButton_Annuler.setText("Annuler");
        jButton_Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AnnulerActionPerformed(evt);
            }
        });

        jLabel_Justificatif1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Justificatif1.setForeground(java.awt.Color.white);
        jLabel_Justificatif1.setText("Justificatif:");

        jLabel_Justificatif2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_Justificatif2.setForeground(java.awt.Color.white);
        jLabel_Justificatif2.setText("default");

        jLabel_depart2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_depart2.setForeground(java.awt.Color.white);
        jLabel_depart2.setText("default");

        jLabel_depart.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_depart.setForeground(java.awt.Color.white);
        jLabel_depart.setText("Ville de départ:");

        jLabel_arrive.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_arrive.setForeground(java.awt.Color.white);
        jLabel_arrive.setText("Ville de d'arrivé:");

        jLabel_arrive2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_arrive2.setForeground(java.awt.Color.white);
        jLabel_arrive2.setText("default");

        jLabel_km.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_km.setForeground(java.awt.Color.white);
        jLabel_km.setText("Kilometrage:");

        jLabel_km2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_km2.setForeground(java.awt.Color.white);
        jLabel_km2.setText("default");

        jLabel_duree2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_duree2.setForeground(java.awt.Color.white);
        jLabel_duree2.setText("default");

        jLabel_duree.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_duree.setForeground(java.awt.Color.white);
        jLabel_duree.setText("Durée:");

        jLabel_aller.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_aller.setForeground(java.awt.Color.white);
        jLabel_aller.setText("Date aller:");

        jLabel_aller2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_aller2.setForeground(java.awt.Color.white);
        jLabel_aller2.setText("default");

        jLabel_retour.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_retour.setForeground(java.awt.Color.white);
        jLabel_retour.setText("Date retour:");

        jLabel_retour2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_retour2.setForeground(java.awt.Color.white);
        jLabel_retour2.setText("default");

        jLabel_date.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_date.setForeground(java.awt.Color.white);
        jLabel_date.setText("default");

        jLabel_date1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_date1.setForeground(java.awt.Color.white);
        jLabel_date1.setText("Date:");

        jLabel_salarie2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_salarie2.setForeground(java.awt.Color.white);
        jLabel_salarie2.setText("default");

        jLabel_salarie.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_salarie.setForeground(java.awt.Color.white);
        jLabel_salarie.setText("Salarié:");

        jLabel_client2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_client2.setForeground(java.awt.Color.white);
        jLabel_client2.setText("default");

        jLabel_client.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_client.setForeground(java.awt.Color.white);
        jLabel_client.setText("Client");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel_Titre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Titre2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Commentaire)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_Valider)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton_Oui))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jButton_Annuler)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton_Valider))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_salarie)
                                        .addComponent(jLabel_date1))
                                    .addGap(57, 57, 57)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_date, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel_salarie2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_Justificatif1)
                                    .addComponent(jLabel_Valeur)
                                    .addComponent(jLabel_Libelle)
                                    .addComponent(jLabel_client))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_client2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_Valeur2)
                                    .addComponent(jLabel_Justificatif2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel_depart)
                                                .addGap(24, 24, 24))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel_arrive)
                                                    .addComponent(jLabel_aller)
                                                    .addComponent(jLabel_retour)
                                                    .addComponent(jLabel_km)
                                                    .addComponent(jLabel_duree))
                                                .addGap(18, 18, 18)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel_arrive2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel_aller2)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel_retour2)
                                                .addComponent(jLabel_depart2))
                                            .addComponent(jLabel_duree2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel_km2, javax.swing.GroupLayout.Alignment.LEADING)))
                                    .addComponent(jLabel_Libelle2))))))
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Titre)
                    .addComponent(jLabel_Titre2))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_date1)
                    .addComponent(jLabel_date))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_salarie)
                    .addComponent(jLabel_salarie2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_client)
                    .addComponent(jLabel_client2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Libelle)
                    .addComponent(jLabel_Libelle2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Valeur)
                    .addComponent(jLabel_Valeur2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Justificatif1)
                    .addComponent(jLabel_Justificatif2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_depart)
                    .addComponent(jLabel_depart2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_aller)
                    .addComponent(jLabel_aller2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_arrive)
                    .addComponent(jLabel_arrive2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_retour)
                    .addComponent(jLabel_retour2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_duree)
                    .addComponent(jLabel_duree2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_km2)
                    .addComponent(jLabel_km))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_Oui)
                    .addComponent(jLabel_Valider))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Commentaire)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Valider)
                    .addComponent(jButton_Annuler))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton_OuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_OuiActionPerformed
        jRadioButton_Oui.setSelected(true);
    }//GEN-LAST:event_jRadioButton_OuiActionPerformed

    private void jButton_ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ValiderActionPerformed
//mettre en place commentaire
        String Commentaire = jTextArea_Commentaire.getText();
        System.out.println(Commentaire);

        try {
            uneDepenseDAO.Commentaire(Commentaire, Id);
        } catch (SQLException ex) {
            Logger.getLogger(DetailDepense.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*Commentaire()*/
        if (jRadioButton_Oui.isSelected()) {
                Validation v = new Validation("coucou", date() , Id_Utilisateur,Id,Id_Note);
                System.out.println(v);
                System.out.println(v.getEtat_Validation()+" "+v.getDate_Validation()+" "+v.getId_Utilisateur()+" "+v.getId_Depense()+" "+v.getId_Notefrais());
            try {
                unValiderDAO.addValidation(v);
            } catch (SQLException ex) {
                Logger.getLogger(DetailDepense.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        DetailNote uneFenetre = new DetailNote(Id_Note, Retour);
        uneFenetre.setVisible(true);
        DetailDepense.this.setVisible(false);

    }//GEN-LAST:event_jButton_ValiderActionPerformed

    private void jButton_AnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AnnulerActionPerformed
        DetailNote uneFenetre = new DetailNote(Id_Note, Retour);
        uneFenetre.setVisible(true);
        DetailDepense.this.setVisible(false);

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
            java.util.logging.Logger.getLogger(DetailDepense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailDepense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailDepense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailDepense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetailDepense().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Annuler;
    private javax.swing.JButton jButton_Valider;
    private javax.swing.JLabel jLabel_Commentaire;
    private javax.swing.JLabel jLabel_Justificatif1;
    private javax.swing.JLabel jLabel_Justificatif2;
    private javax.swing.JLabel jLabel_Libelle;
    private javax.swing.JLabel jLabel_Libelle2;
    private javax.swing.JLabel jLabel_Titre;
    private javax.swing.JLabel jLabel_Titre2;
    private javax.swing.JLabel jLabel_Valeur;
    private javax.swing.JLabel jLabel_Valeur2;
    private javax.swing.JLabel jLabel_Valider;
    private javax.swing.JLabel jLabel_aller;
    private javax.swing.JLabel jLabel_aller2;
    private javax.swing.JLabel jLabel_arrive;
    private javax.swing.JLabel jLabel_arrive2;
    private javax.swing.JLabel jLabel_client;
    private javax.swing.JLabel jLabel_client2;
    private javax.swing.JLabel jLabel_date;
    private javax.swing.JLabel jLabel_date1;
    private javax.swing.JLabel jLabel_depart;
    private javax.swing.JLabel jLabel_depart2;
    private javax.swing.JLabel jLabel_duree;
    private javax.swing.JLabel jLabel_duree2;
    private javax.swing.JLabel jLabel_km;
    private javax.swing.JLabel jLabel_km2;
    private javax.swing.JLabel jLabel_retour;
    private javax.swing.JLabel jLabel_retour2;
    private javax.swing.JLabel jLabel_salarie;
    private javax.swing.JLabel jLabel_salarie2;
    private javax.swing.JRadioButton jRadioButton_Oui;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea_Commentaire;
    // End of variables declaration//GEN-END:variables
}
