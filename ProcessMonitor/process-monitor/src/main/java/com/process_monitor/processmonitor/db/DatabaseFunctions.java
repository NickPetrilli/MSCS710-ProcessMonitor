package com.process_monitor.processmonitor.db;

import com.process_monitor.processmonitor.api.memory.model.Memory;
import com.process_monitor.processmonitor.api.cpu.model.Cpu;
import com.process_monitor.processmonitor.api.disk.model.Disk;
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
     * @param cpu Cpu object containing cpu metrics of the user's computer.
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

            // Create statement and insert into process
            String processQuery = """
                    INSERT INTO
                        process (process_id, timestamp, name, status, cpuPercentage, memUsageBytes, memPercentage, diskSpeed, diskPercentage)
                    VALUES
                        (?, ?, ?, ?, ?, ?, ?, ?, ?);
                    """;

            preparedStatement = connection.prepareStatement(processQuery);

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

    /**
     * Inserts Memory metrics into the database.
     * @param memory Memory object containing phsyical memory metrics of the user's computer.
     */
    public void insertMemory(Memory memory) {
        try {
            // Connect to the database
            connection = DriverManager.getConnection(URL);

            // Create statement and insert into memory
            String memoryQuery = """
                    INSERT INTO
                        memory (timestamp, totalMemory, availableMemory, usedMemory, utilization)
                    VALUES
                        (?, ?, ?, ?, ?);
                    """;

            preparedStatement = connection.prepareStatement(memoryQuery);

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

    /**
     * Inserts Disk metrics into the database.
     * @param disk Disk object containing disk metrics of the user's computer.
     */
    public void insertDisk(Disk disk, double diskUtilization) {
        try {
            // Connect to the database
            connection = DriverManager.getConnection(URL);

            // Create statement and insert into disk
            String diskQuery = """
                    INSERT INTO
                        disk (timestamp, name, model, swapTotal, swapUsed, swapUtilization, totalReadBytes, totalWriteBytes, readSpeed, writeSpeed, utilization)
                    VALUES
                        (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                    """;

            preparedStatement = connection.prepareStatement(diskQuery);

            preparedStatement.setString(1, timestamp);
            preparedStatement.setString(2, disk.getName());
            preparedStatement.setString(3, disk.getModel());
            preparedStatement.setLong(4, disk.getSwapTotal());
            preparedStatement.setLong(5, disk.getSwapUsed());
            preparedStatement.setDouble(6, disk.getSwapUtilization());
            preparedStatement.setLong(7, disk.getTotalReadBytes());
            preparedStatement.setLong(8, disk.getTotalWriteBytes());
            preparedStatement.setLong(9, disk.getReadSpeed());
            preparedStatement.setLong(10, disk.getWriteSpeed());
            preparedStatement.setDouble(11, diskUtilization);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error inserting disk data.");
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
