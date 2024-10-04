package entity;

import dao.ClientDao;
import dao.ProjectDao;
import dao.StaffDao;
import dao.TechnicalTaskDao;
import entity.model.SortAction;
import strategy.Searcher;
import strategy.Sorting;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Admin {
    private final ClientDao clientDao;
    private final ProjectDao projectDao;
    private final TechnicalTaskDao technicalTaskDao;
    private final StaffDao staffDao;
    private Sorting sorting;
    private Searcher searcher;

    public Admin (Connection connection) {
        this.sorting = new Sorting();
        this.searcher = new Searcher();
        this.clientDao = new ClientDao(connection);
        this.projectDao = new ProjectDao(connection);
        this.technicalTaskDao = new TechnicalTaskDao(connection);
        this.staffDao = new StaffDao(connection);
    }

    public void addClient(Client client) throws SQLException {
        clientDao.addClient(client);
    }

    public void updateClient(int id, Client client) throws SQLException {
        clientDao.updateClient(id, client);
    }

    public void deleteClient(int id) throws SQLException {
        clientDao.deleteClient(id);
    }

    public List<Client> getAllClients() throws SQLException {
        return clientDao.getAllClients();
    }

    public void addProject(Project project) throws SQLException {
        projectDao.addProject(project);
    }

    public void updateProject(Project project) throws SQLException {
        projectDao.updateProject(project);
    }

    public void deleteProject(int id) throws SQLException {
        projectDao.deleteProject(id);
    }

    public List<Project> getAllProjects() throws SQLException {
        return projectDao.getAllProjects();
    }

    public void addTechnicalTask(TechnicalTask task, int clientId) throws SQLException {
        technicalTaskDao.addTechnicalTask(task, clientId);
    }

    public void updateTechnicalTask(int id, TechnicalTask task) throws SQLException {
        technicalTaskDao.updateTechnicalTask(id, task);
    }

    public void deleteTechnicalTask(int id) throws SQLException {
        technicalTaskDao.deleteTechnicalTask(id);
    }

    public List<TechnicalTask> getAllTechnicalTasks() throws SQLException {
        return technicalTaskDao.getAllTechnicalTasks();
    }

    public void addStaff(Staff staff) throws SQLException {
        staffDao.addStaff(staff);
    }

    public void updateStaff(int id, Staff staff) throws SQLException {
        staffDao.updateStaff(id, staff);
    }

    public void deleteStaff(int id) throws SQLException {
        staffDao.deleteStaff(id);
    }

    public List<Staff> getAllStaff() throws SQLException {
        return staffDao.getAllStaff();
    }

    public Staff getStaffById(int id) throws SQLException {
        return staffDao.getStaffById(id);
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
