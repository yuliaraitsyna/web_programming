package dao;

import entity.Staff;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
    private static final String SELECT_ALL = "SELECT * FROM staff";
    private static final String INSERT_STAFF = "INSERT INTO staff (name, surname, qualification, salary) VALUES (?, ?, ?, ?)";

    public List<Staff> getAllStaff() throws SQLException, IOException {
        List<Staff> staffList = new ArrayList<>();
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String qualification = rs.getString("qualification");
                double salary = rs.getDouble("salary");

                Staff staff = new Staff(name + " " + surname, qualification, salary);
                staffList.add(staff);
            }
        }
        return staffList;
    }

    public void addStaff(Staff staff) throws SQLException, IOException {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STAFF)) {
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getSurname());
            preparedStatement.setString(3, staff.getQualification());
            preparedStatement.setDouble(4, staff.getSalary());

            preparedStatement.executeUpdate();
        }
    }
}
