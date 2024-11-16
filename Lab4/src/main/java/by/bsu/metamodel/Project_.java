package by.bsu.metamodel;

import by.bsu.entity.Project;
import by.bsu.entity.Staff;
import by.bsu.entity.TechnicalTask;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import java.util.Date;

@StaticMetamodel(Project.class)
public class Project_ {
    public static volatile SingularAttribute<Project, Long> id;
    public static volatile SingularAttribute<Project, String> title;
    public static volatile SingularAttribute<Project, Date> date;
    public static volatile ListAttribute<Project, Staff> assignedStaff;
    public static volatile SingularAttribute<Project, Double> cost;
    public static volatile SingularAttribute<Project, TechnicalTask> assignedTask;
}
