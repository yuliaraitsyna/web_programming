package entity;

import java.util.ArrayList;
import java.util.Map;

public class Admin {
    public void assignStaffToProject(TechnicalTask task, Project project, ArrayList<Staff> availableStaff) {
        Map<String, Integer> requiredStaff = task.getRequiredStaff();
        for (Map.Entry<String, Integer> entry : requiredStaff.entrySet()) {
            String qualification = entry.getKey();
            Integer number = entry.getValue();

            for (Staff staff : availableStaff) {
                if (!staff.isBusy() && staff.getQualification().equals(qualification) && number > 0) {
                    project.assignStaff(staff);
                    staff.setBusy(true);
                    number--;
                }
            }

            if (number > 0) {
                System.out.println("Not enough staff with " + qualification + " qualification for task: " + task.getDescription());
            }
        }
    }
}
