package by.bsu.dao;

import by.bsu.metamodel.Client_;
import by.bsu.entity.Client;
import by.bsu.exceptions.DaoException;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ClientDao extends DAO {
    private static final Logger logger = LogManager.getLogger(ClientDao.class);

    public ClientDao() {
        super();
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
            logger.error("Failed to add client", e);
            throw new DaoException(e.getMessage());
        }
    }

    public void updateClient(Long id, Client updatedClient) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            CriteriaUpdate<Client> update = criteriaBuilder.createCriteriaUpdate(Client.class);
            Root<Client> root = update.from(Client.class);

            update.set(root.get(Client_.name), updatedClient.getName())
                    .set(root.get(Client_.surname), updatedClient.getSurname())
                    .where(criteriaBuilder.equal(root.get(Client_.id), id));

            entityManager.createQuery(update).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to update client", e);
            throw new DaoException(e.getMessage());
        }
    }

    public void deleteClient(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CriteriaDelete<Client> delete = criteriaBuilder.createCriteriaDelete(Client.class);
            Root<Client> root = delete.from(Client.class);
            delete.where(criteriaBuilder.equal(root.get(Client_.id), id));
            entityManager.createQuery(delete).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to delete client", e);
            throw new DaoException(e.getMessage());
        }
    }

    public Client getClientById(Long id) {
        try {
            CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
            Root<Client> root = query.from(Client.class);
            query.select(root).where(criteriaBuilder.equal(root.get(Client_.id), id));

            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            logger.error("Failed to retrieve client", e);
            throw new DaoException(e.getMessage());
        }
    }

    public List<Client> getAllClients() {
        try {
            CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
            Root<Client> root = query.from(Client.class);
            query.select(root);

            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            logger.error("Failed to retrieve all clients", e);
            throw new DaoException(e.getMessage());
        }
    }
}
