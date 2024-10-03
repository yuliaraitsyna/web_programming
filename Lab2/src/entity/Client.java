package entity;

public class Client {
    private String name;
    private String surname;

    // Constructor
    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    // Getters
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
}
