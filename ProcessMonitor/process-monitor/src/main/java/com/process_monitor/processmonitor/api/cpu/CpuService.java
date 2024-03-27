package com.process_monitor.processmonitor.api.cpu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.process_monitor.processmonitor.api.util.ChartData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.process_monitor.processmonitor.api.cpu.model.Cpu;
import com.process_monitor.processmonitor.api.process.model.Process;

@Service
public class CpuService {

    private static final Logger logger = LoggerFactory.getLogger(CpuService.class);

    // Database URL
    private final String URL = "jdbc:sqlite:ProcessMonitor.db";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public Cpu getCpuData() {
        Cpu cpu = null;
        try {
            // Connect to the database
            connection = DriverManager.getConnection(URL);

            // Create select query - get all attributes for the latest timestamp
            String cpuSelectQuery = """
                    SELECT *
                    FROM cpu
                    WHERE timestamp = (
                        SELECT MAX(timestamp)
                        FROM cpu
                    )
                    """;

            // Create a SQL statement
            statement = connection.createStatement();

            // Execute the query and get the result set
            resultSet = statement.executeQuery(cpuSelectQuery);

            while (resultSet.next()) {
                // Retrieve data from the result set
                String timestamp = resultSet.getString("timestamp");
                String name = resultSet.getString("name");
                long speed = resultSet.getLong("speed");
                long maxSpeed = resultSet.getLong("maxSpeed");
                int cores = resultSet.getInt("cores");
                int processes = resultSet.getInt("processes");
                int threads = resultSet.getInt("threads");
                double utilization = resultSet.getDouble("utilization");
            
                //Creat cpu object to pass to CpuController
                cpu = new Cpu(timestamp, name, speed, maxSpeed, cores, processes, threads, utilization);
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

        return cpu;
    }


    /**
     * Retrieves list of top 3 processes based on cpu usage (cpuPercentage).
     * @return List of Processes
     */
    public List<Process> getTopProcessesByCpUsage() {
        List<Process> processList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            String sql = """
                    SELECT *
                    FROM process
                    WHERE timestamp = (SELECT MAX(timestamp) FROM process)
                        AND name <> "Idle"
                    ORDER BY cpuPercentage DESC
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
            logger.error("Error while getting top-processes via CpuUsage.");
        }

        return processList.isEmpty() ? null : processList;
    }


    /**
     * Retrieves list of processes based on cpu usage (cpuPercentage).
     * @return List of Processes
     */
    public List<Process> getProcessesOrderByCpuUsage() {
        List<Process> processList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            String sql = """
                    SELECT *
                    FROM process
                    WHERE timestamp = (SELECT MAX(timestamp) FROM process)
                        AND name <> "Idle"
                    ORDER BY cpuPercentage DESC
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
            logger.error("Error while getting processes via Cpu Usage descending.");
        }

        return processList.isEmpty() ? null : processList;
    }

    /**
     * Retrieves list of processes based on cpUsage ascending (cpuPercentage).
     * @return List of Processes
    */
    public List<Process> getProcessesOrderByCpuUsageAsc() {
        List<Process> processList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            String sql = """
                    SELECT *
                    FROM process
                    WHERE timestamp = (SELECT MAX(timestamp) FROM process)
                        AND name <> "Idle"
                    ORDER BY cpuPercentage ASC
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
            logger.error("Error while getting via Cpu Usage ascending.");
        }

        return processList.isEmpty() ? null : processList;
    }

    /**
     * Retrieves CPU utilization chart metrics.
     * @return List of CPU utilization
     */
    public List<ChartData> getUtilizationMetrics() {
        List<ChartData> chartList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            String sql = """
                        SELECT 
                            timestamp, utilization
                        FROM 
                            cpu
                        ORDER BY timestamp DESC 
                        LIMIT 20;
                             """;

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                chartList.add(new ChartData(resultSet.getDouble("utilization"),
                                            resultSet.getString("timestamp").substring(11, 19),
                                            null));
            }

        } catch (SQLException e) {
            logger.error("Error while getting cpu chart metrics.");
        }

        return chartList.isEmpty() ? null : chartList;
    }
}
