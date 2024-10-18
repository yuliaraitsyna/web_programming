package org.example.factory;

import org.example.entity.Client;
import org.example.entity.Project;
import org.example.entity.Staff;
import org.example.entity.TechnicalTask;

import java.util.Date;

public class Factory extends BaseFactory {
    @Override
    public Client createClient(String name, String surname) {
        return new Client(name, surname);
    }

    @Override
    public Staff createStaff(String name, String surname, String qualification, double salary) {
        return new Staff(name, surname, qualification, salary);
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
