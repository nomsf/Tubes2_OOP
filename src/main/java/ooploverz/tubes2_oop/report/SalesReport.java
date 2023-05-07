package ooploverz.tubes2_oop.report;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class SalesReport {
    public static void printPDF(String filename) {
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

            // Set the starting position for the text
            contentStream.beginText();
            contentStream.newLineAtOffset(20, 700);

            // Add the text to the page
            contentStream.showText("Hello, World!");

            // End the text and content stream
            contentStream.endText();
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
