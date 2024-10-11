package dao;

import entity.Client;
import factory.BaseFactory;
import factory.Factory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ClientDao extends DAO{
    private static final String INSERT_CLIENT_SQL = "INSERT INTO Client (name, surname) VALUES (?, ?)";
    private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM Client WHERE id = ?";
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM Client";
    private static final String UPDATE_CLIENT_SQL = "UPDATE Client SET name = ?, surname = ? WHERE id = ?";
    private static final String DELETE_CLIENT_SQL = "DELETE FROM Client WHERE id = ?";

    private BaseFactory factory;

    public ClientDao() throws SQLException, IOException {
        super();
        this.factory = new Factory();
    }

    public void addClient(Client client) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void updateClient(int id, Client client) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_SQL)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void deleteClient(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public Client getClientById(int id) throws SQLException {
        Client client = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                client = factory.createClient(name, surname);
            }
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return client;
    }

    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                clients.add(factory.createClient(name, surname));
            }
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return clients;
    }
}
