package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class DAO {
    protected EntityManager entityManager;

    public DAO() {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Lab3PU");
            this.entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            System.out.println("Failed to create EntityManager: " + e.getMessage());
        }
    }

    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
