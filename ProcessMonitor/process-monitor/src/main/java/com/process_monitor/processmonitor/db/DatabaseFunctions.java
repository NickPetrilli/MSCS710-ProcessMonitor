package com.process_monitor.processmonitor.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseFunctions {
        
    // Database URL
    private static final String URL = "jdbc:sqlite:ProcessMonitor.db";

    static Connection connection = null;
    static PreparedStatement preparedStatement = null;

    public static void insertCpuData(String timestamp, String name, long speed, long maxSpeed, int cores, int processes, int threads, double utilization) {

        try {
            // Connect to the database
            connection = DriverManager.getConnection(URL);

            // Create statement and insert into cpu
            String cpuQuery = "INSERT INTO cpu (timestamp, name, speed, maxSpeed, cores, processes, threads) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(cpuQuery);
            // Set values for the parameters
            preparedStatement.setString(1, timestamp);
            preparedStatement.setString(2, name);
            preparedStatement.setLong(3, speed);
            preparedStatement.setLong(4, maxSpeed);
            preparedStatement.setInt(5, cores);
            preparedStatement.setInt(6, processes);
            preparedStatement.setInt(7, threads);
            preparedStatement.setDouble(8, utilization);
            //Execute query
            preparedStatement.executeUpdate();
            System.out.println("CPU data inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Error inserting CPU data.");
            e.printStackTrace();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
            
    }
}
