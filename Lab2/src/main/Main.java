package main;

import controller.Controller;
import entity.*;
import entity.model.SortAction;
import factory.BaseFactory;
import factory.Factory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        try (Controller controller = new Controller()) {
            Admin admin = new Admin();
            BaseFactory factory = new Factory();

            List<Client> clients = controller.getAllClients();
            System.out.println("Clients:");
            for (Client client : clients) {
                System.out.println(client);
            }

            List<Staff> staffList = controller.getAllStaff();
            System.out.println("Staff:");
            for (Staff staff : staffList) {
                System.out.println(staff);
            }

            List<Project> projects = controller.getAllProjects();
            System.out.println("Projects:");
            for (Project project : projects) {
                admin.assignStaffToProject(project.getAssignedTask(), project, (ArrayList<Staff>) staffList);
                System.out.println(project);
            }

            System.out.println("Clients before updating:");
            Client client1 = factory.createClient("Ivan", "Stepanov");
            controller.updateClient(4, client1);
            System.out.println(controller.getAllClients());

            Client client2 = factory.createClient("John", "Doe");
            controller.updateClient(4, client2);
            System.out.println("Clients after updating:");
            System.out.println(controller.getAllClients());

            System.out.println("TechnicalTasks before adding:");
            System.out.println(controller.getAllTechnicalTasks());
            TechnicalTask task = factory.createTechnicalTask("New task", client1);
            controller.addTechnicalTask(task, 4);

            System.out.println("TechnicalTasks after adding a task:");
            System.out.println(controller.getAllTechnicalTasks());

            controller.deleteTechnicalTask(4);
            System.out.println("TechnicalTasks after deleting new one:");
            System.out.println(controller.getAllTechnicalTasks());

            System.out.println("*************Sort projects by cost: *************");
            admin.sort(projects, SortAction.COST);
            System.out.println(projects);

            System.out.println("*************Sort projects by title: *************");
            admin.sort(projects, SortAction.TITLE);
            System.out.println(projects);

            System.out.println("*************Sort projects by date: *************");
            admin.sort(projects, SortAction.DATE);
            System.out.println(projects);

            String searchQuery = "ABC";
            System.out.println("*************Search for project: " + searchQuery);
            System.out.println(admin.searchByTitle(projects, searchQuery));

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
