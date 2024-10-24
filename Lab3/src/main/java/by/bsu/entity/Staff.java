package by.bsu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "salary")
    private double salary;

    @Column(name = "is_busy")
    private boolean isBusy;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Staff() {

    }

    public Staff(String name, String surname, String qualification, double salary) {
        this.name = name;
        this.surname = surname;
        this.qualification = qualification;
        this.salary = salary;
        this.isBusy = false;
    }

    public String getQualification() {
        return qualification;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setSalary(double newSalary) {
        salary = newSalary;
    }

    public void setQualification(String newQualification) {
        qualification = newQualification;
    }

    public void setBusy(boolean status) {
        isBusy = status;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", qualification='" + qualification + '\'' +
                ", salary=" + salary +
                ", isBusy=" + isBusy +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
