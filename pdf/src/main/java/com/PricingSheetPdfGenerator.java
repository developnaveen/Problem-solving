package com;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import org.vandeseer.easytable.TableDrawer;
import org.vandeseer.easytable.structure.Table;
import org.vandeseer.easytable.structure.Row;
import org.vandeseer.easytable.structure.cell.ImageCell;
import org.vandeseer.easytable.structure.cell.TextCell;
import org.vandeseer.easytable.settings.HorizontalAlignment;
import org.vandeseer.easytable.settings.VerticalAlignment;

public class PricingSheetPdfGenerator {

    public static void main(String[] args) throws IOException {

        // ===============================
        // Document & Page
        // ===============================
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        // ===============================
        // Fonts (PDFBox 3.x)
        // ===============================
        PDType1Font FONT_NORMAL = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
        PDType1Font FONT_BOLD = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
        PDType1Font FONT_ITALIC = new PDType1Font(Standard14Fonts.FontName.HELVETICA_OBLIQUE);
        PDType1Font FONT_BOLD_ITALIC =
                new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD_OBLIQUE);

        // ===============================
        // Logo
        // ===============================
        PDImageXObject logo = PDImageXObject.createFromFile(
                "F:\\Chech out\\pdf\\src\\main\\resources\\download.png",
                document
        );

        // ===============================
        // Colors
        // ===============================
        Color YELLOW = new Color(255, 255, 0);
        Color PEACH = new Color(255, 220, 180);
        Color BLUE = new Color(121, 179, 241);
        Color LIGHTGREY = new Color(197, 192, 192);

        int size = 10;

        // ===============================
        // Header Table
        // ===============================
        Table headerTable = Table.builder()
                .addColumnsOfWidth(80, 300, 120)
                .borderWidth(1)

                .addRow(Row.builder()
                        .height(40f)
                        .add(ImageCell.builder()
                                .image(logo)
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .borderWidth(0)
                                .build())
                        .add(TextCell.builder()
                                .text("Kotak Mahindra Bank")
                                .font(FONT_BOLD_ITALIC)
                                .fontSize(14)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .borderWidth(0)
                                .textColor(BLUE)
                                .build())
                        .add(TextCell.builder()
                                .text("CIIB")
                                .font(FONT_BOLD_ITALIC)
                                .fontSize(12)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .borderWidth(0)
                                .textColor(BLUE)
                                .build())
                        .build())

                .addRow(Row.builder()
                        .height(30f)
                        .add(TextCell.builder()
                                .text("PRICING SHEET FOR LENDING LINKED TO REPO RATE (Ver 2.41)")
                                .colSpan(3)
                                .font(FONT_BOLD)
                                .fontSize(11)
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .build())
                        .build())
                .build();

        // ===============================
        // General Table
        // ===============================
        Table generalTable = Table.builder()
                .addColumnsOfWidth(30, 160, 310)
                .borderWidth(1)
                .padding(5)

                .addRow(Row.builder()
                        .add(TextCell.builder()
                                .text("G\ne\nn\ne\nr\na\nl")
                                .rowSpan(9)
                                .backgroundColor(new Color(190, 190, 190))
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .font(FONT_BOLD)
                                .fontSize(10)
                                .build())
                        .add(TextCell.builder().text("CUSTOMER NAME :").fontSize(size).build())
                        .add(TextCell.builder().text("").backgroundColor(LIGHTGREY).build())
                        .build())

                .addRow(Row.builder()
                        .add(TextCell.builder().text("CRN :").fontSize(size).build())
                        .add(TextCell.builder().text("").backgroundColor(LIGHTGREY).build())
                        .build())

                .addRow(Row.builder()
                        .add(TextCell.builder().text("MSME :").fontSize(size).build())
                        .add(TextCell.builder().text("").backgroundColor(LIGHTGREY).build())
                        .build())

                .addRow(Row.builder()
                        .add(TextCell.builder().text("DATE :").fontSize(size).build())
                        .add(TextCell.builder().text("11-Nov-25").backgroundColor(YELLOW).build())
                        .build())

                .addRow(Row.builder()
                        .add(TextCell.builder().text("FACILITY :").fontSize(size).build())
                        .add(TextCell.builder().text("WCDL").backgroundColor(YELLOW).build())
                        .build())

                .addRow(Row.builder()
                        .add(TextCell.builder().text("CLIENT EXPOSURE :").fontSize(size).build())
                        .add(TextCell.builder().text("7.5 crs and above").backgroundColor(YELLOW).build())
                        .build())

                .addRow(Row.builder()
                        .add(TextCell.builder().text("SECURITY TYPE :").fontSize(size).build())
                        .add(TextCell.builder().text("Others").backgroundColor(YELLOW).build())
                        .build())

                .addRow(Row.builder()
                        .add(TextCell.builder().text("FACILITY TENOR :").fontSize(size).build())
                        .add(TextCell.builder()
                                .text("> 90 days upto 6 months")
                                .backgroundColor(YELLOW)
                                .build())
                        .build())

                .addRow(Row.builder()
                        .add(TextCell.builder().text("BUSINESS SEGMENT :").fontSize(size).build())
                        .add(TextCell.builder().text("CCG").backgroundColor(YELLOW).build())
                        .build())
                .build();

        // ===============================
        // Repo Table
        // ===============================
        Table repoTable = Table.builder()
                .addColumnsOfWidth(30, 119,117,117,117)
                .borderWidth(1)
                .padding(5)
                .addRow(Row.builder()
                        .add(TextCell.builder().text("").backgroundColor(PEACH).build())
                        .add(TextCell.builder().text("Repo Rate").backgroundColor(PEACH).build())
                        .add(TextCell.builder().text("").backgroundColor(PEACH).build())
                        .add(TextCell.builder()
                                .text("5.50")
                                .backgroundColor(PEACH)
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .build())
                        .add(TextCell.builder().text("").backgroundColor(PEACH).build())
                        .build())
                .build();

        Table spreadTable = Table.builder()
                .addColumnsOfWidth(40, 160, 70, 80, 90, 60)
                .borderWidth(1)
                .padding(5)

                // ===== Row 1: SPREAD =====
                .addRow(Row.builder()
                        // Col 1 (rowSpan)
                        .add(TextCell.builder()
                                .text("A\nd\nd\ni\nt\ni\no\nn\ns")
                                .rowSpan(6)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .backgroundColor(new Color(190,190,190))
                                .build())
                        // Col 2
                        .add(TextCell.builder()
                                .text("Spread for Cost of Funds, ALM spread and operating costs")
                                .build())
                        // Col 3–5
                        .add(TextCell.builder().text("").colSpan(3).build())
                        // Col 6
                        .add(TextCell.builder()
                                .text("2.85")
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .build())
                        .build())

                // ===== Row 2: Credit Risk Premium – PD =====
                .addRow(Row.builder()
                        // Col 2 (rowSpan)
                        .add(TextCell.builder()
                                .text("Credit Risk Premium")
                                .rowSpan(2)
                                .build())
                        // Col 3
                        .add(TextCell.builder().text("PD").build())
                        // Col 4
                        .add(TextCell.builder()
                                .text("0.05%")
                                .backgroundColor(Color.YELLOW)
                                .build())
                        // Col 5–6
                        .add(TextCell.builder().text("").colSpan(2).build())
                        .build())

                // ===== Row 3: Credit Risk Premium – LGD (FIXED) =====
                .addRow(Row.builder()
                        // Col 3
                        .add(TextCell.builder().text("LGD").build())
                        // Col 4
                        .add(TextCell.builder()
                                .text("19.56%")
                                .backgroundColor(Color.YELLOW)
                                .build())
                        // Col 5–6  ✅ MUST use colSpan
                        .add(TextCell.builder()
                                .text("")
                                .colSpan(2)
                                .build())
                        .build())

                // ===== Row 4: Waiver =====
                .addRow(Row.builder()
                        // Col 2 (rowSpan)
                        .add(TextCell.builder()
                                .text("Credit Prem & Waiver / Additions")
                                .rowSpan(2)
                                .build())
                        // Col 3
                        .add(TextCell.builder().text("Waiver").build())
                        // Col 4
                        .add(TextCell.builder()
                                .text("0%")
                                .backgroundColor(Color.YELLOW)
                                .build())
                        // Col 5–6
                        .add(TextCell.builder()
                                .text("Choose Reason")
                                .colSpan(2)
                                .build())
                        .build())

                // ===== Row 5: Waiver Input (FIXED) =====
                .addRow(Row.builder()
                        // Col 3–5
                        .add(TextCell.builder()
                                .text("Please Type in Above Cell")
                                .colSpan(3)
                                .backgroundColor(new Color(255,230,150))
                                .build())
                        // Col 6
                        .add(TextCell.builder()
                                .text("0.009")
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .build())
                        .build())

                // ===== Row 6: Business Strategy =====
                .addRow(Row.builder()
                        // Col 2
                        .add(TextCell.builder().text("Business Strategy").build())
                        // Col 3–5
                        .add(TextCell.builder().text("").colSpan(3).build())
                        // Col 6
                        .add(TextCell.builder()
                                .text("0")
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .build())
                        .build())

                .build();

        Table waiverTable = Table.builder()
                .addColumnsOfWidth(40, 200,260)
                .borderWidth(1)
                .padding(5)

                // Row 1
                .addRow(Row.builder()
                        .add(TextCell.builder()
                                .text("W\na\ni\nv\ne\nr\ns\n/\nR\ne\nd\nu\nc\nt\ni\no\nn\ns")
                                .rowSpan(4)
                                .fontSize(7)          // 🔑 key
                                .padding(2)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .backgroundColor(new Color(190, 190, 190))
                                .build())
                        .add(TextCell.builder()
                                .text("Discount for Tenor Premium")
                                .build())
                        .add(TextCell.builder()
                                .text("0.00")
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .build())
                        .build())

                // Row 2
                .addRow(Row.builder()
                        .add(TextCell.builder().text("Discount for BIB").build())
                        .add(TextCell.builder()
                                .text("0.00")
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .build())
                        .build())

                // Row 3
                .addRow(Row.builder()
                        .add(TextCell.builder().text("Discount for Opex").build())
                        .add(TextCell.builder()
                                .text("1.30")
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .build())
                        .build())

                // Row 4
                .addRow(Row.builder()
                        .add(TextCell.builder().text("Business Strategy").build())
                        .add(TextCell.builder()
                                .text("1.50")
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .build())
                        .build())

                .build();

        Table finalPricingTable = Table.builder()
                .addColumnsOfWidth(40, 180, 120, 80, 80)
                .borderWidth(1)
                .padding(5)

                // ===== Row 1: FINAL PRICING =====
                .addRow(Row.builder()
                        .add(TextCell.builder()
                                .text("") // left empty
                                .backgroundColor(PEACH)
                                .build())
                        .add(TextCell.builder()
                                .text("FINAL PRICING :")
                                .font(FONT_BOLD)
                                .backgroundColor(PEACH)
                                .build())
                        .add(TextCell.builder()
                                .text("")
                                .backgroundColor(PEACH)
                                .build())
                        .add(TextCell.builder()
                                .text("5.56")
                                .font(FONT_BOLD)
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .backgroundColor(PEACH)
                                .build())
                        .add(TextCell.builder()
                                .text("")
                                .backgroundColor(PEACH)
                                .build())
                        .build())

                // ===== Row 2: Repo Rate =====
                .addRow(Row.builder()
                        .add(TextCell.builder()
                                .text("")
                                .rowSpan(2)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .backgroundColor(new Color(190,190,190))
                                .fontSize(7)
                                .build())
                        .add(TextCell.builder()
                                .text("Repo Rate")
                                .backgroundColor(BLUE)
                                .build())
                        .add(TextCell.builder()
                                .text("")
                                .backgroundColor(BLUE)
                                .build())
                        .add(TextCell.builder()
                                .text("5.50")
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .backgroundColor(BLUE)
                                .build())
                        .add(TextCell.builder()
                                .text("")
                                .backgroundColor(BLUE)
                                .build())
                        .build())

                // ===== Row 3: Spread over Repo =====
                .addRow(Row.builder()
                        .add(TextCell.builder()
                                .text("Spread over Repo Rate")
                                .backgroundColor(BLUE)
                                .build())
                        .add(TextCell.builder()
                                .text("")
                                .backgroundColor(BLUE)
                                .build())
                        .add(TextCell.builder()
                                .text("0.06")
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .backgroundColor(BLUE)
                                .build())
                        .add(TextCell.builder()
                                .text("")
                                .backgroundColor(BLUE)
                                .build())
                        .build())

                .build();


        // ===============================
        // Drawing
        // ===============================
        float startX = 40f;
        float startY = 780f;
        float gap = 10f;
        float tableWidth = 500f;

        try (PDPageContentStream cs =
                     new PDPageContentStream(document, page,
                             PDPageContentStream.AppendMode.APPEND, true)) {

            // Header separator line (exact)
            cs.setLineWidth(1f);
            cs.moveTo(startX, startY);
            cs.lineTo(startX + tableWidth, startY);
            cs.stroke();

            // Header table
            TableDrawer.builder()
                    .contentStream(cs)
                    .startX(startX)
                    .startY(startY)
                    .table(headerTable)
                    .build()
                    .draw();

            float generalTableStartY =
                    startY - headerTable.getHeight() - gap;

            TableDrawer.builder()
                    .contentStream(cs)
                    .startX(startX)
                    .startY(generalTableStartY)
                    .table(generalTable)
                    .build()
                    .draw();

            float repoTableStartY =
                    generalTableStartY - generalTable.getHeight() - gap;

            TableDrawer.builder()
                    .contentStream(cs)
                    .startX(startX)
                    .startY(repoTableStartY)
                    .table(repoTable)
                    .build()
                    .draw();

            float spreadTableStartY =
                    repoTableStartY - repoTable.getHeight() - gap;

            TableDrawer.builder()
                    .contentStream(cs)
                    .startX(startX)
                    .startY(spreadTableStartY)
                    .table(spreadTable)
                    .build()
                    .draw();

            float waiverTableStartY =
                    spreadTableStartY - spreadTable.getHeight() - gap;

            TableDrawer.builder()
                    .contentStream(cs)
                    .startX(startX)
                    .startY(waiverTableStartY)
                    .table(waiverTable)
                    .build()
                    .draw();

            float finalPricingTableStartY =
                    waiverTableStartY - waiverTable.getHeight() - gap;

            TableDrawer.builder()
                    .contentStream(cs)
                    .startX(startX)
                    .startY(finalPricingTableStartY)
                    .table(finalPricingTable)
                    .build()
                    .draw();
        }

        // ===============================
        // Save
        // ===============================
        document.save("Pricing.pdf");
        document.close();

        System.out.println("PDF created successfully ✅");
    }
}
