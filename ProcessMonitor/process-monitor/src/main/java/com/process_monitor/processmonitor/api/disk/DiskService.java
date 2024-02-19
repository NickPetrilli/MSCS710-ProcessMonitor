package com.process_monitor.processmonitor.api.disk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.process_monitor.processmonitor.api.disk.model.Disk;
import com.process_monitor.processmonitor.collector.MetricCollector;

@Service
public class DiskService {

    private static final Logger logger = LoggerFactory.getLogger(MetricCollector.class);

    // Database URL
    private final String URL = "jdbc:sqlite:ProcessMonitor.db";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    //TODO: Might need to handle if machine has multiple drives
    public Disk getDiskData() {
        Disk disk = null;

        try {
            // Connect to the database
            connection = DriverManager.getConnection(URL);

            // Create select query - get all attributes for the latest timestamp
            String diskSelectQuery = """
                    SELECT *
                    FROM disk
                    WHERE timestamp = (
                        SELECT MAX(timestamp)
                        FROM disk
                    )
                    """;

            // Create a SQL statement
            statement = connection.createStatement();

            // Execute the query and get the result set
            resultSet = statement.executeQuery(diskSelectQuery);

            while (resultSet.next()) {
                // Retrieve data from the result set
                String timestamp = resultSet.getString("timestamp");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                long swapTotal = resultSet.getInt("swapTotal");
                long swapUsed = resultSet.getInt("swapUsed");
                double swapUtilization = resultSet.getDouble("swapUtilization");
                long totalReadBytes = resultSet.getInt("totalReadBytes");
                long totalWriteBytes = resultSet.getInt("totalWriteBytes");
                long readSpeed = resultSet.getInt("readSpeed");
                long writeSpeed = resultSet.getInt("writeSpeed");
                double utilization = resultSet.getDouble("utilization");
            
                //Creat disk object to pass to DiskController
                disk = new Disk(timestamp, name, model, swapTotal, swapUsed, swapUtilization, totalReadBytes, totalWriteBytes, readSpeed, writeSpeed, utilization);
            }

        } catch (SQLException e) {
            logger.error("Error selecting Disk data.");
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

        return disk;
    }
    
}
