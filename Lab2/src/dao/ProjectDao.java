package dao;

import entity.Project;

import javax.management.InvalidAttributeValueException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {
    private static final String INSERT_PROJECT_SQL = "INSERT INTO projects (title, date, cost) VALUES (?, ?, ?)";
    private static final String SELECT_PROJECT_BY_ID = "SELECT * FROM projects WHERE id = ?";
    private static final String SELECT_ALL_PROJECTS = "SELECT * FROM projects";

    private Connection connection;

    public ProjectDao(Connection connection) {
        this.connection = connection;
    }

    public void addProject(Project project) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT_SQL)) {
            preparedStatement.setString(1, project.getTitle());
            preparedStatement.setDate(2, new java.sql.Date(project.getDate().getTime()));
            preparedStatement.setDouble(3, project.getCost());
            preparedStatement.executeUpdate();
        }
    }

    public Project getProjectById(int id) throws SQLException {
        Project project = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                Date date = resultSet.getDate("date");
                double cost = resultSet.getDouble("cost");
                project = new Project(title, date);
                project.setCost(cost);
            }
        }
        return project;
    }

    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROJECTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                Date date = resultSet.getDate("date");
                double cost = resultSet.getDouble("cost");
                projects.add(new Project(title, date));
            }
        }
        return projects;
    }
}
