package main;

import entity.*;
import factory.BaseFactory;
import factory.Factory;
import strategy.Searcher;
import strategy.Sorting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BaseFactory factory = new Factory();
        Admin admin = new Admin();

        Client client1 = factory.createClient("Ivan Ivanov");
        Client client2 = factory.createClient("Irina Petrova");
        Client client3 = factory.createClient("Yulia Raitsyna");

        List<TechnicalTask> technicalTasks = new ArrayList<>();
        TechnicalTask task1 = factory.createTechnicalTask("Construction needs engineers and designers", client1);
        task1.addRequiredStaff("Engineer", 1);
        task1.addRequiredStaff("Designer", 1);

        TechnicalTask task2 = factory.createTechnicalTask("Development needs programmers", client2);
        task2.addRequiredStaff("Programmer", 2);
        task2.addRequiredStaff("Tester", 1);

        TechnicalTask task3 = factory.createTechnicalTask("Engineered project", client3);
        task3.addRequiredStaff("Designer", 1);
        task3.addRequiredStaff("Engineer", 1);

        technicalTasks.add(task1);
        technicalTasks.add(task2);
        technicalTasks.add(task3);

        List<Project> projects = new ArrayList<>();
        Project project1 = factory.createProject("Construction", new Date(2024, 9, 10));
        Project project2 = factory.createProject("Development", new Date(2024, 1, 10));
        Project project3 = factory.createProject("ABC Tech", new Date(2023, 4, 23));

        projects.add(project2);
        projects.add(project1);
        projects.add(project3);

        ArrayList<Staff> availableStaff = new ArrayList<>();
        availableStaff.add(factory.createStaff("John Doe", "Engineer", 3000));
        availableStaff.add(factory.createStaff("Jane Smith", "Engineer", 3200));
        availableStaff.add(factory.createStaff("Alice Johnson", "Designer", 8800));
        availableStaff.add(factory.createStaff("Jenny Stivenson", "Designer", 9800));
        availableStaff.add(factory.createStaff("Bob Brown", "Programmer", 4000));
        availableStaff.add(factory.createStaff("Charlie Green", "Programmer", 3900));
        availableStaff.add(factory.createStaff("Diana White", "Programmer", 4200));
        availableStaff.add(factory.createStaff("Eve Black", "Tester", 3500));

        for (TechnicalTask task : technicalTasks) {
            Project associatedProject = projects.get(technicalTasks.indexOf(task));
            admin.assignStaffToProject(task, associatedProject, availableStaff);
        }

        System.out.println("Projects before sorting:");
        for (Project project : projects) {
            System.out.println(project);
        }

        Sorting.sortByCost(projects);
        System.out.println("\nProjects after sorting by cost:");
        for (Project project : projects) {
            System.out.println(project.getTitle() + " - Cost: " + project.getCost());
        }

        Sorting.sortByTitle(projects);
        System.out.println("\nProjects after sorting by title:");
        for (Project project : projects) {
            System.out.println(project.getTitle());
        }

        Sorting.sortByDate(projects);
        System.out.println("\nProjects after sorting by date:");
        for (Project project : projects) {
            System.out.println(project.getTitle() + " - Date: " + project.getDate());
        }

        Searcher searcher = new Searcher();
        String searchCriteria = "De";
        List<Project> searchResults = searcher.searchByTitle(projects, searchCriteria);
        System.out.println("\nSearch results for title containing '" + searchCriteria + "':");
        for (Project project : searchResults) {
            System.out.println(project);
        }
    }
}
