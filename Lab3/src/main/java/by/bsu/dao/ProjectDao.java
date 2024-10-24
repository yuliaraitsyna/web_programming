package by.bsu.dao;

import by.bsu.metamodel.Project_;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import by.bsu.entity.Project;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ProjectDao extends DAO {

    public ProjectDao() {
        super();
    }

    public void addProject(Project project) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(project);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateProject(Long id, Project updatedProject) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            CriteriaUpdate<Project> update = criteriaBuilder.createCriteriaUpdate(Project.class);
            Root<Project> root = update.from(Project.class);

            update.set(root.get(Project_.title), updatedProject.getTitle())
                    .set(root.get(Project_.date), updatedProject.getDate())
                    .where(criteriaBuilder.equal(root.get(Project_.id), id));

            entityManager.createQuery(update).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteProject(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CriteriaDelete<Project> delete = criteriaBuilder.createCriteriaDelete(Project.class);
            Root<Project> root = delete.from(Project.class);
            delete.where(criteriaBuilder.equal(root.get(Project_.id), id));
            entityManager.createQuery(delete).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Project getProjectById(Long id) {
        CriteriaQuery<Project> query = criteriaBuilder.createQuery(Project.class);
        Root<Project> root = query.from(Project.class);
        query.select(root).where(criteriaBuilder.equal(root.get(Project_.id), id));

        return entityManager.createQuery(query).getSingleResult();
    }

    public List<Project> getAllProjects() {
        CriteriaQuery<Project> query = criteriaBuilder.createQuery(Project.class);
        Root<Project> root = query.from(Project.class);
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }

}
