package org.example.dao;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.entity.Client;
import org.example.factory.BaseFactory;
import org.example.factory.Factory;

import java.util.List;

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

    public void updateClient(Long id, Client updatedClient) {
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

    public void deleteClient(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Client client = entityManager.find(Client.class, id);
            if (client != null) {
                entityManager.remove(client);
                System.out.println("Client deleted: " + client);
            } else {
                System.out.println("Client not found with ID: " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Log the exception
        }
    }

    public Client getClientById(Long id) {
        EntityTransaction transaction = null;
        Client client = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            TypedQuery<Client> query = entityManager.createNamedQuery("Client.findById", Client.class);
            query.setParameter("id", id);
            client = query.getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return client;
    }

    public List<Client> getAllClients() {
        EntityTransaction transaction = null;
        List<Client> clients = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            TypedQuery<Client> query = entityManager.createNamedQuery("Client.findAll", Client.class);
            clients = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return clients;
    }
}
