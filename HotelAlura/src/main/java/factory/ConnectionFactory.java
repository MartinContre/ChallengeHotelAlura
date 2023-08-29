package factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.json.JsonReader;
import utilities.json.JsonReaderUtils;
import views.PrincipalMenu;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);
    private static Connection connection = null;
    private static final String URL = "URL";
    private static final String USER = "USER";
    private static final String PASSWORD = "PASSWORD";

    private static final JsonReader CONFIG_DATA = JsonReaderUtils.getConfigDataFile();

    private ConnectionFactory() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = CONFIG_DATA.getValue(URL);
                String user = CONFIG_DATA.getValue(USER);
                String password = CONFIG_DATA.getValue(PASSWORD);
                connection = DriverManager.getConnection(url,user, password);
            } catch (SQLException e) {
                LOGGER.error("Error while opening the connection: " + e.getMessage());
                JOptionPane.showMessageDialog(
                        null,
                        "Error while opening database.",
                        "Connexion error",
                        JOptionPane.ERROR_MESSAGE
                );
                PrincipalMenu menuPrincipal = new PrincipalMenu();
                menuPrincipal.setVisible(true);
                throw new RuntimeException(e.getMessage());
            }
        }
        return connection;
    }

}
