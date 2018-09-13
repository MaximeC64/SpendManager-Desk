/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import DAO.UtilisateurDAO;
import Metier.Pdf_salarie;
import Metier.Tableau;
import Metier.Utilisateur;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author pierrick.pabijan
 */
public class pdf1 {
    public void pdf( ArrayList maliste, int Id) throws SQLException, BadElementException, IOException {
        
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateur oneUtilisateur = utilisateurDAO.readOneUtilisateur(Id);

        String nom=oneUtilisateur.getNom_Utilisateur();
        String prenom=oneUtilisateur.getPrenom_Utilisateur();
        String mail=oneUtilisateur.getMail_Utilisateur();
        String adresse=oneUtilisateur.getAdresse_Utilisateur();
        String cp=oneUtilisateur.getCp_Utilisateur();
        String ville=oneUtilisateur.getVille_Utilisateur();
        String tel=oneUtilisateur.getTelephone_Utilisateur();
        
        
        Document document = new Document();
        
        try {
            PdfWriter.getInstance(document,
                    /*new FileOutputStream("NoteFrais_" + date + ".pdf"));*/
                    new FileOutputStream("Fiche_individuelle.pdf"));
            //police
            Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                    Font.ITALIC | Font.UNDERLINE);
            Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            Font font4 = new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.BOLD);
            
            document.open();
            //ajout image
            try {
                document.add(Image.getInstance("images/barre.png"));
            } catch (IOException ex) {
                Logger.getLogger(pdf1.class.getName()).log(Level.SEVERE, null, ex);
            }
            //creer titre
            Paragraph paragraph = new Paragraph("Fiche individuelle de "+ nom +" "+prenom, font4);
            //espace sous titre
            paragraph.setSpacingAfter(25);
            //centrer titre
            paragraph.setAlignment (Element.ALIGN_CENTER);
            //ajouter titre au pdf
            document.add(paragraph);
            
            
            
            document.add(new Phrase("Email: ", font1));
            document.add(new Phrase(mail));
            document.add(new Paragraph("   "));
            document.add(new Phrase("Adresse: ", font1));
            document.add(new Phrase(adresse));
            document.add(new Paragraph("   "));
            document.add(new Phrase("Code postal: ", font1));
            document.add(new Phrase(cp));
            document.add(new Paragraph("   "));
            document.add(new Phrase("Ville: ", font1));
            document.add(new Phrase(ville));
            document.add(new Paragraph("   "));
            document.add(new Phrase("Telephone: ", font1));
            document.add(new Phrase(tel));
            document.add(new Paragraph("   "));
            document.add(new Phrase("liste des notes de frais: ", font1));
            document.add(new Paragraph("   "));
            
            //ajout tableau
            //5 colonne
            PdfPTable table = new PdfPTable(4);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Client", font1));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Date", font1));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Valeur", font1));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Statut", font1));

            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            
         /*   for(int i = 0; i < maliste.length; i++)
{
               maliste[i]
  for(int j = 0; j < tableau2.length; j++)
{
  System.out.println("À l'emplacement " + j +" du tableau nous avons = " + tableau[j]);
  table.addCell(tableau[i]);
}
}*/
         for(int i = 0; i < maliste.size(); i++)
    {
      System.out.println("donnée à l'indice " + i + " = " + maliste.get(i));
      
           Pdf_salarie tab=(Pdf_salarie) maliste.get(i);
            table.addCell(tab.getNom());
            table.addCell(tab.getDate());
            table.addCell(tab.getTotal());
            table.addCell(tab.getStatut());
       
      
    }

            document.add(table);

            document.close(); // no need to close PDFwriter?

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            /*Runtime.getRuntime().exec("explorer.exe " + "NoteFrais_" + date + ".pdf");*/
            Runtime.getRuntime().exec("explorer.exe " + "Fiche_individuelle.pdf");
        } catch (IOException ex) {
            Logger.getLogger(pdf2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
