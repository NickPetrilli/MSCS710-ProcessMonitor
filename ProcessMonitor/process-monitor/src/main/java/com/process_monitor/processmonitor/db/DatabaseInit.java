package com.process_monitor.processmonitor.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInit {

    // Database URL
    private static final String URL = "jdbc:sqlite:ProcessMonitor.db";

    // Create table SQL statement
    private static final String CREATE_PROCESS_TABLE_SQL = "CREATE TABLE IF NOT EXISTS process ("
            + "process_id INTEGER,"
            + "timestamp TEXT,"
            + "name TEXT,"
            + "status TEXT,"
            + "cpuUsage REAL,"
            + "memUsage REAL,"
            + "bytesRead REAL,"
            + "bytesWritten REAL,"
            + "PRIMARY KEY (process_id, timestamp)" 
            + ")";

    private static final String CREATE_CPU_TABLE_SQL = "CREATE TABLE IF NOT EXISTS cpu ("
            + "timestamp TEXT PRIMARY KEY,"
            + "name TEXT,"
            + "speed REAL,"
            + "maxSpeed REAL,"
            + "cores INTEGER,"
            + "processes INTEGER,"
            + "threads INTEGER,"
            + "utilization REAL"
            + ")";    

    private static final String CREATE_MEMORY_TABLE_SQL = "CREATE TABLE IF NOT EXISTS memory ("
            + "timestamp TEXT PRIMARY KEY,"
            + "totalMemory REAL,"
            + "usedMemory REAL,"
            + "utilization REAL"
            + ")";
            
    private static final String CREATE_DISK_TABLE_SQL = "CREATE TABLE IF NOT EXISTS disk ("
            + "timestamp TEXT PRIMARY KEY,"
            + "name TEXT,"
            + "model TEXT,"
            + "totalReadBytes REAL,"
            + "totalWriteBytes REAL,"
            + "transferTime REAL"
            + ")";

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public void createTables() {

        try {
            // Connect to the database
            connection = DriverManager.getConnection(URL);

            // Create the tables
            preparedStatement = connection.prepareStatement(CREATE_PROCESS_TABLE_SQL);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(CREATE_CPU_TABLE_SQL);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(CREATE_MEMORY_TABLE_SQL);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(CREATE_DISK_TABLE_SQL);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
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

    public void insertStatement() {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String insertCpu = "INSERT INTO cpu VALUES (1, 'test', 100, 200)";
        try {
            preparedStatement = connection.prepareStatement(insertCpu);
            preparedStatement.executeUpdate();
            System.out.println("Insertion completed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        DatabaseInit db = new DatabaseInit();
        db.createTables();
        //db.insertStatement();

    }
}
