package com.process_monitor.processmonitor.api.cpu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.process_monitor.processmonitor.api.cpu.model.Cpu;
import com.process_monitor.processmonitor.collector.MetricCollector;

@Service
public class CpuService {

    private static final Logger logger = LoggerFactory.getLogger(MetricCollector.class);

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
                long speed = resultSet.getInt("speed");
                long maxSpeed = resultSet.getInt("maxSpeed");
                int cores = resultSet.getInt("cores");
                int processes = resultSet.getInt("processes");
                int threads = resultSet.getInt("threads");
                double utilization = resultSet.getInt("utilization");
            
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

}
