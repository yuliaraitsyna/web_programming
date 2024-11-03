package by.bsu.controller;

import by.bsu.dao.ClientDao;
import by.bsu.dao.ProjectDao;
import by.bsu.dao.StaffDao;
import by.bsu.dao.TechnicalTaskDao;
import by.bsu.entity.Client;
import by.bsu.entity.Project;
import by.bsu.entity.Staff;
import by.bsu.entity.TechnicalTask;
import by.bsu.exceptions.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements AutoCloseable {

    private static final Logger logger = Logger.getLogger(Controller.class.getName());

    private ClientDao clientDao;
    private StaffDao staffDao;
    private ProjectDao projectDao;
    private TechnicalTaskDao technicalTaskDao;

    public Controller() throws DaoException {
        clientDao = new ClientDao();
        staffDao = new StaffDao();
        projectDao = new ProjectDao();
        technicalTaskDao = new TechnicalTaskDao();
    }

    public void addClient(Client client) {
        try {
            clientDao.addClient(client);
            logger.info("Client added successfully: " + client);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to add client: " + e.getMessage(), e);
        }
    }

    public void updateClient(Long id, Client client) {
        try {
            clientDao.updateClient(id, client);
            logger.info("Client updated successfully with ID " + id);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to update client with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public void deleteClient(Long id) {
        try {
            clientDao.deleteClient(id);
            logger.info("Client deleted successfully with ID " + id);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to delete client with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public List<Client> getAllClients() {
        try {
            return clientDao.getAllClients();
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to retrieve clients: " + e.getMessage(), e);
            return List.of();
        }
    }

    public Client getClientById(Long id) {
        try {
            return clientDao.getClientById(id);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to retrieve client with ID " + id + ": " + e.getMessage(), e);
            return null;
        }
    }

    public void addProject(Project project) {
        try {
            projectDao.addProject(project);
            logger.info("Project added successfully: " + project);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to add project: " + e.getMessage(), e);
        }
    }

    public void updateProject(Long id, Project project) {
        try {
            projectDao.updateProject(id, project);
            logger.info("Project updated successfully with ID " + id);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to update project with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public void deleteProject(Long id) {
        try {
            projectDao.deleteProject(id);
            logger.info("Project deleted successfully with ID " + id);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to delete project with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public List<Project> getAllProjects() {
        try {
            return projectDao.getAllProjects();
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to retrieve projects: " + e.getMessage(), e);
            return List.of();
        }
    }

    public Project getProjectById(Long id) {
        try {
            return projectDao.getProjectById(id);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to retrieve project with ID " + id + ": " + e.getMessage(), e);
            return null;
        }
    }

    public void addTechnicalTask(TechnicalTask task) {
        try {
            technicalTaskDao.addTechnicalTask(task);
            logger.info("Technical task added successfully: " + task);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to add technical task: " + e.getMessage(), e);
        }
    }

    public void updateTechnicalTask(Long id, TechnicalTask task) {
        try {
            technicalTaskDao.updateTechnicalTask(id, task);
            logger.info("Technical task updated successfully with ID " + id);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to update technical task with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public void deleteTechnicalTask(Long id) {
        try {
            technicalTaskDao.deleteTechnicalTask(id);
            logger.info("Technical task deleted successfully with ID " + id);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to delete technical task with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public List<TechnicalTask> getAllTechnicalTasks() {
        try {
            return technicalTaskDao.getAllTechnicalTasks();
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to retrieve technical tasks: " + e.getMessage(), e);
            return List.of();
        }
    }

    public TechnicalTask getTechnicalTaskById(Long id) {
        try {
            return technicalTaskDao.getTechnicalTaskById(id);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to retrieve technical task with ID " + id + ": " + e.getMessage(), e);
            return null;
        }
    }

    public void addStaff(Staff staff) {
        try {
            staffDao.addStaff(staff);
            logger.info("Staff added successfully: " + staff);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to add staff: " + e.getMessage(), e);
        }
    }

    public void updateStaff(Long id, Staff staff) {
        try {
            staffDao.updateStaff(id, staff);
            logger.info("Staff updated successfully with ID " + id);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to update staff with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public void deleteStaff(Long id) {
        try {
            staffDao.deleteStaff(id);
            logger.info("Staff deleted successfully with ID " + id);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to delete staff with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public List<Staff> getAllStaff() {
        try {
            return staffDao.getAllStaff();
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to retrieve staff: " + e.getMessage(), e);
            return List.of();
        }
    }

    public Staff getStaffById(Long id) {
        try {
            return staffDao.getStaffById(id);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to retrieve staff with ID " + id + ": " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void close() throws SQLException, DaoException {
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
                logger.log(Level.WARNING, "Not enough staff with " + qualification + " qualification for task: " + task.getDescription());
            }
        }
    }
}
