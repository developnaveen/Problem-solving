import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteExample {
    public static void main(String[] args) {

        String filePath = "output.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write("Hello using BufferedWriter!");
            writer.newLine();
            writer.write("This writes text efficiently.");
            writer.newLine();

            System.out.println("Data written successfully!");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
