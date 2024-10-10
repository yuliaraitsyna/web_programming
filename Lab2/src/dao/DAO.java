package dao;

import connectionPool.ConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
    protected Connection connection = null;
    protected Statement statement = null;
    protected ConnectionPool connectionPool;

    public DAO() throws SQLException, IOException {
        this.connectionPool = new ConnectionPool();
        this.connection = connectionPool.getConnection();
        this.statement = connection.createStatement();
    }

    public void close() {
        closeStatement();
        releaseConnection();
    }

    private void closeStatement() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void releaseConnection() {
        if (connection != null) {
            connectionPool.releaseConnection(connection);
        }
    }
}
