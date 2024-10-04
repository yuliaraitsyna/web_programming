package main;

import dao.ClientDao;
import dao.ProjectDao;
import dao.JdbcConnector;
import dao.StaffDao;
import dao.TechnicalTaskDao;
import entity.*;
import entity.model.SortAction;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = JdbcConnector.getConnection()) {

            ClientDao clientDao = new ClientDao(connection);
            StaffDao staffDao = new StaffDao(connection);
            ProjectDao projectDao = new ProjectDao(connection);
            TechnicalTaskDao technicalTaskDao = new TechnicalTaskDao(connection);
            Admin admin = new Admin();

            List<Client> clients = clientDao.getAllClients();
            System.out.println("Clients:");
            for (Client client : clients) {
                System.out.println(client);
            }

            List<Staff> staffList = staffDao.getAllStaff();
            System.out.println("Staff:");
            for (Staff staff : staffList) {
                System.out.println(staff);
            }

            List<Project> projects = projectDao.getAllProjects();
            System.out.println("Projects:");
            for (Project project : projects) {
                System.out.println(project);
            }

            List<TechnicalTask> tasks = technicalTaskDao.getAllTechnicalTasks();
            System.out.println("Technical Tasks:");
            for (TechnicalTask task : tasks) {
                System.out.println(task);
            }

            System.out.println("Sort projects by cost: ");
            admin.sort(projects, SortAction.COST);
            System.out.println(projects);

            System.out.println("Sort projects by title: ");
            admin.sort(projects, SortAction.TITLE);
            System.out.println(projects);

            System.out.println("Sort projects by date: ");
            admin.sort(projects, SortAction.DATE);
            System.out.println(projects);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
