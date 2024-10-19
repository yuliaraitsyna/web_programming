package org.example.entity;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "TechnicalTasks")
public class TechnicalTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ElementCollection
    @CollectionTable(name = "required_staff", joinColumns = @JoinColumn(name = "task_id"))
    @MapKeyColumn(name = "qualification")
    @Column(name = "quantity")
    private Map<String, Integer> requiredStaff;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public TechnicalTask() {
        // Required by JPA
    }

    public TechnicalTask(String description, Client client) {
        this.description = description;
        this.requiredStaff = new HashMap<>();
        this.client = new Client(client.getName(), client.getSurname());
    }

    public void addRequiredStaff(String qualification, int quantity) {
        requiredStaff.put(qualification, quantity);
    }

    public Map<String, Integer> getRequiredStaff() {
        return requiredStaff;
    }

    public Client getClient() {
        return client;
    }

    public String getDescription() {
        return description;
    }

    public void setRequiredStaff(Map<String, Integer> requiredStaff) {
        this.requiredStaff = requiredStaff;
    }

    @Override
    public String toString() {
        return "TechnicalTask{" +
                "description='" + description + '\'' +
                ", requiredWorkers=" + requiredStaff +
                ", client=" + client.toString() +
                '}';
    }
}
