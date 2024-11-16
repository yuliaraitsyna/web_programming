package by.bsu.dao;

import by.bsu.entity.TechnicalTask;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class TechnicalTaskDao extends DAO {
    public TechnicalTaskDao() {
        super();
    }

    public List<TechnicalTask> getAllTechnicalTasks() {
        try {
            CriteriaQuery<TechnicalTask> query = criteriaBuilder.createQuery(TechnicalTask.class);
            Root<TechnicalTask> root = query.from(TechnicalTask.class);
            query.select(root);

            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while retrieving all TechnicalTasks: " + e.getMessage());
        }
        return List.of();
    }

}
