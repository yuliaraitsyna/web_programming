package org.example.entity;

import org.example.entity.model.SortAction;
import org.example.strategy.Searcher;
import org.example.strategy.Sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Admin {
    private Sorting sorting;
    private Searcher searcher;

    public Admin () {
        this.sorting = new Sorting();
        this.searcher = new Searcher();
    }

    public void assignStaffToProject(TechnicalTask task, Project project, ArrayList<Staff> availableStaff) {
        Map<String, Integer> requiredStaff = task.getRequiredStaff();
        for (Map.Entry<String, Integer> entry : requiredStaff.entrySet()) {
            String qualification = entry.getKey();
            Integer number = entry.getValue();

            for (Staff staff : availableStaff) {
                if (!staff.isBusy() && staff.getQualification().equals(qualification) && number > 0) {
                    project.assignStaff(staff);
                    staff.setBusy(true);
                    number--;
                }
            }

            if (number > 0) {
                System.out.println("Not enough staff with " + qualification + " qualification for task: " + task.getDescription());
            }
        }
    }

    public void sort(List<Project> projects, SortAction action) {
        switch (action) {
            case COST:
                Sorting.sortByCost(projects);
                break;
            case TITLE:
                Sorting.sortByTitle(projects);
                break;
            case DATE:
                Sorting.sortByDate(projects);
                break;
            default:
                System.out.println("Invalid sorting option");
                break;
        }
    }

    public List<Project> searchByTitle(List<Project> projects, String title) {
        return searcher.searchByTitle(projects, title);
    }
}
