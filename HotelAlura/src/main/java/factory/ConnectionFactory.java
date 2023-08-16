package factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.JsonReader;
import utilities.JsonReaderUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);
    private static Connection connection = null;
    private static final String URL = "/URL";
    private static final String USER = "/USER";
    private static final String PASSWORD = "/PASSWORD";

    private static final JsonReader CONFIG_DATA = JsonReaderUtils.getConfigDataFile();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = CONFIG_DATA.getValue(URL);
                String user = CONFIG_DATA.getValue(USER);
                String password = CONFIG_DATA.getValue(PASSWORD);
                connection = DriverManager.getConnection(url,user, password);
            } catch (SQLException e) {
                LOGGER.error("Error while opening the connection: " + e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (SQLException e) {
            LOGGER.error("Error while closing connection: " + e.getMessage());
        }
    }
}
