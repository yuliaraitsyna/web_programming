package by.bsu.dao;

import by.bsu.metamodel.Staff_;
import jakarta.persistence.EntityTransaction;
import by.bsu.entity.Staff;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.bsu.exceptions.DaoException;

import java.util.List;

public class StaffDao extends DAO {
    private static final Logger logger = LogManager.getLogger(StaffDao.class);

    public StaffDao() {
        super();
    }

    public void addStaff(Staff staff) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(staff);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to add staff: " + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }
    }

    public void updateStaff(Long id, Staff updatedStaff) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CriteriaUpdate<Staff> update = criteriaBuilder.createCriteriaUpdate(Staff.class);
            Root<Staff> root = update.from(Staff.class);

            update.set(root.get(Staff_.name), updatedStaff.getName())
                    .set(root.get(Staff_.surname), updatedStaff.getSurname())
                    .set(root.get(Staff_.qualification), updatedStaff.getQualification())
                    .set(root.get(Staff_.salary), updatedStaff.getSalary())
                    .set(root.get(Staff_.isBusy), updatedStaff.isBusy())
                    .where(criteriaBuilder.equal(root.get(Staff_.id), id));

            entityManager.createQuery(update).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to update staff: " + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }
    }

    public void deleteStaff(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CriteriaDelete<Staff> delete = criteriaBuilder.createCriteriaDelete(Staff.class);
            Root<Staff> root = delete.from(Staff.class);
            delete.where(criteriaBuilder.equal(root.get(Staff_.id), id));
            entityManager.createQuery(delete).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to delete staff: " + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }
    }

    public Staff getStaffById(Long id) {
        try {
            CriteriaQuery<Staff> query = criteriaBuilder.createQuery(Staff.class);
            Root<Staff> root = query.from(Staff.class);
            query.select(root).where(criteriaBuilder.equal(root.get(Staff_.id), id));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            logger.error("Failed to get staff by ID: " + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }
    }

    public List<Staff> getAllStaff() {
        try {
            CriteriaQuery<Staff> query = criteriaBuilder.createQuery(Staff.class);
            Root<Staff> root = query.from(Staff.class);
            query.select(root);
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            logger.error("Failed to get all staff: " + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }
    }
}
