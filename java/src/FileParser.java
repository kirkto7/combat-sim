import com.google.gson.*;
import java.util.ArrayList;

/**
 * Singleton object class used to parse character data
 * from a DNDBeyond character json file
 */
public class FileParser {

    private static final FileParser instance = new FileParser();

    private FileParser(){}
    public static FileParser getInstance() {
        return instance;
    }

    /**
     * Returns character ID
     * @param json String containing character data
     * @return character ID in int form
     * @throws IllegalArgumentException If file is formatted incorrectly
     * @throws NullPointerException If file is formatted incorrectly
     */
    public int getID(String json) throws IllegalArgumentException, NullPointerException {
        JsonObject data = JsonParser.parseString(json).getAsJsonObject().get("data").getAsJsonObject();
        return data.get("id").getAsInt();
    }

    /**
     * Internal method to grab "data" section of json data
     * @param json String containing json character data
     * @return JsonObject of data
     */
    private JsonObject getData(String json) {
        return JsonParser.parseString(json).getAsJsonObject().get("data").getAsJsonObject();
    }


    /**
     * Returns total base ability scores
     * @param json String containing character data
     * @return int[] containing all 6 base stats, in typical order
     * @throws IllegalArgumentException If file is formatted incorrectly
     * @throws NullPointerException If file is formatted incorrectly
     */
    public int[] getAbilityScores(String json) throws IllegalArgumentException, NullPointerException{
        JsonObject data = getData(json);
        int[] scores = {0,0,0,0,0,0};

        //Add modifiers to scores
        //This is done first so that they get overridden if the score is overridden

        //Get all objects related to scores
        ArrayList<JsonObject> modifiers = getObjects(data, "entityTypeId", "1472902489");
        for(JsonObject o : modifiers) {
            String type = o.get("type").isJsonNull() ? "null" : o.get("type").getAsString();
            //For all Json objects labeled as a bonus
            if(type.equals("bonus")) {
                //Add the value of the bonus to the appropriate value
                scores[o.get("entityId").getAsInt() - 1] += o.get("value").getAsInt();
            }
        }

        //Get base ability scores
        JsonArray stats = data.get("stats").getAsJsonArray();
        JsonArray bonusStats = data.get("bonusStats").getAsJsonArray();;
        JsonArray overrides = data.get("overrideStats").getAsJsonArray();
        for(int i = 0; i < scores.length; i++) {
            int rawStat = stats.get(i).getAsJsonObject().get("value").getAsInt();
            int bonusStat = bonusStats.get(i).getAsJsonObject().get("value").isJsonNull() ? 0 : bonusStats.get(i).getAsJsonObject().get("value").getAsInt();
            int override = overrides.get(i).getAsJsonObject().get("value").isJsonNull() ? 0 : overrides.get(i).getAsJsonObject().get("value").getAsInt();
            scores[i] = (override > 0) ? override : rawStat + bonusStat + scores[i];
        }
        return scores;
    }

    /**
     * getObjects() returns every JsonObject within a JsonObject containing a
     * specified key, value pair
     * @param obj JsonObject to search within
     * @param key Key
     * @param val Value
     * @return an Array List of JsonObjects containing the key value pair
     */
    private ArrayList<JsonObject> getObjects(JsonObject obj, String key, String val) {
        //ArrayList containing list of JsonObjects containing the key val pair
        ArrayList<JsonObject> objects = new ArrayList<>();

        //For every Json Element in the obj
        for(String s : obj.keySet()) {

            //Grab value of key
            JsonElement sVal = obj.get(s);

            //if value is a primitive
            if(sVal.isJsonPrimitive()) {
                //If key value pair match up with parameters, add the current obj to the list
                if(s.equals(key) && sVal.getAsString().equals(val)) {
                    objects.add(obj);
                }
            //If the key is another json object, search within the object for the key value pair
            }else if (obj.get(s).isJsonObject()) {
                objects.addAll(getObjects(obj.get(s).getAsJsonObject(), key, val));
            //If the key is an array, search every json object in the array
            }else if(obj.get(s).isJsonArray()) {
                JsonArray a = obj.get(s).getAsJsonArray();
                for(int i = 0; i < a.size(); i++) {
                    if(a.get(i).isJsonObject()) {
                        objects.addAll(getObjects(a.get(i).getAsJsonObject(), key, val));
                    }
                }
            }
        }
        return objects;
    }
}
