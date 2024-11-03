package by.bsu.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class DAO {
    protected EntityManager entityManager;
    protected CriteriaBuilder criteriaBuilder;
    private static final Logger logger = LogManager.getLogger(DAO.class);;

    public DAO() {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Lab3PU");
            this.entityManager = entityManagerFactory.createEntityManager();
            this.criteriaBuilder = entityManager.getCriteriaBuilder();
        } catch (Exception e) {
            logger.error("Failed to create EntityManager", e);
        }
    }

    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
