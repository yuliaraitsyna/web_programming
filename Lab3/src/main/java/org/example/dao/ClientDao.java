package org.example.dao;


import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.Client;
import org.example.factory.BaseFactory;
import org.example.factory.Factory;

import java.util.List;
import java.util.logging.Level;

public class ClientDao extends DAO {
    private BaseFactory factory;

    public ClientDao() {
        super();
        this.factory = new Factory();
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

    public void updateClient(int id, Client updatedClient) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Client client = entityManager.find(Client.class, id);
            if (client != null) {
                client.setName(updatedClient.getName());
                client.setSurname(updatedClient.getSurname());
                entityManager.merge(client);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public void deleteClient(int id) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Client client = entityManager.find(Client.class, id);
            if (client != null) {
                entityManager.remove(client);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public Client getClientById(int id) {
        try {
            return entityManager.find(Client.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Client> getAllClients() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> root = criteriaQuery.from(Client.class);
        criteriaQuery.select(root);
        List<Client> weatherList = entityManager.createQuery(criteriaQuery).getResultList();
        return weatherList;
    }
}
