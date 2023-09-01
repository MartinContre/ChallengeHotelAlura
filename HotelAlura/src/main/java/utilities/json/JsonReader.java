package utilities.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.exception.KeyNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class for reading JSON files and retrieving values.
 * This class allows reading JSON files and extracting values from them using keys.
 */
public class JsonReader {
    private static final Logger LOGGER = LogManager.getLogger(JsonReader.class);

    private final JsonObject jsonObject;

    /**
     * Creates a new instance of the JsonReader class.
     *
     * @param filePath The path to the JSON file.
     * @throws RuntimeException if the JSON file cannot be loaded.
     */
    public JsonReader(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                throw new FileNotFoundException("The JSON file does not exist: " + filePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        try (FileReader reader = new FileReader(filePath)) {
            Object obj = JsonParser.parseReader(reader);
            jsonObject = (JsonObject) obj;
        } catch (IOException e) {
            LOGGER.error(String.format("An exception occurred while reading the JSON file. Exception: %s", e));
            throw new RuntimeException("Error reading JSON file: " + e.getMessage());
        }
    }

    /**
     * Retrieves an array of values associated with the given key from the JSON object.
     *
     * @param key The key for retrieving the values.
     * @return An array of values.
     * @throws IllegalStateException if the JSON file has not been loaded.
     */
    public String[] getValues(String key) {
        try {
            if (jsonObject == null) {
                throw new IllegalStateException("The JSON file has not been loaded.");
            }

            JsonElement element = jsonObject.get(key);

            if (element == null) {
                throw new KeyNotFoundException("Key not found in the JSON file: " + key);
            }

            if (element.isJsonArray()) {
                JsonArray jsonArray = element.getAsJsonArray();
                String[] values = new String[jsonArray.size()];

                for (int i = 0; i < jsonArray.size(); i++) {
                    values[i] = jsonArray.get(i).getAsString();
                }

                return values;
            }

            throw new KeyNotFoundException("The value of the key is not an array in the JSON file: " + key + key);
        } catch (IllegalStateException | KeyNotFoundException e) {
            LOGGER.error(String.format("An exception occurred while reading the JSON file. Exception: %s", e));
            return new String[0];
        }
    }

    /**
     * Retrieves the value associated with the given key from the JSON object.
     *
     * @param key The key for retrieving the value.
     * @return The value as a string.
     * @throws RuntimeException if the key is not found in the JSON object.
     */
    public String getValue(String key) {
        JsonElement element = jsonObject.get(key);
        if (element == null) {
            try {
                throw new KeyNotFoundException("Key not found in the JSON file: " + key + key);
            } catch (KeyNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return element.getAsString();
    }
}
