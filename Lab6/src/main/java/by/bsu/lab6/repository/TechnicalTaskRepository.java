package by.bsu.lab6.repository;

import by.bsu.lab6.entity.TechnicalTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalTaskRepository extends CrudRepository<TechnicalTask, Long> {
}
