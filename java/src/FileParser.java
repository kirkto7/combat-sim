import com.google.gson.*;
import java.util.Map;
import java.util.Set;

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

    public int getID(String json) throws IllegalArgumentException, NullPointerException {
        JsonObject data = JsonParser.parseString(json).getAsJsonObject().get("data").getAsJsonObject();
        return data.get("id").getAsInt();
    }

    private JsonObject getData(String json) {
        return JsonParser.parseString(json).getAsJsonObject().get("data").getAsJsonObject();
    }

    public int[] getAbilityScores(String json) {
        int[] scores = new int[6];
        //Get base ability scores first
        JsonArray stats = getData(json).get("stats").getAsJsonArray();
        JsonArray bonusStats = getData(json).get("bonusStats").getAsJsonArray();;
        JsonArray overrides = getData(json).get("overrideStats").getAsJsonArray();
        for(int i = 0; i < scores.length; i++) {
            int rawStat = stats.get(i).getAsJsonObject().get("value").getAsInt();
            int bonusStat = bonusStats.get(i).getAsJsonObject().get("value").isJsonNull() ? 0 : bonusStats.get(i).getAsJsonObject().get("value").getAsInt();
            int override = overrides.get(i).getAsJsonObject().get("value").isJsonNull() ? 0 : overrides.get(i).getAsJsonObject().get("value").getAsInt();
            scores[i] = (override > 0) ? override : rawStat + bonusStat;
        }

        //Add modifiers to scores

        return scores;
    }
}
