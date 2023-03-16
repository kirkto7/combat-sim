import java.io.*;

public class FileManager {
    private static final FileManager instance = new FileManager();

    private FileManager(){}
    public static FileManager getInstance() {
        return instance;
    }

    public String importCharacter(String filename) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader(filename));
        return(in.readLine());
    }

    public boolean exportResults() {
        return false;
    }
}
