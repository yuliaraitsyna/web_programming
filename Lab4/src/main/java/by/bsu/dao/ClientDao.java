package by.bsu.dao;

import by.bsu.entity.Client;
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
           System.out.print("Failed while gettting list of Clients");
        }
        return List.of();
    }
}
