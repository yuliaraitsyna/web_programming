package main;

import dao.JdbcConnector;
import entity.*;
import factory.BaseFactory;
import factory.Factory;
import strategy.Searcher;
import strategy.Sorting;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = JdbcConnector.getConnection()) {
            System.out.println("Connected to the database!");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        BaseFactory factory = new Factory();
        Admin admin = new Admin();
    }
}
