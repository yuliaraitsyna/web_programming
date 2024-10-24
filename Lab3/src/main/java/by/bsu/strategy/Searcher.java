package by.bsu.strategy;



import by.bsu.entity.Project;

import java.util.List;
import java.util.stream.Collectors;

public class Searcher {
    public List<Project> searchByTitle(List<Project> projects, String title) {
        return projects.stream()
                .filter(project -> project.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}
