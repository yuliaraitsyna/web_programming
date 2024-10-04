package entity;

import java.util.HashMap;
import java.util.Map;

public class TechnicalTask {
    private final String description;
    private Map<String, Integer> requiredStaff;
    private final Client client;

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
