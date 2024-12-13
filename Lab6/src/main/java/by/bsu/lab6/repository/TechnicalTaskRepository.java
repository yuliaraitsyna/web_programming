package by.bsu.lab6.repository;

import by.bsu.lab6.entity.TechnicalTask;
import org.springframework.data.repository.CrudRepository;

public interface TechnicalTaskRepository extends CrudRepository<TechnicalTask, Long> {
}
