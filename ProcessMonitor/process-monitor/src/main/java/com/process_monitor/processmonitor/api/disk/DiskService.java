package com.process_monitor.processmonitor.api.disk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.process_monitor.processmonitor.api.process.model.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.process_monitor.processmonitor.api.disk.model.Disk;

/**
 * Service class for responding to Disk API requests.
 */
@Service
public class DiskService {

    private static final Logger logger = LoggerFactory.getLogger(DiskService.class);
    // Database URL
    private final String URL = "jdbc:sqlite:ProcessMonitor.db";
    private ResultSet resultSet = null;

    /**
     * Retrieves most recent disk information.
     * @return List of disks on user's machine
     */
    public List<Disk> getDiskData() {
        List<Disk> disks = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            // Create select query - get all attributes for the latest timestamp
            String diskSelectQuery = """
                    SELECT *
                    FROM disk
                    WHERE timestamp = (
                        SELECT MAX(timestamp)
                        FROM disk
                    )
                    """;

            resultSet = statement.executeQuery(diskSelectQuery);

            while (resultSet.next()) {
                disks.add(new Disk(
                        resultSet.getString("timestamp"),
                        resultSet.getString("name"),
                        resultSet.getString("model"),
                        resultSet.getLong("swapTotal"),
                        resultSet.getLong("swapUsed"),
                        resultSet.getDouble("swapUtilization"),
                        resultSet.getLong("totalReadBytes"),
                        resultSet.getLong("totalWriteBytes"),
                        resultSet.getLong("readSpeed"),
                        resultSet.getLong("writeSpeed"),
                        resultSet.getDouble("utilization"))
                );
            }

        } catch (SQLException e) {
            logger.error("Error selecting Disk data.");
        }

        return disks.isEmpty() ? null : disks;
    }


    /**
     * Retrieves list of top 3 processes based on diskUsage (diskPercentage).
     * @return List of Processes
     */
    public List<Process> getTopProcessesByDiskUsage() {
        List<Process> processList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            String sql = """
                    SELECT *
                    FROM process
                    WHERE timestamp = (SELECT MAX(timestamp) FROM process)
                    ORDER BY diskPercentage DESC
                    LIMIT 3;
                    """;

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
            logger.error("Error while getting top-processes via DiskUsage.");
        }

        return processList.isEmpty() ? null : processList;
    }


    /**
     * Retrieves fuller list processes based on diskUsage (diskSpeed).
     * @return List of Processes
     */
    public List<Process> getProcessesByDiskUsage() {
        List<Process> processList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            String sql = """
                    SELECT *
                    FROM process
                    WHERE timestamp = (SELECT MAX(timestamp) FROM process)
                    ORDER BY diskSpeed DESC
                    LIMIT 50;
                    """;

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
            logger.error("Error while getting top-processes via DiskUsage.");
        }

        return processList.isEmpty() ? null : processList;
    }

    /**
     * Retrieves Memory utilization chart metrics from the past 3 minutes
     * @param name Disk name
     * @return List of utilization metrics for the specified disk
     */
    public List<Double> getUtilizationMetrics(String name) {
        List<Double> utilizationList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            String sql = """
                        SELECT 
                            timestamp, utilization 
                        FROM 
                            disk
                        WHERE 
                            timestamp BETWEEN datetime('now', 'localtime', '-3 minutes') AND datetime('now', 'localtime')
                            AND name = '
                             """ + name + "';";

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                utilizationList.add(resultSet.getDouble("utilization"));
            }

        } catch (SQLException e) {
            logger.error("Error while getting disk chart metrics");
        }

        return utilizationList.isEmpty() ? null : utilizationList;
    }
}
