package com.process_monitor.processmonitor.api.disk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.process_monitor.processmonitor.api.process.model.Process;
import com.process_monitor.processmonitor.api.util.ChartData;
import com.process_monitor.processmonitor.api.util.DiskAverages;
import com.process_monitor.processmonitor.api.util.DiskChartData;

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
     * Retrieves fuller list processes based on diskUsage ascending (diskSpeed).
     * @return List of Processes
     */
    public List<Process> getProcessesByDiskUsageAsc() {
        List<Process> processList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            String sql = """
                    SELECT *
                    FROM process
                    WHERE timestamp = (SELECT MAX(timestamp) FROM process)
                    ORDER BY diskSpeed ASC
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
     * Retrieves Disk utilization metrics for charts.
     * @param name Disk name
     * @return List of utilization metrics for the specified disk
     */
    public List<ChartData> getUtilizationMetrics(String name) {
        List<ChartData> chartList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            String sql = "SELECT timestamp, utilization, name, model" +
                    " FROM disk WHERE name = '" + name + "'" +
                    " ORDER BY timestamp DESC LIMIT 20;";

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                chartList.add(new ChartData(resultSet.getDouble("utilization"),
                                            resultSet.getString("timestamp").substring(11, 19)));
            }

        } catch (SQLException e) {
            logger.error("Error while getting disk chart metrics");
        }

        return chartList.isEmpty() ? null : chartList;
    }

    /**
     * Retrieves Disk read speed and write speed metrics for charts.
     * @param name Disk name
     * @return List of read/write speed metrics for the specified disk
     */
    public List<DiskChartData> getChartMetrics(String name) {
        List<DiskChartData> diskChartList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            String sql = "SELECT timestamp, readSpeed, writeSpeed, name" +
                    " FROM disk WHERE name = '" + name + "'" +
                    " ORDER BY timestamp DESC LIMIT 20;";

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                diskChartList.add(new DiskChartData(resultSet.getDouble("readSpeed"),
                                            resultSet.getDouble("writeSpeed"),
                                            resultSet.getString("timestamp").substring(11, 19)));
            }

        } catch (SQLException e) {
            logger.error("Error while getting disk chart metrics");
        }

        return diskChartList.isEmpty() ? null : diskChartList;
    }

    /**
     * Retrieves average disk read/write speeds in the past 15 minutes
     * @return DiskAverages object containing average read and write speed
     */
    public DiskAverages getAverageSpeeds15Min() {
         DiskAverages averageReadWriteSpeeds = new DiskAverages();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

                String sql = """
                    SELECT AVG(readSpeed) as average_readSpeed, AVG(writeSpeed) as average_writeSpeed
                    FROM disk
                    WHERE timestamp >= datetime('now', '-15 minute');
                    """;

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                averageReadWriteSpeeds.setAverageReadSpeed(resultSet.getLong("average_readSpeed"));
                averageReadWriteSpeeds.setAverageWriteSpeed(resultSet.getLong("average_writeSpeed"));
            }

        } catch (SQLException e) {
            logger.error("Error while getting disk average speeds in past 15 minutes");
        }

        return averageReadWriteSpeeds == null ? null : averageReadWriteSpeeds;
    }

    /**
     * Retrieves average disk read/write speeds in the past 1 hour
     * @return DiskAverages object containing average read and write speed
     */
    public DiskAverages getAverageSpeeds1Hour() {
        DiskAverages averageReadWriteSpeeds = new DiskAverages();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(readSpeed) as average_readSpeed, AVG(writeSpeed) as average_writeSpeed
                   FROM disk
                   WHERE timestamp >= datetime('now', '-1 hour');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
               averageReadWriteSpeeds.setAverageReadSpeed(resultSet.getLong("average_readSpeed"));
               averageReadWriteSpeeds.setAverageWriteSpeed(resultSet.getLong("average_writeSpeed"));
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average speeds in past 1 hour");
       }

       return averageReadWriteSpeeds == null ? null : averageReadWriteSpeeds;
   }

    /**
     * Retrieves average disk read/write speeds in the past 24 hours
     * @return DiskAverages object containing average read and write speed
     */
    public DiskAverages getAverageSpeeds24Hours() {
        DiskAverages averageReadWriteSpeeds = new DiskAverages();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(readSpeed) as average_readSpeed, AVG(writeSpeed) as average_writeSpeed
                   FROM disk
                   WHERE timestamp >= datetime('now', '-24 hour');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
               averageReadWriteSpeeds.setAverageReadSpeed(resultSet.getLong("average_readSpeed"));
               averageReadWriteSpeeds.setAverageWriteSpeed(resultSet.getLong("average_writeSpeed"));
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average speeds in past 24 hours");
       }

       return averageReadWriteSpeeds == null ? null : averageReadWriteSpeeds;
   }

    /**
     * Retrieves average disk utilization in the past 15 minutes
     * @return average disk utilization
     */
    public Double getAverageUtilization15Min() {
        Double averageUtilization = 0.0;

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(utilization) as average_utilization
                   FROM disk
                   WHERE timestamp >= datetime('now', '-15 minute');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
                averageUtilization = resultSet.getDouble("average_utilization");
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average utilization in past 15 minutes");
       }

       return averageUtilization == 0.0 ? null : averageUtilization;
   }

    /**
     * Retrieves average disk utilization in the past 1 hour
     * @return average disk utilization
     */
    public Double getAverageUtilization1Hour() {
        Double averageUtilization = 0.0;

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(utilization) as average_utilization
                   FROM disk
                   WHERE timestamp >= datetime('now', '-1 hour');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
                averageUtilization = resultSet.getDouble("average_utilization");
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average utiliation in past 1 hour");
       }

       return averageUtilization == 0.0 ? null : averageUtilization;
   }

    /**
     * Retrieves average disk utilization in the past 24 hours
     * @return average disk utilization
     */
    public Double getAverageUtilization24Hours() {
        Double averageUtilization = 0.0;

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(utilization) as average_utilization
                   FROM disk
                   WHERE timestamp >= datetime('now', '-24 hour');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
                averageUtilization = resultSet.getDouble("average_utilization");
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average utilization in past 24 hours");
       }

       return averageUtilization == 0.0 ? null : averageUtilization;
   }
}
