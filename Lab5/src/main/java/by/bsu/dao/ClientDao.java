package by.bsu.dao;

import by.bsu.entity.Client;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ClientDao extends DAO {
    public ClientDao() {
        super();
    }

    public List<Client> getAllClients() {
        try {
            CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
            Root<Client> root = query.from(Client.class);
            query.select(root);

            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
           System.out.print(e.getMessage());
        }
        return List.of();
    }

    public void addClient(Client client) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
