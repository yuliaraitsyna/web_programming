package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.client.ConsoleClient;
import org.example.dao.ClientDao;
import org.example.dao.ProjectDao;
import org.example.dao.StaffDao;
import org.example.dao.TechnicalTaskDao;
import org.example.entity.Client;
import org.example.entity.Project;
import org.example.entity.Staff;
import org.example.entity.TechnicalTask;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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