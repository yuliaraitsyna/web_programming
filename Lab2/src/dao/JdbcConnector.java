package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class JdbcConnector {
    private static final String PROPERTIES_FILE = "database.properties";

    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();

        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + PROPERTIES_FILE);
            }
            properties.load(input);
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }
}
