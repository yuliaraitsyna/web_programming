package dao;

import entity.Client;
import entity.TechnicalTask;
import factory.BaseFactory;
import factory.Factory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicalTaskDao {
    private static final String INSERT_TASK_SQL = "INSERT INTO TechnicalTask (description, client_id) VALUES (?, ?)";
    private static final String SELECT_TASK_BY_ID = "SELECT tt.description, c.name, c.surname " +
            "FROM TechnicalTask tt " +
            "JOIN Client c ON tt.client_id = c.id " +
            "WHERE tt.id = ?";
    private static final String SELECT_ALL_TASKS = "SELECT tt.description, c.name, c.surname " +
            "FROM TechnicalTask tt " +
            "JOIN Client c ON tt.client_id = c.id";

    private Connection connection;
    private BaseFactory factory;

    public TechnicalTaskDao(Connection connection) {
        this.connection = connection;
        this.factory = new Factory();
    }

    public void addTechnicalTask(TechnicalTask task, int clientId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_SQL)) {
            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setInt(2, clientId);
            preparedStatement.executeUpdate();
        }
    }

    public TechnicalTask getTechnicalTaskById(int id) throws SQLException {
        TechnicalTask task = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String description = resultSet.getString("description");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Client client = factory.createClient(name, surname);
                task = factory.createTechnicalTask(description, client);
            }
        }
        return task;
    }

    public List<TechnicalTask> getAllTechnicalTasks() throws SQLException {
        List<TechnicalTask> tasks = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String description = resultSet.getString("description");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Client client = factory.createClient(name, surname);
                tasks.add(factory.createTechnicalTask(description, client));
            }
        }
        return tasks;
    }
}
