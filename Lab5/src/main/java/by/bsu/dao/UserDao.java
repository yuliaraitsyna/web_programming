package by.bsu.dao;

import by.bsu.entity.User;
import by.bsu.metamodel.User_;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class UserDao extends DAO {

    public UserDao() {
        super();
    }

    public User getUserByUsername(String username) {
        try {
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root).where(criteriaBuilder.equal(root.get(User_.username), username));

            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
