package dao;

import entity.Client;
import entity.TechnicalTask;
import factory.BaseFactory;
import factory.Factory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class TechnicalTaskDao extends DAO {
    private static final String INSERT_TASK_SQL = "INSERT INTO TechnicalTask (description, client_id) VALUES (?, ?)";
    private static final String SELECT_TASK_BY_ID = "SELECT tt.description, c.name, c.surname " +
            "FROM TechnicalTask tt " +
            "JOIN Client c ON tt.client_id = c.id " +
            "WHERE tt.id = ?";
    private static final String SELECT_REQUIRED_STAFF_BY_TASK_ID = "SELECT role, amount FROM RequiredStaff WHERE tech_task_id = ?";
    private static final String SELECT_ALL_TASKS = "SELECT tt.id, tt.description, c.name, c.surname " +
            "FROM TechnicalTask tt " +
            "JOIN Client c ON tt.client_id = c.id";
    private static final String UPDATE_TASK_SQL = "UPDATE TechnicalTask SET description = ?, client_id = ? WHERE id = ?";
    private static final String DELETE_TASK_SQL = "DELETE FROM TechnicalTask WHERE id = ?";

    private BaseFactory factory;

    public TechnicalTaskDao() throws SQLException, IOException {
        super();
        this.factory = new Factory();
    }

    public void addTechnicalTask(TechnicalTask task, int clientId) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_SQL)) {
            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setInt(2, clientId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public TechnicalTask getTechnicalTaskById(int id) throws SQLException {
        TechnicalTask task = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String description = resultSet.getString("description");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Client client = factory.createClient(name, surname);
                task = factory.createTechnicalTask(description, client);
                loadRequiredStaff(task, id);
            }
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return task;
    }

    public List<TechnicalTask> getAllTechnicalTasks() throws SQLException {
        List<TechnicalTask> tasks = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Client client = factory.createClient(name, surname);
                TechnicalTask task = factory.createTechnicalTask(description, client);
                loadRequiredStaff(task, id);
                tasks.add(task);
            }
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return tasks;
    }

    private void loadRequiredStaff(TechnicalTask task, int techTaskId) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REQUIRED_STAFF_BY_TASK_ID)) {
            preparedStatement.setInt(1, techTaskId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String role = resultSet.getString("role");
                int amount = resultSet.getInt("amount");
                task.addRequiredStaff(role, amount);
            }
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void updateTechnicalTask(int id, TechnicalTask task) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASK_SQL)) {
            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void deleteTechnicalTask(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TASK_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
