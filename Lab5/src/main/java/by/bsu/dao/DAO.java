package by.bsu.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;

public abstract class DAO {
    protected EntityManager entityManager;
    protected CriteriaBuilder criteriaBuilder;

    public DAO() {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Lab4PU");
            this.entityManager = entityManagerFactory.createEntityManager();
            this.criteriaBuilder = entityManager.getCriteriaBuilder();
        } catch (Exception e) {
            System.out.print("Failed to create EntityManager");
        }
    }
}
