package by.bsu.dao;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import by.bsu.entity.TechnicalTask;
import by.bsu.factory.BaseFactory;
import by.bsu.factory.Factory;

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
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TechnicalTask task = entityManager.find(TechnicalTask.class, id);
            if (task != null) {
                task.setDescription(updatedTask.getDescription());
                task.setRequiredStaff(updatedTask.getRequiredStaff());
                task.setClient(updatedTask.getClient());
                entityManager.merge(task);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTechnicalTask(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TechnicalTask task = entityManager.find(TechnicalTask.class, id);
            if (task != null) {
                entityManager.remove(task);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public TechnicalTask getTechnicalTaskById(Long id) {
        EntityTransaction transaction = null;
        TechnicalTask task = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            TypedQuery<TechnicalTask> query = entityManager.createNamedQuery("TechnicalTask.findById", TechnicalTask.class);
            query.setParameter("id", id);
            task = query.getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return task;
    }

    public List<TechnicalTask> getAllTechnicalTasks() {
        EntityTransaction transaction = null;
        List<TechnicalTask> tasks = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            TypedQuery<TechnicalTask> query = entityManager.createNamedQuery("TechnicalTask.findAll", TechnicalTask.class);
            tasks = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return tasks;
    }
}
