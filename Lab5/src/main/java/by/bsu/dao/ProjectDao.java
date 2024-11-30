package by.bsu.dao;

import by.bsu.entity.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ProjectDao extends DAO {

    public ProjectDao() {
        super();
    }

    public List<Project> getAllProjects() {
        try {
            CriteriaQuery<Project> query = criteriaBuilder.createQuery(Project.class);
            Root<Project> root = query.from(Project.class);
            query.select(root);
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            System.out.println("Failed to retrieve all projects: " + e.getMessage());
            return List.of();
        }
    }
}
