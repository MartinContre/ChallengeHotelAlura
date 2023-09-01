package utilities.json;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for managing JSON data files and their associated JsonReader instances.
 * This class provides a convenient way to access JsonReader instances for different JSON data files.
 */
public class JsonReaderUtils {
    private static final String CONFIG_DATA_FILE = "src/main/resources/config.json";

    private static final Map<String, JsonReader> dataFiles = new HashMap<>();

    static {
        dataFiles.put(CONFIG_DATA_FILE, new JsonReader(CONFIG_DATA_FILE));
    }

    /**
     * Retrieves the JsonReader instance for the configuration data file.
     *
     * @return The JsonReader instance for the configuration data file.
     */
    public static JsonReader getConfigDataFile() {
        return dataFiles.get(CONFIG_DATA_FILE);
    }
}
