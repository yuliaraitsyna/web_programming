package entity;

import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {
    private final String title;
    Date date;
    private final List<Staff> assignedStaff;
    private double cost;

    public Project(String title, Date date) {
        this.title = title;
        this.date = date;
        this.cost = 0;
        this.assignedStaff = new ArrayList<Staff>();
    }

    protected void assignStaff(Staff s) {
        assignedStaff.add(s);
        s.setBusy(true);
        cost += s.getSalary();
    }

    protected void removeStaff(Staff s) {
        if (assignedStaff.remove(s)) {
            s.setBusy(false);
            cost -= s.getSalary();
        }
    }

    public double getCost() {
        return cost;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public List<Staff> getAssignedStaff() {
        return assignedStaff;
    }


    @Override
    public String toString() {
        StringBuilder staffInfo = new StringBuilder();
        for (Staff s : assignedStaff) {
            staffInfo.append(s.toString()).append("\n");
        }

        return "Project{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", cost=" + cost +
                ", assignedStaff=\n" + staffInfo +
                '}';
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
