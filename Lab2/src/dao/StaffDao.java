package dao;

import entity.Staff;
import factory.BaseFactory;
import factory.Factory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
    private static final String INSERT_STAFF_SQL = "INSERT INTO staff (name, surname, qualification, salary) VALUES (?, ?, ?, ?)";
    private static final String SELECT_STAFF_BY_ID = "SELECT * FROM staff WHERE id = ?";
    private static final String SELECT_ALL_STAFF = "SELECT * FROM staff";

    private Connection connection;
    private BaseFactory factory;

    public StaffDao(Connection connection) {
        this.connection = connection;
        this.factory = new Factory();
    }

    public void addStaff(Staff staff) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STAFF_SQL)) {
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getSurname());
            preparedStatement.setString(3, staff.getQualification());
            preparedStatement.setDouble(4, staff.getSalary());
            preparedStatement.executeUpdate();
        }
    }

    public Staff getStaffById(int id) throws SQLException {
        Staff staff = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String qualification = resultSet.getString("qualification");
                double salary = resultSet.getDouble("salary");

                staff = factory.createStaff(name, surname, qualification, salary);
            }
        }
        return staff;
    }

    // Get a list of all staff members
    public List<Staff> getAllStaff() throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STAFF)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String qualification = resultSet.getString("qualification");
                double salary = resultSet.getDouble("salary");

                Staff staff = factory.createStaff(name, surname, qualification, salary);
                staffList.add(staff);
            }
        }
        return staffList;
    }
}
