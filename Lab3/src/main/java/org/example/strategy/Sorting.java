package org.example.strategy;

import org.example.entity.Project;
import org.example.strategy.comparator.ProjectComparator;

import java.util.Collections;
import java.util.List;

public class Sorting {
    public static void sortByCost(List<Project> projects) {
        Collections.sort(projects, ProjectComparator.byCost());
    }

    public static void sortByTitle(List<Project> projects) {
        Collections.sort(projects, ProjectComparator.byTitle());
    }

    public static void sortByDate(List<Project> projects) {
        Collections.sort(projects, ProjectComparator.byDate());
    }
}
