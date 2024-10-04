package dao;

import entity.Client;
import factory.BaseFactory;
import factory.Factory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    private static final String INSERT_CLIENT_SQL = "INSERT INTO Client (name, surname) VALUES (?, ?)";
    private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM Client WHERE id = ?";
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM Client";

    private Connection connection;
    private BaseFactory factory;

    public ClientDao(Connection connection) {
        this.connection = connection;
        this.factory = new Factory();
    }

    public void addClient(Client client) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.executeUpdate();
        }
    }

    public Client getClientById(int id) throws SQLException {
        Client client = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                client = factory.createClient(name, surname);
            }
        }
        return client;
    }

    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                clients.add(factory.createClient(name, surname));
            }
        }
        return clients;
    }
}
