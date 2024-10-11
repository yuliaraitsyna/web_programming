package dao;

import connectionPool.ConnectionPool;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {
    protected ConnectionPool connectionPool;

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
