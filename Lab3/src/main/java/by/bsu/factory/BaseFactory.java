package by.bsu.factory;


import by.bsu.entity.Client;
import by.bsu.entity.Project;
import by.bsu.entity.Staff;
import by.bsu.entity.TechnicalTask;

import java.util.Date;

public abstract class BaseFactory {
    public abstract Staff createStaff(String name, String surname, String qualification, double salary);
    public abstract Project createProject(String title, Date date);
    public abstract TechnicalTask createTechnicalTask(String description, Client client);
    public abstract Client createClient(String name, String surname);
}
