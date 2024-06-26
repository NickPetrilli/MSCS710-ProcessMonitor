package com.process_monitor.processmonitor.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Class to prune database
 * Scheduled to prune every 5 minutes deleting records that are 1 week old
 */
@Component
public class DatabasePruning {

    private static final Logger logger = LoggerFactory.getLogger(DatabasePruning.class);
    // Database URL
    private final String URL = "jdbc:sqlite:ProcessMonitor.db";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    
    //300000 milliseconds = 5 minutes
    @Scheduled(fixedRate = 300000)
    public void pruneDatabase() {
        
        try {
            // Connect to the database
            connection = DriverManager.getConnection(URL);

            //Cut off is a week ago from the current time
            String cutOffTime = LocalDateTime.now().minusWeeks(1).toString();

            //Delete from Process table
            String processQuery = "DELETE FROM process WHERE timestamp < ?";

            preparedStatement = connection.prepareStatement(processQuery);
            preparedStatement.setString(1, cutOffTime);

            int processRowsAffected = preparedStatement.executeUpdate();
            logger.info(processRowsAffected + " records deleted from process table");

            //Delete from CPU table
            String cpuQuery = "DELETE FROM cpu WHERE timestamp < ?";

            preparedStatement = connection.prepareStatement(cpuQuery);
            preparedStatement.setString(1, cutOffTime);

            int cpuRowsAffected = preparedStatement.executeUpdate();
            logger.info(cpuRowsAffected + " records deleted from cpu table");

            //Delete from Memory table
            String memoryQuery = "DELETE FROM memory WHERE timestamp < ?";

            preparedStatement = connection.prepareStatement(memoryQuery);
            preparedStatement.setString(1, cutOffTime);

            int memoryRowsAffected = preparedStatement.executeUpdate();
            logger.info(memoryRowsAffected + " records deleted from memory table");

            //Delete from Disk table
            String diskQuery = "DELETE FROM disk WHERE timestamp < ?";

            preparedStatement = connection.prepareStatement(diskQuery);
            preparedStatement.setString(1, cutOffTime);
            
            int diskRowsAffected = preparedStatement.executeUpdate();
            logger.info(diskRowsAffected + " records deleted from disk table");

        } catch (SQLException e) {
            logger.error("Error deleting records from the database.");
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
    

