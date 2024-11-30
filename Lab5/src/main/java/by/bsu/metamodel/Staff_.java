package by.bsu.metamodel;

import by.bsu.entity.Project;
import by.bsu.entity.Staff;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Staff.class)
public class Staff_ {
    public static volatile SingularAttribute<Staff, Long> id;
    public static volatile SingularAttribute<Staff, String> name;
    public static volatile SingularAttribute<Staff, String> surname;
    public static volatile SingularAttribute<Staff, String> qualification;
    public static volatile SingularAttribute<Staff, Double> salary;
    public static volatile SingularAttribute<Staff, Boolean> isBusy;
    public static volatile SingularAttribute<Staff, Project> project;
}
