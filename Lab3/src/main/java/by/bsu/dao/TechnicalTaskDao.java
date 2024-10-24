package by.bsu.dao;

import by.bsu.metamodel.TechnicalTask_;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import by.bsu.entity.TechnicalTask;
import by.bsu.factory.BaseFactory;
import by.bsu.factory.Factory;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class TechnicalTaskDao extends DAO {
    private BaseFactory factory;

    public TechnicalTaskDao() {
        super();
        this.factory = new Factory();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public TechnicalTask getTechnicalTaskById(Long id) {
        CriteriaQuery<TechnicalTask> query = criteriaBuilder.createQuery(TechnicalTask.class);
        Root<TechnicalTask> root = query.from(TechnicalTask.class);
        query.select(root).where(criteriaBuilder.equal(root.get(TechnicalTask_.id), id));

        return entityManager.createQuery(query).getSingleResult();
    }

    public List<TechnicalTask> getAllTechnicalTasks() {
        CriteriaQuery<TechnicalTask> query = criteriaBuilder.createQuery(TechnicalTask.class);
        Root<TechnicalTask> root = query.from(TechnicalTask.class);
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }
}
