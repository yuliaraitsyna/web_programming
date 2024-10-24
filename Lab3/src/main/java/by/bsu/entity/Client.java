package by.bsu.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Client")
@NamedQueries({
        @NamedQuery(name = "Client.findById",
                query = "SELECT c FROM Client c WHERE c.id = :id"),
        @NamedQuery(name = "Client.findAll",
                query = "SELECT c FROM Client c")
})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    public Client() {

    }

    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
