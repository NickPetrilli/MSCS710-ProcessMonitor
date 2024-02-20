package com.process_monitor.processmonitor.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class for initializing the database tables
 */
public class DatabaseInit {

    // Database URL
    private final String URL = "jdbc:sqlite:ProcessMonitor.db";

    // Create table SQL statements
    private final String CREATE_PROCESS_TABLE_SQL = "CREATE TABLE IF NOT EXISTS process ("
            + "process_id INTEGER,"
            + "timestamp TEXT,"
            + "name TEXT,"
            + "status TEXT,"
            + "cpuPercentage REAL,"
            + "memUsageBytes INTEGER,"
            + "memPercentage REAL,"
            + "diskSpeed REAL,"
            + "diskPercentage REAL,"
            + "PRIMARY KEY (process_id, timestamp)" 
            + ")";

    private final String CREATE_CPU_TABLE_SQL = "CREATE TABLE IF NOT EXISTS cpu ("
            + "timestamp TEXT PRIMARY KEY,"
            + "name TEXT,"
            + "speed INTEGER,"
            + "maxSpeed INTEGER,"
            + "cores INTEGER,"
            + "processes INTEGER,"
            + "threads INTEGER,"
            + "utilization REAL"
            + ")";    

    private final String CREATE_MEMORY_TABLE_SQL = "CREATE TABLE IF NOT EXISTS memory ("
            + "timestamp TEXT PRIMARY KEY,"
            + "totalMemory INTEGER,"
            + "availableMemory INTEGER,"
            + "usedMemory INTEGER,"
            + "utilization REAL"
            + ")";
            
    private final String CREATE_DISK_TABLE_SQL = "CREATE TABLE IF NOT EXISTS disk ("
            + "timestamp TEXT,"
            + "name TEXT,"
            + "model TEXT,"
            + "swapTotal INTEGER,"
            + "swapUsed INTEGER,"
            + "swapUtilization REAL,"
            + "totalReadBytes INTEGER,"
            + "totalWriteBytes INTEGER,"
            + "readSpeed INTEGER,"
            + "writeSpeed INTEGER,"
            + "utilization REAL,"
            + "PRIMARY KEY (timestamp, name)" 
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

    public static void main(String[] args) {
        DatabaseInit db = new DatabaseInit();
        db.createTables();
    }
}
