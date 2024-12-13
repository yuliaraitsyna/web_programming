package by.bsu.lab6.entity;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Entity
@Table(name = "TechnicalTask", schema = "dbo")
public class TechnicalTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "RequiredStaff", joinColumns = @JoinColumn(name = "tech_task_id"))
    @MapKeyColumn(name = "role")
    @Column(name = "amount")
    private Map<String, Integer> requiredStaff;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public TechnicalTask() {
        this.requiredStaff = new HashMap<>();
    }

    public TechnicalTask(String description, Client client) {
        this.description = description;
        this.requiredStaff = new HashMap<>();
        this.client = new Client(client.getId(), client.getName(), client.getSurname());
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
                ", requiredStaff=" + requiredStaff +
                ", client=" + client.toString() +
                '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
