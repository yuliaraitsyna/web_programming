package by.bsu.dao;

import by.bsu.metamodel.Staff_;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import by.bsu.entity.Staff;
import by.bsu.factory.BaseFactory;
import by.bsu.factory.Factory;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class StaffDao extends DAO {
    private BaseFactory factory;

    public StaffDao() {
        super();
        this.factory = new Factory();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public Staff getStaffById(Long id) {
        CriteriaQuery<Staff> query = criteriaBuilder.createQuery(Staff.class);
        Root<Staff> root = query.from(Staff.class);
        query.select(root).where(criteriaBuilder.equal(root.get(Staff_.id), id));

        return entityManager.createQuery(query).getSingleResult();
    }

    public List<Staff> getAllStaff() {
        CriteriaQuery<Staff> query = criteriaBuilder.createQuery(Staff.class);
        Root<Staff> root = query.from(Staff.class);
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }
}
