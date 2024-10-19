package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.ClientDao;
import org.example.dao.ProjectDao;
import org.example.dao.StaffDao;
import org.example.dao.TechnicalTaskDao;
import org.example.entity.Client;
import org.example.entity.Project;
import org.example.entity.Staff;
import org.example.entity.TechnicalTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Lab3PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ClientDao clientDao = new ClientDao();
        ProjectDao projectDao = new ProjectDao();
        StaffDao staffDao = new StaffDao();
        TechnicalTaskDao technicalTaskDao = new TechnicalTaskDao();

        System.out.println(technicalTaskDao.getAllTechnicalTasks());
        System.out.println(technicalTaskDao.getTechnicalTaskById(1L));

//        projectDao.updateProject();
//        System.out.println(projectDao.getAllProjects());

        entityManager.close();
        entityManagerFactory.close();
    }
}