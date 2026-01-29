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
import org.vandeseer.easytable.structure.cell.VerticalTextCell;

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
                "src/main/resources/download.png",
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
                .addColumnsOfWidth(44, 146, 310)
                .borderWidth(1)
                .padding(5)

                // FIRST ROW (contains VerticalTextCell)
                .addRow(Row.builder()
                        .add(VerticalTextCell.builder()
                                .text("General")
                                .rowSpan(9)
                                .backgroundColor(LIGHTGREY)
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .font(FONT_BOLD)
                                .fontSize(10)
                                .build())
                        .add(TextCell.builder()
                                .text("CUSTOMER NAME :")
                                .fontSize(size)
                                .build())
                        .add(TextCell.builder()
                                .text("")
                                .backgroundColor(LIGHTGREY)
                                .build())
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
                .addColumnsOfWidth(44, 95, 48, 90, 43, 62, 118)
                .borderWidth(1)
                .padding(5)
                .addRow(Row.builder()
                        .add(TextCell.builder().text("").backgroundColor(PEACH).build())
                        .add(TextCell.builder().text("Repo Rate").backgroundColor(PEACH).build())
                        .add(TextCell.builder().text("").colSpan(2).backgroundColor(PEACH).build())
                        .add(TextCell.builder()
                                .text("5.50")
                                .backgroundColor(PEACH)
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .build())
                        .add(TextCell.builder().text("").colSpan(2).backgroundColor(PEACH).build())
                        .build())
                .build();

        Table spreadTable = Table.builder()
                .addColumnsOfWidth(44, 95, 48, 90, 43, 62, 118)
                .borderWidth(1)
                .padding(5)

                // ================= ROW 1 : SPREAD =================
                .addRow(Row.builder()
                        .add(TextCell.builder().text("SPREAD")
                                .backgroundColor(LIGHTGREY)
                                .fontSize(8)
                                .build()) // C1
                        .add(TextCell.builder()
                                .text("Spread for Cost of Funds, ALM spread and operating costs")
                                .colSpan(3) // C2–C4
                                .build())
                        .add(TextCell.builder()
                                .text("2.85")
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .build()) // C5
                        .add(TextCell.builder().text("").colSpan(2).build()) // C6
                        .build())

                // ================= ROW 2 : ADDITIONS / PD =================
                .addRow(Row.builder()
                        .add(VerticalTextCell.builder()
                                .text("Additions")
                                .rowSpan(6) // spans rows 2–6 ONLY
                                .backgroundColor(LIGHTGREY)
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .font(FONT_BOLD)
                                .fontSize(10)
                                .build()) // C1

                        .add(TextCell.builder()
                                .text("Credit Risk Premium")
                                .rowSpan(2)
                                .build()) // C2

                        .add(TextCell.builder().text("PD").build()) // C3
                        .add(TextCell.builder()
                                .text("0.05%")
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .backgroundColor(YELLOW)
                                .build()) // C4
                        .add(TextCell.builder().text("0.010").rowSpan(2)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .build()) // C5
                        .add(TextCell.builder().text("").colSpan(2).build()) // C6–C7
                        .build())

                // ================= ROW 3 : LGD =================
                .addRow(Row.builder()
                        .add(TextCell.builder().text("LGD").build()) // C3
                        .add(TextCell.builder()
                                .text("19.56%")
                                .backgroundColor(YELLOW)
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .build()) // C4
                        .add(TextCell.builder().text("").colSpan(2).build()) // C5–C7
                        .build())

                // ================= ROW 4 : WAIVER =================
                .addRow(Row.builder()
                        .add(TextCell.builder()
                                .text("Credit Prem & Waiver / Additions")
                                .rowSpan(2)
                                .build()) // C2
                        .add(TextCell.builder()
                                .text("Waiver")
                                .rowSpan(2)
                                .backgroundColor(YELLOW)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .build()) // C3
                        .add(TextCell.builder()
                                .text("0%")
                                .backgroundColor(YELLOW)
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .build()) // C4
                        .add(TextCell.builder()
                                .text("0.001")
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .backgroundColor(YELLOW)
                                .build()) // C5
                        .add(TextCell.builder()
                                .text("Choose Reason")
                                .build())
                        .add(TextCell.builder()
                                .text("Control Over Cash Flows")
                                .build())
                        .build())

                // ================= ROW 5 : WAIVER INPUT =================
                .addRow(Row.builder()
                        .add(TextCell.builder()
                                .text("Please Type in Above Cell")
                                .build())
                        .add(TextCell.builder()
                                .text("0.009")
                                .backgroundColor(PEACH)
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .build()) // C6
                        .add(TextCell.builder()
                                .text("")
                                .build()) // C7
                        .add(TextCell.builder()
                                .text("")
                                .build())
                        .build())

                // ================= ROW 6 : EMBEDDED OPTIONS =================
                .addRow(Row.builder()
                        .add(TextCell.builder().text("Business Strategy")
                                .verticalAlignment(VerticalAlignment.BOTTOM)
                                .colSpan(3).rowSpan(2).build()) // C2
                        .add(TextCell.builder()
                                .text("0").rowSpan(2)
                                .horizontalAlignment(HorizontalAlignment.RIGHT)
                                .verticalAlignment(VerticalAlignment.BOTTOM)
                                .build())
                        .add(TextCell.builder()
                                .text("Embedded options in the loan product")
                                .backgroundColor(YELLOW)
                                .colSpan(2)
                                .build()) // C6–C7
                        .build())

                // ================= ROW 7 : BUSINESS STRATEGY (OUTSIDE Additions) =================
                .addRow(Row.builder()// C5
                        .add(TextCell.builder().text("").build()) // C6
                        .add(TextCell.builder().text("").build()) // C7
                        .build())

                .build();


        Table waiverTable = Table.builder()
                .addColumnsOfWidth(44, 230,226)
                .borderWidth(1)
                .padding(5)

                // Row 1
                .addRow(Row.builder()
                        .add(VerticalTextCell.builder()
                                .text("Waivers / Reductions")
                                .rowSpan(4)
                                .fontSize(7)
                                .padding(2)
                                .backgroundColor(LIGHTGREY)
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .font(FONT_BOLD)
                                .fontSize(10)
                                .build())
                        .add(TextCell.builder()
                                .text("Discount for Tenor Premium")
                                .build())
                        .add(TextCell.builder()
                                .text("0.00")
                                .horizontalAlignment(HorizontalAlignment.LEFT)
                                .build())
                        .build())

                // Row 2
                .addRow(Row.builder()
                        .add(TextCell.builder().text("Discount for BIB").build())
                        .add(TextCell.builder()
                                .text("0.00")
                                .horizontalAlignment(HorizontalAlignment.LEFT)
                                .build())
                        .build())

                // Row 3
                .addRow(Row.builder()
                        .add(TextCell.builder().text("Discount for Opex").build())
                        .add(TextCell.builder()
                                .text("1.30")
                                .horizontalAlignment(HorizontalAlignment.LEFT)
                                .build())
                        .build())

                // Row 4
                .addRow(Row.builder()
                        .add(TextCell.builder().text("Business Strategy").build())
                        .add(TextCell.builder()
                                .text("1.50")
                                .horizontalAlignment(HorizontalAlignment.LEFT)
                                .build())
                        .build())

                .build();

        Table finalPricingTable = Table.builder()
                .addColumnsOfWidth(44, 230, 226)
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
                                .text("5.56")
                                .font(FONT_BOLD)
                                .horizontalAlignment(HorizontalAlignment.LEFT)
                                .backgroundColor(PEACH)
                                .build())
                        .build())

                // ===== Row 2: Repo Rate =====
                .addRow(Row.builder()
                        .add(VerticalTextCell.builder()
                                .text("For Ops Team Use Only")
                                .rowSpan(2)
                                .backgroundColor(LIGHTGREY)
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .verticalAlignment(VerticalAlignment.MIDDLE)
                                .font(FONT_BOLD)
                                .fontSize(10)
                                .build())
                        .add(TextCell.builder()
                                .text("Repo Rate")
                                .backgroundColor(BLUE)
                                .build())

                        .add(TextCell.builder()
                                .text("5.50")
                                .horizontalAlignment(HorizontalAlignment.LEFT)
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
                                .text("0.06")
                                .horizontalAlignment(HorizontalAlignment.LEFT)
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
