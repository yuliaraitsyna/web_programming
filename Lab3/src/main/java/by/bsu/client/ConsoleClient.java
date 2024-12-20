package by.bsu.client;

import by.bsu.controller.Controller;
import by.bsu.dao.ClientDao;
import by.bsu.entity.Client;
import by.bsu.entity.Project;
import by.bsu.entity.Staff;
import by.bsu.entity.TechnicalTask;
import by.bsu.strategy.Searcher;
import by.bsu.strategy.Sorting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConsoleClient {

    private Controller controller;
    private Searcher searcher;
    private static final Logger logger = LogManager.getLogger(ConsoleClient.class);

    public ConsoleClient() throws SQLException, IOException {
        controller = new Controller();
        searcher = new Searcher();
    }

    public void start() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Project Management System ===");
            System.out.println("1. Add Client");
            System.out.println("2. Update Client");
            System.out.println("3. Delete Client");
            System.out.println("4. List Clients");
            System.out.println("5. Add Project");
            System.out.println("6. Update Project");
            System.out.println("7. Delete Project");
            System.out.println("8. List Projects");
            System.out.println("9. Search Projects");
            System.out.println("10. Sort Projects");
            System.out.println("11. Add Technical Task");
            System.out.println("12. Update Technical Task");
            System.out.println("13. Delete Technical Task");
            System.out.println("14. List Technical Tasks");
            System.out.println("15. Add Staff");
            System.out.println("16. Update Staff");
            System.out.println("17. Delete Staff");
            System.out.println("18. List Staff");
            System.out.println("19. Assign staff to project");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addClient(scanner);
                    break;
                case 2:
                    updateClient(scanner);
                    break;
                case 3:
                    deleteClient(scanner);
                    break;
                case 4:
                    listClients();
                    break;
                case 5:
                    addProject(scanner);
                    break;
                case 6:
                    updateProject(scanner);
                    break;
                case 7:
                    deleteProject(scanner);
                    break;
                case 8:
                    listProjects();
                    break;
                case 9:
                    searchProjects(scanner);
                    break;
                case 10:
                    sortProjects(scanner);
                    break;
                case 11:
                    addTechnicalTask(scanner);
                    break;
                case 12:
                    updateTechnicalTask(scanner);
                    break;
                case 13:
                    deleteTechnicalTask(scanner);
                    break;
                case 14:
                    listTechnicalTasks();
                    break;
                case 15:
                    addStaff(scanner);
                    break;
                case 16:
                    updateStaff(scanner);
                    break;
                case 17:
                    deleteStaff(scanner);
                    break;
                case 18:
                    listStaff();
                    break;
                case 19:
                    assignStaffToProject(scanner);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
        try {
            controller.close();
        } catch (SQLException e) {
            logger.error("Failed to close controller.");
        }
    }

    private void addClient(Scanner scanner) {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        System.out.print("Enter client surname: ");
        String surname = scanner.nextLine();
        Client client = new Client(name, surname);
        controller.addClient(client);
    }

    private void updateClient(Scanner scanner) {
        System.out.print("Enter client ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // consume the newline
        System.out.print("Enter new client name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new client surname: ");
        String surname = scanner.nextLine();
        Client client = new Client(name, surname);
        controller.updateClient(id, client);
    }

    private void deleteClient(Scanner scanner) {
        System.out.print("Enter client ID to delete: ");
        Long id = scanner.nextLong();
        controller.deleteClient(id);
    }

    private void listClients() {
        List<Client> clients = controller.getAllClients();
        clients.forEach(client -> System.out.println(client));
    }

    private void addProject(Scanner scanner) {
        System.out.print("Enter project name: ");
        String name = scanner.nextLine();
        System.out.print("Enter project date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();
        Date date = parseDate(dateString);
        Project project = new Project(name, date);
        controller.addProject(project);
        System.out.println("Project added successfully.");
    }

    private void updateProject(Scanner scanner) {
        System.out.print("Enter project ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter new project name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new project date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();
        Date date = parseDate(dateString);
        Project project = new Project(name, date);
        controller.updateProject(id, project);
    }

    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }

    private void deleteProject(Scanner scanner) {
        System.out.print("Enter project ID to delete: ");
        Long id = scanner.nextLong();
        controller.deleteProject(id);
    }

    private void listProjects() {
        List<Project> projects = controller.getAllProjects();
        projects.forEach(project -> System.out.println(project));
    }

    private void addTechnicalTask(Scanner scanner) {
        System.out.print("Enter technical task description: ");
        String description = scanner.nextLine();

        System.out.print("Enter client ID for the task: ");
        Long clientId = scanner.nextLong();
        Client client = controller.getClientById(clientId);
        TechnicalTask task = new TechnicalTask(description, client);
        controller.addTechnicalTask(task);
    }

    private void updateTechnicalTask(Scanner scanner) {
        System.out.print("Enter technical task ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter new technical task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter client ID for the task: ");
        Long clientId = scanner.nextLong();
        Client client = controller.getClientById(clientId);
        TechnicalTask task = new TechnicalTask(description, client);
        controller.updateTechnicalTask(id, task);
    }

    private void deleteTechnicalTask(Scanner scanner) {
        System.out.print("Enter technical task ID to delete: ");
        Long id = scanner.nextLong();
        controller.deleteTechnicalTask(id);
    }

    private void listTechnicalTasks() {
        List<TechnicalTask> tasks = controller.getAllTechnicalTasks();
        tasks.forEach(task -> System.out.println(task));
    }

    private void addStaff(Scanner scanner) {
        System.out.print("Enter staff name: ");
        String name = scanner.nextLine();

        System.out.print("Enter staff surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter staff qualification: ");
        String qualification = scanner.nextLine();

        System.out.print("Enter staff salary: ");
        double salary;
        while (true) {
            try {
                salary = Double.parseDouble(scanner.nextLine());
                if (salary < 0) {
                    System.out.print("Salary cannot be negative. Enter again: ");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid salary: ");
            }
        }

        Staff staff = new Staff(name, surname, qualification, salary);

        controller.addStaff(staff);
        System.out.println("Staff added successfully.");
    }

    private void updateStaff(Scanner scanner) {
        System.out.print("Enter staff ID to update: ");
        Long id;
        while (true) {
            try {
                id = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid ID. Please enter a valid staff ID: ");
            }
        }

        System.out.print("Enter new staff name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new staff surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter new staff qualification: ");
        String qualification = scanner.nextLine();

        System.out.print("Enter new staff salary: ");
        double salary;
        while (true) {
            try {
                salary = Double.parseDouble(scanner.nextLine());
                if (salary < 0) {
                    System.out.print("Salary cannot be negative. Enter again: ");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid salary: ");
            }
        }

        Staff staff = new Staff(name, surname, qualification, salary);

        controller.updateStaff(id, staff);
        System.out.println("Staff updated successfully.");
    }

    private void deleteStaff(Scanner scanner) {
        System.out.print("Enter staff ID to delete: ");
        Long id = scanner.nextLong();
        controller.deleteStaff(id);
        System.out.println("Staff deleted successfully.");
    }

    private void listStaff() {
        List<Staff> staffList = controller.getAllStaff();
        staffList.forEach(staff -> System.out.println(staff));
    }

    private void searchProjects(Scanner scanner) {
        System.out.print("Enter project title to search: ");
        String title = scanner.nextLine();
        List<Project> projects = controller.getAllProjects();
        List<Project> results = searcher.searchByTitle(projects, title);
        if (results.isEmpty()) {
            System.out.println("No projects found with the title containing: " + title);
        } else {
            results.forEach(System.out::println);
        }
    }

    private void sortProjects(Scanner scanner) {
        System.out.println("Choose sort option:");
        System.out.println("1. By Title");
        System.out.println("2. By Cost");
        System.out.println("3. By Date");

        int sortOption = scanner.nextInt();
        scanner.nextLine();
        List<Project> projects = controller.getAllProjects();
        switch (sortOption) {
            case 1:
                Sorting.sortByTitle(projects);
                System.out.println("Projects sorted by title:");
                break;
            case 2:
                Sorting.sortByCost(projects);
                System.out.println("Projects sorted by cost:");
                break;
            case 3:
                Sorting.sortByDate(projects);
                System.out.println("Projects sorted by date:");
                break;
            default:
                System.out.println("Invalid sort option.");
                return;
        }
        projects.forEach(System.out::println);
    }

    private void assignStaffToProject(Scanner scanner) throws SQLException {
        System.out.print("Enter technical task ID to assign staff: ");
        Long taskId = scanner.nextLong();
        TechnicalTask task = controller.getTechnicalTaskById(taskId);

        if (task == null) {
            System.out.println("Technical task not found.");
            return;
        }

        System.out.print("Enter project ID to assign staff to: ");
        Long projectId = scanner.nextLong();
        Project project = controller.getProjectById(projectId);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        List<Staff> availableStaff = controller.getAllStaff();

        controller.assignStaff(task, project, availableStaff);
    }
}