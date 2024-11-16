package by.bsu.dao;

import by.bsu.entity.Staff;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class StaffDao extends DAO {
    public StaffDao() {
        super();
    }

    public List<Staff> getAllStaff() {
        try {
            CriteriaQuery<Staff> query = criteriaBuilder.createQuery(Staff.class);
            Root<Staff> root = query.from(Staff.class);
            query.select(root);
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            System.out.println("Failed to get all staff" );
        }
        return List.of();
    }
}
