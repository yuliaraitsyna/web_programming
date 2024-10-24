package by.bsu.dao;

import by.bsu.metamodel.Client_;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import by.bsu.entity.Client;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ClientDao extends DAO {
    //private BaseFactory factory;

    public ClientDao() {
        super();
        //this.factory = new Factory();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }


    public Client getClientById(Long id) {
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        Root<Client> root = query.from(Client.class);
        query.select(root).where(criteriaBuilder.equal(root.get(Client_.id), id));

        return entityManager.createQuery(query).getSingleResult();
    }

    public List<Client> getAllClients() {
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        Root<Client> root = query.from(Client.class);
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }

}
