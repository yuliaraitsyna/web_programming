package factory;

import entity.Client;
import entity.Project;
import entity.Staff;
import entity.TechnicalTask;

import java.util.Date;

public abstract class BaseFactory {
    public abstract Staff createStaff(String fullName, String qualification, double salary);
    public abstract Project createProject(String title, Date date);
    public abstract TechnicalTask createTechnicalTask(String description, Client client);
    public abstract Client createClient(String fullName);
}
