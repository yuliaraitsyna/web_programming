package by.bsu.lab6.repository;

import by.bsu.lab6.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
}
