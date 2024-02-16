package com.process_monitor.processmonitor.db;

import com.process_monitor.processmonitor.api.process.model.Process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseFunctions {
        
    // Database URL
    private final String URL = "jdbc:sqlite:ProcessMonitor.db";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String timestamp = "";

    /**
     * Constructor for DatabaseFunctions object.
     * @param timestamp Initializes the timestamp a time, usually the LocalDateTime.now() value is passed as a string.
     */
    public DatabaseFunctions(String timestamp) {
        this.timestamp = timestamp;
    }


    /**
     * Inserts CPU metrics into the database.
     * @param name Name of the computer's processor
     * @param speed Speed of CPU
     * @param maxSpeed Max speed of CPU
     * @param cores Total cores in the computer's processor
     * @param processes Total processes running
     * @param threads Number of threads used on computer
     * @param utilization CPU utilization
     */
    public void insertCpuData(String name,
                              long speed,
                              long maxSpeed,
                              int cores,
                              int processes,
                              int threads,
                              double utilization) {

        try {
            // Connect to the database
            connection = DriverManager.getConnection(URL);

            // Create statement and insert into cpu
            String cpuQuery = """
                    INSERT INTO
                        cpu (timestamp, name, speed, maxSpeed, cores, processes, threads)
                    VALUES
                        (?, ?, ?, ?, ?, ?, ?);
                    """;

            preparedStatement = connection.prepareStatement(cpuQuery);
            // Set values for the parameters
            preparedStatement.setString(1, timestamp);
            preparedStatement.setString(2, name);
            preparedStatement.setLong(3, speed);
            preparedStatement.setLong(4, maxSpeed);
            preparedStatement.setInt(5, cores);
            preparedStatement.setInt(6, processes);
            preparedStatement.setInt(7, threads);

            // NOT INCLUDED IN SQL INSERT STATEMENT
            // preparedStatement.setDouble(8, utilization);
            //Execute query
            preparedStatement.executeUpdate();

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


    /**
     * Inserts process based metrics into the database.
     * @param process Process object containing metrics of a single process running on the user's computer.
     */
    public void insertProcess(Process process) {
        try {
            // Connect to the database
            connection = DriverManager.getConnection(URL);

            // Create statement and insert into cpu
            String sql = """
                    INSERT INTO
                        process (timestamp, name, status, cpuUsage, memUsage, bytesRead, bytesWritten)
                    VALUES
                        (?, ?, ?, ?, ?, ?, ?);
                    """;

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, timestamp);
            preparedStatement.setString(2, process.getName());
            preparedStatement.setString(3, process.getStatus());
            preparedStatement.setDouble(4, process.getCpuUsage());
            preparedStatement.setLong(5, process.getMemoryUsage());
            preparedStatement.setLong(6, process.getBytesRead());
            preparedStatement.setLong(7, process.getBytesWritten());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting process data.");
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
