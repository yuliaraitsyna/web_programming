package by.bsu.strategy.comparator;

import by.bsu.entity.Project;
import java.util.Comparator;

public class ProjectComparator {

    public static Comparator<Project> byCost() {
        return Comparator.comparingDouble(Project::getCost);
    }

    public static Comparator<Project> byDate() {
        return Comparator.comparing(Project::getDate);
    }

    public static Comparator<Project> byTitle() {
        return Comparator.comparing(Project::getTitle);
    }
}
