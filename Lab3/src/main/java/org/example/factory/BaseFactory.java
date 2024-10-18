package org.example.factory;


import org.example.entity.Client;
import org.example.entity.Project;
import org.example.entity.Staff;
import org.example.entity.TechnicalTask;

import java.util.Date;

public abstract class BaseFactory {
    public abstract Staff createStaff(String name, String surname, String qualification, double salary);
    public abstract Project createProject(String title, Date date);
    public abstract TechnicalTask createTechnicalTask(String description, Client client);
    public abstract Client createClient(String name, String surname);
}
