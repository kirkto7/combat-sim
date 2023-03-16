import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        FileManager test = FileManager.getInstance();
        try {
            test.importCharacter("rook.json");
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

}

