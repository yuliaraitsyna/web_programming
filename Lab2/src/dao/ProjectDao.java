package dao;

import entity.Project;
import factory.BaseFactory;
import factory.Factory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ProjectDao extends DAO {
    private static final String INSERT_PROJECT_SQL = "INSERT INTO Project (title, date, cost) VALUES (?, ?, ?)";
    private static final String SELECT_PROJECT_BY_ID = "SELECT * FROM Project WHERE id = ?";
    private static final String SELECT_ALL_PROJECTS = "SELECT * FROM Project";
    private static final String UPDATE_PROJECT_TASK_SQL = "UPDATE Project SET tech_task_id = ? WHERE id = ?";
    private static final String UPDATE_PROJECT_SQL = "UPDATE Project SET title = ?, date = ?, cost = ?, tech_task_id = ? WHERE id = ?";
    private static final String DELETE_PROJECT_SQL = "DELETE FROM Project WHERE id = ?";
    ;
    private BaseFactory factory;
    private TechnicalTaskDao technicalTaskDao;

    public ProjectDao() throws SQLException, IOException {
        super();
        this.factory = new Factory();
        this.technicalTaskDao = new TechnicalTaskDao(); // Используем TechnicalTaskDao
    }

    public void addProject(Project project) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT_SQL)) {
            preparedStatement.setString(1, project.getTitle());
            preparedStatement.setDate(2, new java.sql.Date(project.getDate().getTime()));
            preparedStatement.setDouble(3, project.getCost());
            preparedStatement.setNull(4, Types.INTEGER);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void updateProject(Project project) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROJECT_SQL)) {
            preparedStatement.setString(1, project.getTitle());
            preparedStatement.setDate(2, new java.sql.Date(project.getDate().getTime()));
            preparedStatement.setDouble(3, project.getCost());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public Project getProjectById(int id) throws SQLException {
        Project project = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                Date date = resultSet.getDate("date");
                double cost = resultSet.getDouble("cost");
                Integer techTaskId = resultSet.getInt("tech_task_id");

                project = factory.createProject(title, date);
                project.setCost(cost);

                if (techTaskId != null && techTaskId > 0) {
                    project.setAssignedTask(technicalTaskDao.getTechnicalTaskById(techTaskId));
                }
            }
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return project;
    }

    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROJECTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                Date date = resultSet.getDate("date");
                double cost = resultSet.getDouble("cost");
                Integer techTaskId = resultSet.getInt("tech_task_id");
                Project project = factory.createProject(title, date);
                project.setCost(cost);

                if (techTaskId != null && techTaskId > 0) {
                    project.setAssignedTask(technicalTaskDao.getTechnicalTaskById(techTaskId));
                }

                projects.add(project);
            }
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return projects;
    }

    public void assignTaskToProject(int projectId, int taskId) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROJECT_TASK_SQL)) {
            preparedStatement.setInt(1, taskId);
            preparedStatement.setInt(2, projectId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void deleteProject(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROJECT_SQL)) {
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
