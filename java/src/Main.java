import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = FileManager.getInstance();

        Scanner in = new Scanner(System.in);
        System.out.println("Enter Filename: ");

        String fileName = "";
        String data = "";

        boolean isValidFile = true;
        do {
            fileName = in.nextLine();
            if(fileName.equalsIgnoreCase("q")) {
                return;
            }
            try {
                data = fileManager.importCharacter(fileName);
                isValidFile = true;
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                isValidFile = false;
                System.out.println("Enter new filename, or \"q\" to quit: ");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                isValidFile = false;
                System.out.println("Enter new filename, or \"q\" to quit: ");
            }
        } while (!isValidFile);
    }

}

