package org.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {
    private final String title;
    private Date date;
    private final List<Staff> assignedStaff;
    private double cost;
    private TechnicalTask assignedTask;

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

    public TechnicalTask getAssignedTask() {
        return assignedTask;
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

        staffInfo.append(String.format("%-20s %-20s %-10s\n", "Name", "Role", "Salary"));
        staffInfo.append("----------------------------------------------------------\n");

        for (Staff s : assignedStaff) {
            staffInfo.append(String.format("%-20s %-20s %-10.2f\n", s.getName(), s.getQualification(), s.getSalary()));
        }

        return String.format(
                "Project Details:\n" +
                        "----------------------------------------------------------\n" +
                        "Title: %-30s\n" +
                        "Date: %-30s\n" +
                        "Cost: %-30.2f\n" +
                        "Assigned Staff:\n%s\n" +
                        "Technical Task:\n%s\n",
                title, date, cost, staffInfo.toString(), assignedTask != null ? assignedTask.toString() : "No technical task assigned"
        );
    }


    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setAssignedTask(TechnicalTask technicalTask) {
        assignedTask = technicalTask;
    }
}
