package factory;

import entity.Client;
import entity.Project;
import entity.Staff;
import entity.TechnicalTask;

import java.util.Date;

public class Factory extends BaseFactory {
    @Override
    public Client createClient(String name, String surname) {
        return new Client(name, surname);
    }

    @Override
    public Staff createStaff(String fullName, String qualification, double salary) {
        return new Staff(fullName, qualification, salary);
    }

    @Override
    public Project createProject(String title, Date startDate) {
        return new Project(title, startDate);
    }

    @Override
    public TechnicalTask createTechnicalTask(String description, Client client) {
        return new TechnicalTask(description, client);
    }
}
