package by.bsu.dao;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import by.bsu.entity.Staff;
import by.bsu.factory.BaseFactory;
import by.bsu.factory.Factory;

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
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Staff staff = entityManager.find(Staff.class, id);
            if (staff != null) {
                staff.setName(updatedStaff.getName());
                staff.setSurname(updatedStaff.getSurname());
                staff.setQualification(updatedStaff.getQualification());
                staff.setSalary(updatedStaff.getSalary());
                staff.setBusy(updatedStaff.isBusy());
                entityManager.merge(staff);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteStaff(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Staff staff = entityManager.find(Staff.class, id);
            if (staff != null) {
                entityManager.remove(staff);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Staff getStaffById(Long id) {
        EntityTransaction transaction = null;
        Staff staff = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Staff> query = entityManager.createNamedQuery("Staff.findById", Staff.class);
            query.setParameter("id", id);
            staff = query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return staff;
    }

    public List<Staff> getAllStaff() {
        EntityTransaction transaction = null;
        List<Staff> staffList = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Staff> query = entityManager.createNamedQuery("Staff.findAll", Staff.class);
            staffList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return staffList;
    }
}
