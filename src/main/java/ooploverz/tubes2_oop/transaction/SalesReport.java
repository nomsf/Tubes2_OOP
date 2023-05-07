package ooploverz.tubes2_oop.transaction;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.json.JSONArray;

import java.io.IOException;

public class SalesReport {
    public static void printPDF(String filename, JSONArray data) {
        try {
            // Create a new document
            PDDocument document = new PDDocument();

            // Create a new page
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Create a content stream for the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Set the font and font size
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            // Create Report Title
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Transaction Report");
            contentStream.endText();

            // Create the report table
            float y = 600;
            // Table Header
            contentStream.beginText();
            contentStream.newLineAtOffset(100,y);
            contentStream.showText("Buyer id");
            contentStream.newLineAtOffset(150, y);
            contentStream.showText("Amount");
            contentStream.newLineAtOffset(200,y);
            contentStream.showText("Date");
            contentStream.endText();

            int totalExpenses = 0;
            y-=20;
            try{
                for (int i=0; i < data.length(); i++){
                    contentStream.newLineAtOffset(100,y);
                    contentStream.showText(Integer.toString(data.getJSONObject(i).getInt("buyerId")));
                    contentStream.newLineAtOffset(150,y);
                    int amount = data.getJSONObject(i).getInt("total");
                    totalExpenses += amount;
                    contentStream.showText(Integer.toString(amount));
                    contentStream.newLineAtOffset(200,y);
                    contentStream.showText(data.getJSONObject(i).getString("date"));
                    contentStream.endText();
                    y-=20;
                }
            } catch (Exception e ){
                e.printStackTrace();
            }

            // Create the report summary
            contentStream.beginText();
            contentStream.newLineAtOffset(100,y);
            contentStream.showText("Total Sales");
            contentStream.newLineAtOffset(150,0);
            contentStream.showText(Integer.toString(data.length()));

            contentStream.newLineAtOffset(150,0);
            contentStream.showText("Total Expenses");
            contentStream.newLineAtOffset(150, 0);
            contentStream.showText(Integer.toString(totalExpenses));


            // Table

            contentStream.close();
            // Save the document to a file
            document.save("src/main/resources/ooploverz/tubes2_oop/Database/example.pdf");
            System.out.println("PDF printed successfully.");

            // Close the document
            document.close();

            // Simulate a long process with a 10-second delay
            Thread.sleep(10000);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
