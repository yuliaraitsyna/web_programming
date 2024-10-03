package dao;

import entity.Client;
import entity.TechnicalTask;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicalTaskDao {
    private static final String INSERT_TASK_SQL = "INSERT INTO technical_tasks (description, client_name, client_surname) VALUES (?, ?, ?)";
    private static final String SELECT_TASK_BY_ID = "SELECT * FROM technical_tasks WHERE id = ?";
    private static final String SELECT_ALL_TASKS = "SELECT * FROM technical_tasks";

    private Connection connection;

    public TechnicalTaskDao(Connection connection) {
        this.connection = connection;
    }

    public void addTechnicalTask(TechnicalTask task) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_SQL)) {
            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setString(2, task.getClient().getName());
            preparedStatement.setString(3, task.getClient().getSurname());
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
                String name = resultSet.getString("client_name");
                String surname = resultSet.getString("client_surname");
                Client client = new Client(name, surname);
                task = new TechnicalTask(description, client);
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
                String name = resultSet.getString("client_name");
                String surname = resultSet.getString("client_surname");
                Client client = new Client(name, surname);
                tasks.add(new TechnicalTask(description, client));
            }
        }
        return tasks;
    }
}
