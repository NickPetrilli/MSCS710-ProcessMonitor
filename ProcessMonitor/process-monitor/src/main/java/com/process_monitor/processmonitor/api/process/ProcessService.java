package com.process_monitor.processmonitor.api.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.process_monitor.processmonitor.api.process.model.Process;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for responding to Process requests.
 */
@Service
public class ProcessService {

    private static final Logger logger = LoggerFactory.getLogger(ProcessService.class);

    // Database URL
    private final String URL = "jdbc:sqlite:ProcessMonitor.db";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     * Retrieves list of all recent processes.
     * @return List of processes
     */
    public List<Process> getProcesses() {
        List<Process> processList = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL);

            // Create select query - get all attributes for the latest timestamp
            String sql = """
                    SELECT *
                    FROM process
                    WHERE timestamp = (
                        SELECT MAX(timestamp)
                        FROM process
                    )
                    """;

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                processList.add(new Process(
                        resultSet.getInt("process_id"),
                        resultSet.getString("timestamp"),
                        resultSet.getString("name"),
                        resultSet.getString("status"),
                        resultSet.getDouble("cpuPercentage"),
                        resultSet.getLong("memUsageBytes"),
                        resultSet.getDouble("memPercentage"),
                        resultSet.getDouble("diskSpeed"),
                        resultSet.getDouble("diskPercentage")
                ));
            }

        } catch (SQLException e) {
            logger.error("Error selecting CPU data.");
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

        return processList.isEmpty() ? null : processList;
    }

    /**
     * Gets a all process entries stored in database with specific name.
     * @param name Name of process (ex: chrome)
     * @return List of processes with specified name
     */
    public List<Process> getProcessByName(String name) {
        List<Process> processList = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL);

            // Create select query - get all process information via name
            String sql = "SELECT * FROM process WHERE name = \"" + name + "\""
                    + " ORDER BY timestamp";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                processList.add(new Process(
                        resultSet.getInt("process_id"),
                        resultSet.getString("timestamp"),
                        resultSet.getString("name"),
                        resultSet.getString("status"),
                        resultSet.getDouble("cpuPercentage"),
                        resultSet.getLong("memUsageBytes"),
                        resultSet.getDouble("memPercentage"),
                        resultSet.getDouble("diskSpeed"),
                        resultSet.getDouble("diskPercentage")
                ));
            }

        } catch (SQLException e) {
            logger.error("Error selecting CPU data.");
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

        return processList.isEmpty() ? null : processList;
    }
}
