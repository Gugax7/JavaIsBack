package challenge2;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {

        String filename = "output.txt";
        String content = "Hello, Java I/O!";
        // Write to the file
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            fos.write(bytes);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
        // Read from the file
        try (FileInputStream fis = new FileInputStream(filename)) {
            int ch;
            System.out.print("File content: ");
            while((ch = fis.read()) != -1){
                System.out.println((char)ch);
            }
            System.out.println(); // For new line after content
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}



