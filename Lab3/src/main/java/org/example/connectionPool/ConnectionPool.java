package org.example.connectionPool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool {
    private static final int INITIAL_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 20;
    private static final int TIMEOUT = 30;

    private static final String PROPERTIES_FILE = "database.properties";
    //private static final Logger logger = LoggerManager.getErrorLogger();

    private final String jdbcUrl;
    private final String jdbcUser;
    private final String jdbcPassword;

    private final BlockingQueue<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();

    public ConnectionPool() throws SQLException, IOException {
        Properties properties = new Properties();

        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + PROPERTIES_FILE);
            }
            properties.load(input);
        }
        catch(IOException e) {
            //logger.log(Level.SEVERE, "Error loading properties file: " + e.getMessage(), e);
        }

        this.jdbcUrl = properties.getProperty("db.url");
        this.jdbcUser = properties.getProperty("db.user");
        this.jdbcPassword = properties.getProperty("db.password");

        connectionPool = new LinkedBlockingQueue<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            connectionPool.add(createConnection());
        }
    }

    private Connection createConnection() throws SQLException {
        try {
            return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        }
        catch(SQLException e) {
            //logger.log(Level.SEVERE, "Error creating database connections.", e.getMessage());
            throw new RuntimeException("Error creating database connections.");
        }
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.poll(TIMEOUT, TimeUnit.SECONDS);
            if (connection == null && usedConnections.size() < MAX_POOL_SIZE) {
                connection = createConnection();
            }
            if (connection != null) {
                usedConnections.add(connection);
            } else {
                throw new SQLException("Number of connections in ConnectionPool has reached maximum");
            }
        } catch (InterruptedException e) {
            throw new SQLException("Error getting connection from pool", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (usedConnections.remove(connection)) {
            try {
                if (!connection.isClosed()) {
                    connectionPool.add(connection);
                } else {
                    connectionPool.add(createConnection());
                }
            } catch (SQLException e) {
                //logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    public void shutdown() throws SQLException {
        for (Connection connection : connectionPool) {
            connection.close();
        }
        for (Connection connection : usedConnections) {
            connection.close();
        }
    }
}
