package by.bsu.dao;

import by.bsu.metamodel.Project_;
import jakarta.persistence.EntityTransaction;
import by.bsu.entity.Project;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.bsu.exceptions.DaoException;

import java.util.List;

public class ProjectDao extends DAO {
    private static final Logger logger = LogManager.getLogger(ProjectDao.class);

    public ProjectDao() {
        super();
    }

    public void addProject(Project project) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(project);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to add project: " + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }
    }

    public void updateProject(Long id, Project updatedProject) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            CriteriaUpdate<Project> update = criteriaBuilder.createCriteriaUpdate(Project.class);
            Root<Project> root = update.from(Project.class);

            update.set(root.get(Project_.title), updatedProject.getTitle())
                    .set(root.get(Project_.date), updatedProject.getDate())
                    .where(criteriaBuilder.equal(root.get(Project_.id), id));

            entityManager.createQuery(update).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to update project: " + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }
    }

    public void deleteProject(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CriteriaDelete<Project> delete = criteriaBuilder.createCriteriaDelete(Project.class);
            Root<Project> root = delete.from(Project.class);
            delete.where(criteriaBuilder.equal(root.get(Project_.id), id));
            entityManager.createQuery(delete).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to delete project: " + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }
    }

    public Project getProjectById(Long id) {
        try {
            CriteriaQuery<Project> query = criteriaBuilder.createQuery(Project.class);
            Root<Project> root = query.from(Project.class);
            query.select(root).where(criteriaBuilder.equal(root.get(Project_.id), id));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            logger.error("Failed to get project by ID: " + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }
    }

    public List<Project> getAllProjects() {
        try {
            CriteriaQuery<Project> query = criteriaBuilder.createQuery(Project.class);
            Root<Project> root = query.from(Project.class);
            query.select(root);
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            logger.error("Failed to get all projects: " + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }
    }
}
