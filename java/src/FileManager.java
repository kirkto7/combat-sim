import java.io.*;

public class FileManager {
    private static final FileManager instance = new FileManager();
    private final FileParser parser = FileParser.getInstance();

    private FileManager(){}
    public static FileManager getInstance() {
        return instance;
    }

    public String importCharacter(String filename) throws IOException, IllegalArgumentException{
        int extensionIndex = filename.indexOf(".");

        //Verify filename includes extension
        if(extensionIndex < 0 ) {
            throw new IOException("Include extension in filename");
        }

        //Verify file is a .json
        String extension = filename.substring(extensionIndex);
        if(!extension.equals(".json")) {
            throw new IOException("File is not desired type (.json)");
        }

        BufferedReader in = new BufferedReader(new FileReader(filename));

        return in.readLine();
    }

    public boolean exportResults() {
        return false;
    }
}
