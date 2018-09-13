/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Metier.Tableau;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pierrick.pabijan
 */
public class pdf2 {

    public void pdf(String date, ArrayList maliste) {
        Document document = new Document();
        
        try {
            PdfWriter.getInstance(document,
                    /*new FileOutputStream("NoteFrais_" + date + ".pdf"));*/
                    new FileOutputStream("NoteFrais.pdf"));
            //police
            Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                    Font.ITALIC | Font.UNDERLINE);
            Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            Font font4 = new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.BOLD);
            
            document.open();
            try {
                document.add(Image.getInstance("images/barre.png"));
            } catch (IOException ex) {
                Logger.getLogger(pdf1.class.getName()).log(Level.SEVERE, null, ex);
            }
            //creer titre
            Paragraph paragraph = new Paragraph("Note de frais mensuelles du " + date, font4);
            //espace sous titre
            paragraph.setSpacingAfter(25);
            //centrer titre
            paragraph.setAlignment (Element.ALIGN_CENTER);
            //ajouter titre au pdf
            document.add(paragraph);
            
            //ajout tableau
            //5 colonne
            PdfPTable table = new PdfPTable(5);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Salarié", font1));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Client", font1));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Date", font1));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Valeur", font1));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Statut", font1));

            table.addCell(cell1);
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
      
           Tableau tab=(Tableau) maliste.get(i);
           table.addCell(tab.getSalarie());
            table.addCell(tab.getClient());
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
            Runtime.getRuntime().exec("explorer.exe " + "NoteFrais.pdf");
        } catch (IOException ex) {
            Logger.getLogger(pdf2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


}
