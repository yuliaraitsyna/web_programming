package entity;

public class Staff {
    private String name;             // Field for name
    private String surname;
    private String qualification;
    private double salary;
    private boolean isBusy;

    public Staff(String fullName, String qualification, double salary) {
        this.name = fullName.split(" ")[0];
        this.surname = fullName.split(" ")[1];
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
}
