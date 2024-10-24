package by.bsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import by.bsu.client.ConsoleClient;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Lab3PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            ConsoleClient consoleClient = new ConsoleClient();
            consoleClient.start();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}