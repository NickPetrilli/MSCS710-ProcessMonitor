package com.process_monitor.processmonitor.api.memory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.process_monitor.processmonitor.api.memory.model.Memory;
import com.process_monitor.processmonitor.api.process.model.Process;

@Service
public class MemoryService {

    private static final Logger logger = LoggerFactory.getLogger(MemoryService.class);

    // Database URL
    private final String URL = "jdbc:sqlite:ProcessMonitor.db";

    private Connection connection = null;
    private Statement memoryStatement = null;
    private ResultSet memoryResultSet = null;
    private Statement processStatement = null;
    private ResultSet processResultSet = null;

    /**
     * Gets the most recent timestmap memory object 
     * @return Memory object
     */
    public Memory getMemoryData() {
        Memory memory = null;

        try {
            // Connect to the database
            connection = DriverManager.getConnection(URL);

            // Create select query - get all attributes for the latest timestamp
            String memorySelectQuery = """
                    SELECT *
                    FROM memory
                    WHERE timestamp = (
                        SELECT MAX(timestamp)
                        FROM memory
                    )
                    """;

            // Create a SQL statement
            memoryStatement = connection.createStatement();

            // Execute the query and get the result set
            memoryResultSet = memoryStatement.executeQuery(memorySelectQuery);

            while (memoryResultSet.next()) {
                // Retrieve data from the result set
                String timestamp = memoryResultSet.getString("timestamp");
                long totalMemory = memoryResultSet.getLong("totalMemory");
                long availableMemory = memoryResultSet.getLong("availableMemory");
                long usedMemory = memoryResultSet.getLong("usedMemory");
                double utilization = memoryResultSet.getDouble("utilization");
            
                //Creat memory object to pass to MemoryController
                memory = new Memory(timestamp, totalMemory, availableMemory, usedMemory, utilization);
            }

        } catch (SQLException e) {
            logger.error("Error selecting Memory data.");
            e.printStackTrace();
        } finally {
            // Close resources
            if (memoryStatement != null) {
                try {
                    memoryStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return memory;
    }

    /**
     * Gets a list of the top 3 processes based on memory usage in bytes
     * @return list of top 3 processes
     */
    public ArrayList<Process> getMemoryTopProcesses() {
        ArrayList<Process> processList = new ArrayList<>();

        try {
            // Connect to the database
            connection = DriverManager.getConnection(URL);

            // Create select query - get all attributes for the latest timestamp
            String memoryProcessesQuery = """
                    SELECT *
                    FROM process
                    WHERE timestamp = (
                        SELECT MAX(timestamp)
                        FROM process
                    )
                    GROUP BY name
                    ORDER BY memUsageBytes DESC
                    LIMIT 3
                    """;

            // Create a SQL statement
            processStatement = connection.createStatement();

            // Execute the query and get the result set
            processResultSet = processStatement.executeQuery(memoryProcessesQuery);

            while (processResultSet.next()) {
                // Retrieve data from the result set
                int process_id = processResultSet.getInt("process_id");
                String timestamp = processResultSet.getString("timestamp");
                String name = processResultSet.getString("name");
                String status = processResultSet.getString("status");
                double cpuPercentage = processResultSet.getDouble("cpuPercentage");
                long memUsageBytes = processResultSet.getLong("memUsageBytes");
                double memPercentage = processResultSet.getDouble("memPercentage");
                double diskSpeed = processResultSet.getDouble("diskSpeed");
                double diskPercentage = processResultSet.getDouble("diskPercentage");
            
                //Creat memory object to pass to MemoryController
                processList.add(new Process(process_id, timestamp, name, status, cpuPercentage, memUsageBytes, memPercentage, diskSpeed, diskPercentage));
            }

        } catch (SQLException e) {
            logger.error("Error selecting Memory Top 3 Processes data.");
            e.printStackTrace();
        } finally {
            // Close resources
            if (processStatement != null) {
                try {
                    processStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return processList;
    }
}
