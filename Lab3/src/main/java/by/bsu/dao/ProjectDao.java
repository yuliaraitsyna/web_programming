package by.bsu.dao;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import by.bsu.entity.Project;
import by.bsu.factory.BaseFactory;
import by.bsu.factory.Factory;

import java.util.List;

public class ProjectDao extends DAO {
    private BaseFactory factory;

    public ProjectDao() {
        super();
        this.factory = new Factory();
    }

    public void addProject(Project project) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(project);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateProject(Long id, Project updatedProject) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Project project = entityManager.find(Project.class, id);
            if (project != null) {
                project.setTitle(updatedProject.getTitle());
                project.setDate(updatedProject.getDate());
                entityManager.merge(project);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteProject(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Project project = entityManager.find(Project.class, id);
            entityManager.remove(project);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Project getProjectById(Long id) {
        EntityTransaction transaction = null;
        Project project = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            TypedQuery<Project> query = entityManager.createNamedQuery("Project.findById", Project.class);
            query.setParameter("id", id);
            project = query.getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return project;
    }

    public List<Project> getAllProjects() {
        EntityTransaction transaction = null;
        List<Project> projects = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            TypedQuery<Project> query = entityManager.createNamedQuery("Project.findAll", Project.class);
            projects = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return projects;
    }
}
