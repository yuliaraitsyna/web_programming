package by.bsu.metamodel;

import by.bsu.entity.Client;
import by.bsu.entity.TechnicalTask;
import jakarta.persistence.metamodel.MapAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TechnicalTask.class)
public class TechnicalTask_ {
    public static volatile SingularAttribute<TechnicalTask, Long> id;
    public static volatile SingularAttribute<TechnicalTask, String> description;
    public static volatile MapAttribute<TechnicalTask, String, Integer> requiredStaff;
    public static volatile SingularAttribute<TechnicalTask, Client> client;
}
