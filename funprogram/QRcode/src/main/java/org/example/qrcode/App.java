package org.example.qrcode;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== QR Code Utility ===");
        System.out.println("1. Generate QR Code");
        System.out.println("2. Scan QR Code");
        System.out.print("Choose an option (1 or 2): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter text or URL to encode: ");
                    String text = scanner.nextLine();

                    System.out.print("Enter output file name (example: myqr.png): ");
                    String fileName = scanner.nextLine();

                    QRGenerate.generate(text, fileName);
                    System.out.println("QR Code generated successfully: " + fileName);
                    break;

                case 2:
                    System.out.print("Enter QR image file name to scan: ");
                    String inputFile = scanner.nextLine();

                    String result = QRScan.scan(inputFile);
                    System.out.println("Decoded text: " + result);
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
