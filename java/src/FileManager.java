
import java.io.*;
import java.util.*;
import com.google.gson.*;

public class FileManager {
    private static final FileManager instance = new FileManager();

    private static final Map<Integer, String> ABILITIES = Map.of(1, "STR", 2, "DEX", 3, "CON", 4, "INT", 5, "WIS", 6, "CHA");

    private static JsonObject data;
    private FileManager(){}
    public static FileManager getInstance() {
        return instance;
    }

    public void importFile(String filename) {
        try(BufferedReader in = new BufferedReader(new FileReader(filename))) {

            //Puts everything into a JsonObject
            data = JsonParser.parseString(in.readLine()).getAsJsonObject().get("data").getAsJsonObject();

            //Parses stats
            JsonArray stats = data.get("stats").getAsJsonArray();


        } catch (IOException e) {
            System.out.println("File IO Exception");
        }
    }

    private static void parseMods() {
        JsonObject modifiers = data.get("modifiers").getAsJsonObject();
        System.out.println(modifiers.keySet());
        JsonArray raceMods = modifiers.get("race").getAsJsonArray();
//        JsonArray classMods = modifiers.get("class").getAsJsonArray();
        for (int i = 0; i < raceMods.size(); i++) {
            System.out.println(raceMods.get(i));
        }
    }

    private JsonObject[] getObjects(JsonObject obj, String key, String val) {
        return null;
    }

}
