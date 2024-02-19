package com.process_monitor.processmonitor.api.memory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.process_monitor.processmonitor.api.memory.model.Memory;

@Service
public class MemoryService {

    private static final Logger logger = LoggerFactory.getLogger(MemoryService.class);

    // Database URL
    private final String URL = "jdbc:sqlite:ProcessMonitor.db";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

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
            statement = connection.createStatement();

            // Execute the query and get the result set
            resultSet = statement.executeQuery(memorySelectQuery);

            while (resultSet.next()) {
                // Retrieve data from the result set
                String timestamp = resultSet.getString("timestamp");
                long totalMemory = resultSet.getLong("totalMemory");
                long availableMemory = resultSet.getLong("availableMemory");
                long usedMemory = resultSet.getLong("usedMemory");
                double utilization = resultSet.getDouble("utilization");
            
                //Creat memory object to pass to MemoryController
                memory = new Memory(timestamp, totalMemory, availableMemory, usedMemory, utilization);
            }

        } catch (SQLException e) {
            logger.error("Error selecting Memory data.");
            e.printStackTrace();
        } finally {
            // Close resources
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return memory;
    }
}
