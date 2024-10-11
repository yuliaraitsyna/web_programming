package dao;

import connectionPool.ConnectionPool;
import loggerManager.LoggerManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public abstract class DAO {
    protected ConnectionPool connectionPool;
    protected static final Logger logger = LoggerManager.getErrorLogger();

    public DAO() throws SQLException, IOException {
        this.connectionPool = new ConnectionPool();
    }

    protected Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    protected void releaseConnection(Connection connection) {
        connectionPool.releaseConnection(connection);
    }

    public void close() {
        try {
            connectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
