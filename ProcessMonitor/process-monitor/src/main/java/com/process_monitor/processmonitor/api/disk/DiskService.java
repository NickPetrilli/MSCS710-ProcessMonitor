package com.process_monitor.processmonitor.api.disk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Handles each request logic and database functionality for Disk.
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
     * Retrieves average disk read/write speeds in the past 5 minutes
     * @return DiskAverages object containing average read and write speed
     */
    public DiskAverages getAverageSpeeds5Min() {
        DiskAverages averageReadWriteSpeeds = new DiskAverages();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(readSpeed) as average_readSpeed, AVG(writeSpeed) as average_writeSpeed
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-5 minute') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
               averageReadWriteSpeeds.setAverageReadSpeed(resultSet.getLong("average_readSpeed"));
               averageReadWriteSpeeds.setAverageWriteSpeed(resultSet.getLong("average_writeSpeed"));
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average speeds in past 5 minutes");
       }

       return averageReadWriteSpeeds == null ? null : averageReadWriteSpeeds;
   }

    /**
     * Retrieves average disk read/write speeds in the past 10 minutes
     * @return DiskAverages object containing average read and write speed
     */
    public DiskAverages getAverageSpeeds10Min() {
        DiskAverages averageReadWriteSpeeds = new DiskAverages();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(readSpeed) as average_readSpeed, AVG(writeSpeed) as average_writeSpeed
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-10 minute') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
               averageReadWriteSpeeds.setAverageReadSpeed(resultSet.getLong("average_readSpeed"));
               averageReadWriteSpeeds.setAverageWriteSpeed(resultSet.getLong("average_writeSpeed"));
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average speeds in past 10 minutes");
       }

       return averageReadWriteSpeeds == null ? null : averageReadWriteSpeeds;
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
                    WHERE timestamp
                    BETWEEN datetime('now', 'localtime', '-15 minute') AND datetime('now', 'localtime');
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
     * Retrieves average disk read/write speeds in the past 30 minutes
     * @return DiskAverages object containing average read and write speed
     */
    public DiskAverages getAverageSpeeds30Min() {
        DiskAverages averageReadWriteSpeeds = new DiskAverages();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(readSpeed) as average_readSpeed, AVG(writeSpeed) as average_writeSpeed
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-30 minute') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
               averageReadWriteSpeeds.setAverageReadSpeed(resultSet.getLong("average_readSpeed"));
               averageReadWriteSpeeds.setAverageWriteSpeed(resultSet.getLong("average_writeSpeed"));
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average speeds in past 30 minutes");
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
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-1 hour') AND datetime('now', 'localtime');
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
     * Retrieves average disk read/write speeds in the past 2 hours
     * @return DiskAverages object containing average read and write speed
     */
    public DiskAverages getAverageSpeeds2Hours() {
        DiskAverages averageReadWriteSpeeds = new DiskAverages();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(readSpeed) as average_readSpeed, AVG(writeSpeed) as average_writeSpeed
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-2 hour') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
               averageReadWriteSpeeds.setAverageReadSpeed(resultSet.getLong("average_readSpeed"));
               averageReadWriteSpeeds.setAverageWriteSpeed(resultSet.getLong("average_writeSpeed"));
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average speeds in past 2 hours");
       }

       return averageReadWriteSpeeds == null ? null : averageReadWriteSpeeds;
   }

    /**
     * Retrieves average disk read/write speeds in the past 4 hours
     * @return DiskAverages object containing average read and write speed
     */
    public DiskAverages getAverageSpeeds4Hours() {
        DiskAverages averageReadWriteSpeeds = new DiskAverages();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(readSpeed) as average_readSpeed, AVG(writeSpeed) as average_writeSpeed
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-4 hour') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
               averageReadWriteSpeeds.setAverageReadSpeed(resultSet.getLong("average_readSpeed"));
               averageReadWriteSpeeds.setAverageWriteSpeed(resultSet.getLong("average_writeSpeed"));
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average speeds in past 4 hours");
       }

       return averageReadWriteSpeeds == null ? null : averageReadWriteSpeeds;
   }

    /**
     * Retrieves average disk read/write speeds in the past 6 hours
     * @return DiskAverages object containing average read and write speed
     */
    public DiskAverages getAverageSpeeds6Hours() {
        DiskAverages averageReadWriteSpeeds = new DiskAverages();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(readSpeed) as average_readSpeed, AVG(writeSpeed) as average_writeSpeed
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-6 hour') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
               averageReadWriteSpeeds.setAverageReadSpeed(resultSet.getLong("average_readSpeed"));
               averageReadWriteSpeeds.setAverageWriteSpeed(resultSet.getLong("average_writeSpeed"));
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average speeds in past 6 hours");
       }

       return averageReadWriteSpeeds == null ? null : averageReadWriteSpeeds;
   }

    /**
     * Retrieves average disk read/write speeds in the past 12 hours
     * @return DiskAverages object containing average read and write speed
     */
    public DiskAverages getAverageSpeeds12Hours() {
        DiskAverages averageReadWriteSpeeds = new DiskAverages();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(readSpeed) as average_readSpeed, AVG(writeSpeed) as average_writeSpeed
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-12 hour') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
               averageReadWriteSpeeds.setAverageReadSpeed(resultSet.getLong("average_readSpeed"));
               averageReadWriteSpeeds.setAverageWriteSpeed(resultSet.getLong("average_writeSpeed"));
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average speeds in past 12 hours");
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
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-24 hour') AND datetime('now', 'localtime');
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
     * Retrieves average disk utilization in the past 5 minutes
     * @return average disk utilization
     */
    public Map<String, Double> getAverageUtilization5Min() {
        Double averageUtilization = 0.0;

        Map<String, Double> map = new HashMap<>();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(utilization) as average_utilization
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-5 minute') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
                averageUtilization = resultSet.getDouble("average_utilization");
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average utilization in past 5 minutes");
       }

        if (averageUtilization == 0)
            return null;

        map.put("utilization", averageUtilization);
        return map;
   }

    /**
     * Retrieves average disk utilization in the past 10 minutes
     * @return average disk utilization
     */
    public Map<String, Double> getAverageUtilization10Min() {
        Double averageUtilization = 0.0;

        Map<String, Double> map = new HashMap<>();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(utilization) as average_utilization
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-10 minute') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
                averageUtilization = resultSet.getDouble("average_utilization");
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average utilization in past 10 minutes");
       }

        if (averageUtilization == 0)
            return null;

        map.put("utilization", averageUtilization);
        return map;
   }

    /**
     * Retrieves average disk utilization in the past 15 minutes
     * @return average disk utilization
     */
    public Map<String, Double> getAverageUtilization15Min() {
        Double averageUtilization = 0.0;

        Map<String, Double> map = new HashMap<>();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(utilization) as average_utilization
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-15 minute') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
                averageUtilization = resultSet.getDouble("average_utilization");
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average utilization in past 15 minutes");
       }

        if (averageUtilization == 0)
            return null;

        map.put("utilization", averageUtilization);
        return map;
   }

    /**
     * Retrieves average disk utilization in the past 30 minutes
     * @return average disk utilization
     */
    public Map<String, Double> getAverageUtilization30Min() {
        Double averageUtilization = 0.0;

        Map<String, Double> map = new HashMap<>();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(utilization) as average_utilization
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-30 minute') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
                averageUtilization = resultSet.getDouble("average_utilization");
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average utilization in past 30 minutes");
       }

        if (averageUtilization == 0)
            return null;

        map.put("utilization", averageUtilization);
        return map;
   }

    /**
     * Retrieves average disk utilization in the past 1 hour
     * @return average disk utilization
     */
    public Map<String, Double> getAverageUtilization1Hour() {
        Double averageUtilization = 0.0;

        Map<String, Double> map = new HashMap<>();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(utilization) as average_utilization
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-1 hour') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
                averageUtilization = resultSet.getDouble("average_utilization");
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average utiliation in past 1 hour");
       }

        if (averageUtilization == 0)
            return null;

        map.put("utilization", averageUtilization);
        return map;
   }

    /**
     * Retrieves average disk utilization in the past 2 hours
     * @return average disk utilization
     */
    public Map<String, Double> getAverageUtilization2Hours() {
        Double averageUtilization = 0.0;

        Map<String, Double> map = new HashMap<>();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(utilization) as average_utilization
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-2 hour') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
                averageUtilization = resultSet.getDouble("average_utilization");
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average utiliation in past 2 hours");
       }

        if (averageUtilization == 0)
            return null;

        map.put("utilization", averageUtilization);
        return map;
   }

    /**
     * Retrieves average disk utilization in the past 4 hours
     * @return average disk utilization
     */
    public Map<String, Double> getAverageUtilization4Hours() {
        Double averageUtilization = 0.0;

        Map<String, Double> map = new HashMap<>();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(utilization) as average_utilization
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-4 hour') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
                averageUtilization = resultSet.getDouble("average_utilization");
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average utiliation in past 4 hours");
       }

        if (averageUtilization == 0)
            return null;

        map.put("utilization", averageUtilization);
        return map;
   }

    /**
     * Retrieves average disk utilization in the past 6 hours
     * @return average disk utilization
     */
    public Map<String, Double> getAverageUtilization6Hours() {
        Double averageUtilization = 0.0;

        Map<String, Double> map = new HashMap<>();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(utilization) as average_utilization
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-6 hour') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
                averageUtilization = resultSet.getDouble("average_utilization");
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average utiliation in past 6 hours");
       }

        if (averageUtilization == 0)
            return null;

        map.put("utilization", averageUtilization);
        return map;
   }

    /**
     * Retrieves average disk utilization in the past 12 hours
     * @return average disk utilization
     */
    public Map<String, Double> getAverageUtilization12Hours() {
        Double averageUtilization = 0.0;

        Map<String, Double> map = new HashMap<>();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(utilization) as average_utilization
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-12 hour') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
                averageUtilization = resultSet.getDouble("average_utilization");
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average utiliation in past 12 hours");
       }

        if (averageUtilization == 0)
            return null;

        map.put("utilization", averageUtilization);
        return map;
   }

    /**
     * Retrieves average disk utilization in the past 24 hours
     * @return average disk utilization
     */
    public Map<String, Double> getAverageUtilization24Hours() {
        Double averageUtilization = 0.0;

        Map<String, Double> map = new HashMap<>();

       try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

               String sql = """
                   SELECT AVG(utilization) as average_utilization
                   FROM disk
                   WHERE timestamp
                   BETWEEN datetime('now', 'localtime', '-24 hour') AND datetime('now', 'localtime');
                   """;

           resultSet = statement.executeQuery(sql);

           while (resultSet.next()) {
                averageUtilization = resultSet.getDouble("average_utilization");
           }

       } catch (SQLException e) {
           logger.error("Error while getting disk average utilization in past 24 hours");
       }

        if (averageUtilization == 0)
            return null;

        map.put("utilization", averageUtilization);
        return map;
   }
}
