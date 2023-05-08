package ooploverz.tubes2_oop.report;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.json.JSONArray;

import java.io.IOException;

public class BillReport {
    public static void printPDF(String filename, JSONArray data){
        try {
            // Create a new document
            PDDocument document = new PDDocument();

            // Create a new page
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Create a content stream for the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Set the font and font size
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);

            // Create Report Title
            contentStream.beginText();
            contentStream.newLineAtOffset(250, 800);
            contentStream.showText("Bill Report");
            contentStream.endText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            float y = 800;
            y -= 35;
            contentStream.setLineWidth(1.0f);
            contentStream.moveTo(100, y);
            contentStream.lineTo(500, y);
            contentStream.stroke();

            y-= 15;

            // Create the report table
            // Table Header
            contentStream.beginText();
            contentStream.newLineAtOffset(150,y);
            contentStream.showText("Buyer id");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(300, y);
            contentStream.showText("Amount");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(450,y);
            contentStream.showText("Date");
            contentStream.endText();

            long totalExpenses = 1000000;
            y-=20;
            try{
                for (int i=0; i < data.length(); i++){
                    contentStream.beginText();
                    contentStream.newLineAtOffset(150,y);
                    contentStream.showText(Integer.toString(data.getJSONObject(i).getInt("buyerId")));
                    contentStream.endText();
                    contentStream.beginText();
                    contentStream.newLineAtOffset(300,y);
                    int amount = data.getJSONObject(i).getInt("total");
                    totalExpenses += amount;
                    contentStream.showText(Integer.toString(amount));
                    contentStream.endText();
                    contentStream.beginText();
                    contentStream.newLineAtOffset(450,y);
                    contentStream.showText(data.getJSONObject(i).getString("date"));
                    contentStream.endText();
                    y-=20;
                }
            } catch (Exception e ){
                e.printStackTrace();
            }
            y -= 35;
            contentStream.setLineWidth(1.0f);
            contentStream.moveTo(100, y);
            contentStream.lineTo(500, y);
            contentStream.stroke();

            y-= 15;
            // Create the report summary
            contentStream.beginText();
            contentStream.newLineAtOffset(100,y);
            contentStream.showText("Summary");
            contentStream.endText();
            y-=30;



            contentStream.beginText();
            contentStream.newLineAtOffset(100,y);
            contentStream.showText("Total Sales");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(200, y);
            contentStream.showText(Integer.toString(data.length()));
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(350,y);
            contentStream.showText("Total Expenses");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(450, y);
            contentStream.showText(Long.toString(totalExpenses));
            contentStream.endText();

            // Table

            contentStream.close();
            // Save the document to a file
            document.save("src/main/resources/ooploverz/tubes2_oop/Database/example.pdf");
            System.out.println("PDF printed successfully.");

            // Close the document
            document.close();

            // Simulate a long process with a 10-second delay
            Thread.sleep(1);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
