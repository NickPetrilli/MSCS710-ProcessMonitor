package com.process_monitor.processmonitor.db;

import com.process_monitor.processmonitor.api.memory.model.Memory;
import com.process_monitor.processmonitor.api.cpu.model.Cpu;
import com.process_monitor.processmonitor.api.process.model.Process;
import com.process_monitor.processmonitor.collector.MetricCollector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseFunctions {
    
    private static final Logger logger = LoggerFactory.getLogger(MetricCollector.class);

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
    public void insertCpu(Cpu cpu) {

        try {
            // Connect to the database
            connection = DriverManager.getConnection(URL);

            // Create statement and insert into cpu
            String cpuQuery = """
                    INSERT INTO
                        cpu (timestamp, name, speed, maxSpeed, cores, processes, threads, utilization)
                    VALUES
                        (?, ?, ?, ?, ?, ?, ?, ?);
                    """;

            preparedStatement = connection.prepareStatement(cpuQuery);
            // Set values for the parameters
            preparedStatement.setString(1, timestamp);
            preparedStatement.setString(2, cpu.getName());
            preparedStatement.setLong(3, cpu.getSpeed());
            preparedStatement.setLong(4, cpu.getMaxSpeed());
            preparedStatement.setInt(5, cpu.getNumCores());
            preparedStatement.setInt(6, cpu.getNumProcesses());
            preparedStatement.setInt(7, cpu.getNumThreads());
            preparedStatement.setDouble(8, cpu.getUtilization());

            //Execute query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error inserting CPU data.");
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
                        process (process_id, timestamp, name, status, cpuPercentage, memUsageBytes, memPercentage, diskSpeed, diskPercentage)
                    VALUES
                        (?, ?, ?, ?, ?, ?, ?, ?, ?);
                    """;

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, process.getId());
            preparedStatement.setString(2, timestamp);
            preparedStatement.setString(3, process.getName());
            preparedStatement.setString(4, process.getStatus());
            preparedStatement.setDouble(5, process.getCpuPercentage());
            preparedStatement.setLong(6, process.getMemoryUsageBytes());
            preparedStatement.setDouble(7, process.getMemoryUsagePercentage());
            preparedStatement.setDouble(8, process.getDiskSpeed());
            preparedStatement.setDouble(9, process.getDiskPercentage());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error inserting process data.");
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

    public void insertMemory(Memory memory) {
        try {
            // Connect to the database
            connection = DriverManager.getConnection(URL);

            // Create statement and insert into cpu
            String sql = """
                    INSERT INTO
                        memory (timestamp, totalMemory, availableMemory, usedMemory, utilization)
                    VALUES
                        (?, ?, ?, ?, ?);
                    """;

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, timestamp);
            preparedStatement.setLong(2, memory.getTotalMemory());
            preparedStatement.setLong(3, memory.getAvailableMemory());
            preparedStatement.setLong(4, memory.getUsedMemory());
            preparedStatement.setDouble(5, memory.getUtilization());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error inserting memory data.");
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
