package controller;

import dao.ClientDao;
import dao.ProjectDao;
import dao.StaffDao;
import dao.TechnicalTaskDao;
import entity.Client;
import entity.Project;
import entity.Staff;
import entity.TechnicalTask;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

    @Override
    public void close() throws SQLException {
        if (clientDao != null) clientDao.close();
        if (staffDao != null) staffDao.close();
        if (projectDao != null) projectDao.close();
        if (technicalTaskDao != null) technicalTaskDao.close();
    }
}
