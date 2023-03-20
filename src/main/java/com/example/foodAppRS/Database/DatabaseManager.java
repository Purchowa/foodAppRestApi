package com.example.foodAppRS.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private Connection connection;

    public Connection connectDB() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://195.150.230.208:5432/2023_wolek_dawid", "2023_wolek_dawid", "35244");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

}