package by.bsu.lab6.repository;

import by.bsu.lab6.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}