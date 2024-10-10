package dao;

import java.sql.Connection;
import java.io.IOException;
import java.sql.SQLException;

public class DAO {
    protected Connection connection;

    public DAO() throws SQLException, IOException {
        this.connection = JdbcConnector.getConnection();
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
