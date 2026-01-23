package com.kotak.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PdfTemplate {

    public static void main(String[] args) {
        // Use try-with-resources for automatic closing of document
        try (PDDocument document = new PDDocument()) {
            PDPage pricing = new PDPage(PDRectangle.A4);
            document.addPage(pricing);

            // Initialize stream inside try-with-resources
            try (PDPageContentStream contentStream = new PDPageContentStream(document, pricing)) {

                // Coordinates: A4 is roughly 595 width x 842 height
                // Moving x=50, y=750 to start from the top-left
                // Width=400, Height=30 to fit the long text
                drawCell(contentStream, 50, 750, 450, 30, "PRICING SHEET FOR LENDING TO REPO RATE (Ver 2.41)");

                // Example of a second row (Moving down by 30 units)
                // general section
                drawCell(contentStream, 50, 720, 450, 30, "General");

// Row 3: Customer Name (Labels at X=50, Values at X=200)
                drawCell(contentStream, 50, 690, 150, 30, "CUSTOMER NAME:");
                drawCell(contentStream, 200, 690, 300, 30, "NAVEEN KAPOOR");

// Row 4: CRN
                drawCell(contentStream, 50, 660, 150, 30, "CRN:");
                drawCell(contentStream, 200, 660, 300, 30, "KXT77037");

// Row 5: MSME
                drawCell(contentStream, 50, 630, 150, 30, "MSME:");
                drawCell(contentStream, 200, 630, 300, 30, "YES");






            }

            document.save("Pricing_Sheet.pdf");
            System.out.println("PDF Created Successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void drawCell(PDPageContentStream contentStream, float x, float y, float width, float height, String text) {
        try {
            // 1. Draw the Rectangle (Border)
            contentStream.addRect(x, y, width, height);
            contentStream.stroke();

            // 2. Add the Text
            contentStream.beginText();
            // In PDFBox 3.x use: new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            // Positioning text inside the box (Offset from bottom-left of cell)
            contentStream.newLineAtOffset(x + 5, y + (height / 2) - 4);
            contentStream.showText(text);
            contentStream.endText();

        } catch (IOException e) {
            throw new RuntimeException("Error drawing cell: " + e.getMessage());
        }
    }
}
