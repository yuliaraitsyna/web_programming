package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.ClientDao;
import org.example.entity.Client;
import org.example.entity.Project;
import org.example.entity.Staff;
import org.example.entity.TechnicalTask;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Lab3PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ClientDao clientDao = new ClientDao();

        entityManager.close();
        entityManagerFactory.close();
    }
}