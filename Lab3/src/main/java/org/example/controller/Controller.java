package org.example.controller;

import org.example.dao.ClientDao;
import org.example.dao.ProjectDao;
import org.example.dao.StaffDao;
import org.example.dao.TechnicalTaskDao;
import org.example.entity.Client;
import org.example.entity.Project;
import org.example.entity.Staff;
import org.example.entity.TechnicalTask;
import org.example.entity.model.SortAction;
import org.example.strategy.Sorting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Controller implements AutoCloseable {

    private ClientDao clientDao;
    private StaffDao staffDao;
    private ProjectDao projectDao;
    private TechnicalTaskDao technicalTaskDao;

    public Controller() throws SQLException, IOException {
        clientDao = new ClientDao();
        staffDao = new StaffDao();
        projectDao = new ProjectDao();
        technicalTaskDao = new TechnicalTaskDao();
    }

    public void addClient(Client client) throws SQLException {
        clientDao.addClient(client);
    }

    public void updateClient(Long id, Client client) throws SQLException {
        clientDao.updateClient(id, client);
    }

    public void deleteClient(Long id) throws SQLException {
        clientDao.deleteClient(id);
    }

    public List<Client> getAllClients() throws SQLException {
        return clientDao.getAllClients();
    }

    public Client getClientById(Long id) throws SQLException {
        return clientDao.getClientById(id);
    }

    public void addProject(Project project) throws SQLException {
        projectDao.addProject(project);
    }

    public void updateProject(Long id, Project project) throws SQLException {
        projectDao.updateProject(id, project);
    }

    public void deleteProject(Long id) throws SQLException {
        projectDao.deleteProject(id);
    }

    public List<Project> getAllProjects() throws SQLException {
        return projectDao.getAllProjects();
    }

    public Project getProjectById(Long id) throws SQLException {
        return projectDao.getProjectById(id);
    }

    public void addTechnicalTask(TechnicalTask task) throws SQLException {
        technicalTaskDao.addTechnicalTask(task);
    }

    public void updateTechnicalTask(Long id, TechnicalTask task) throws SQLException {
        technicalTaskDao.updateTechnicalTask(id, task);
    }

    public void deleteTechnicalTask(Long id) throws SQLException {
        technicalTaskDao.deleteTechnicalTask(id);
    }

    public List<TechnicalTask> getAllTechnicalTasks() throws SQLException {
        return technicalTaskDao.getAllTechnicalTasks();
    }

    public TechnicalTask getTechnicalTaskById(Long id) throws SQLException {
        return technicalTaskDao.getTechnicalTaskById(id);
    }

    public void addStaff(Staff staff) throws SQLException {
        staffDao.addStaff(staff);
    }

    public void updateStaff(Long id, Staff staff) throws SQLException {
        staffDao.updateStaff(id, staff);
    }

    public void deleteStaff(Long id) throws SQLException {
        staffDao.deleteStaff(id);
    }

    public List<Staff> getAllStaff() throws SQLException {
        return staffDao.getAllStaff();
    }

    public Staff getStaffById(Long id) throws SQLException {
        return staffDao.getStaffById(id);
    }

    @Override
    public void close() throws SQLException {
        if (clientDao != null) clientDao.close();
        if (staffDao != null) staffDao.close();
        if (projectDao != null) projectDao.close();
        if (technicalTaskDao != null) technicalTaskDao.close();
    }

    public void assignStaff(TechnicalTask task, Project project, List<Staff> availableStaff) {
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
}
