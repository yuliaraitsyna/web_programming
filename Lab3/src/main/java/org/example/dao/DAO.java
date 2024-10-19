package org.example.dao;

import entity.Client;
import factory.BaseFactory;
import factory.Factory;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
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
            logger.log(Level.SEVERE, e.getMessage());
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
            logger.log(Level.SEVERE, e.getMessage());
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
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public Client getClientById(int id) {
        try {
            return entityManager.find(Client.class, id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Client> getAllClients() {
        try {
            TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
            return query.getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }
}
