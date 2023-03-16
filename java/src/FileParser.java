import com.google.gson.*;
import java.util.Map;

public class FileParser {

    private static final Map<Integer, String> ABILITIES =
            Map.of(1, "STR",
                    2, "DEX",
                    3, "CON",
                    4, "INT",
                    5, "WIS",
                    6, "CHA");

    private static final FileParser instance = new FileParser();

    private FileParser(){}
    public static FileParser getInstance() {
        return instance;
    }

    public int getID(String json) {
        return -1;
    }

}
