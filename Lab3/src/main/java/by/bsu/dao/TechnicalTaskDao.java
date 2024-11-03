package by.bsu.dao;

import by.bsu.exceptions.DaoException;
import by.bsu.metamodel.TechnicalTask_;
import jakarta.persistence.EntityTransaction;
import by.bsu.entity.TechnicalTask;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TechnicalTaskDao extends DAO {
    private static final Logger logger = LogManager.getLogger(TechnicalTaskDao.class);

    public TechnicalTaskDao() {
        super();
    }

    public void addTechnicalTask(TechnicalTask technicalTask) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(technicalTask);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to add technical task: " + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }
    }

    public void updateTechnicalTask(Long id, TechnicalTask updatedTask) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CriteriaUpdate<TechnicalTask> update = criteriaBuilder.createCriteriaUpdate(TechnicalTask.class);
            Root<TechnicalTask> root = update.from(TechnicalTask.class);

            update.set(root.get(TechnicalTask_.description), updatedTask.getDescription())
                    .set(String.valueOf(root.get(TechnicalTask_.requiredStaff)), updatedTask.getRequiredStaff())
                    .set(root.get(TechnicalTask_.client), updatedTask.getClient())
                    .where(criteriaBuilder.equal(root.get(TechnicalTask_.id), id));

            entityManager.createQuery(update).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to update technical task: " + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }
    }

    public void deleteTechnicalTask(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CriteriaDelete<TechnicalTask> delete = criteriaBuilder.createCriteriaDelete(TechnicalTask.class);
            Root<TechnicalTask> root = delete.from(TechnicalTask.class);
            delete.where(criteriaBuilder.equal(root.get(TechnicalTask_.id), id));

            entityManager.createQuery(delete).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to delete technical task: " + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }
    }

    public TechnicalTask getTechnicalTaskById(Long id) {
        try {
            CriteriaQuery<TechnicalTask> query = criteriaBuilder.createQuery(TechnicalTask.class);
            Root<TechnicalTask> root = query.from(TechnicalTask.class);
            query.select(root).where(criteriaBuilder.equal(root.get(TechnicalTask_.id), id));

            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            logger.error("An unexpected error occurred while retrieving TechnicalTask with ID " + id + ": " + e.getMessage());
            throw new DaoException(e.getMessage());
        }
    }

    public List<TechnicalTask> getAllTechnicalTasks() {
        try {
            CriteriaQuery<TechnicalTask> query = criteriaBuilder.createQuery(TechnicalTask.class);
            Root<TechnicalTask> root = query.from(TechnicalTask.class);
            query.select(root);

            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            logger.error("An unexpected error occurred while retrieving all TechnicalTasks: " + e.getMessage());
            throw new DaoException(e.getMessage());
        }
    }

}
