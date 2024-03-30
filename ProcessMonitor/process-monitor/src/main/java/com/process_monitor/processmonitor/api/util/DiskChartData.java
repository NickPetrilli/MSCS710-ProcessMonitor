package com.process_monitor.processmonitor.api.util;

/**
 * Utility class for disk chart data (utilization and timestamp).
 */
public class DiskChartData {

    private double readSpeed;
    private double writeSpeed;
    private String timestamp;

    public DiskChartData(double readSpeed, double writeSpeed, String timestamp) {
        this.readSpeed = readSpeed;
        this.writeSpeed = writeSpeed;
        this.timestamp = timestamp;
    }

    public double getReadSpeed() {
        return readSpeed;
    }

    public void setReadSpeed(double readSpeed) {
        this.readSpeed = readSpeed;
    }

    public double getWriteSpeed() {
        return writeSpeed;
    }

    public void setWriteSpeed(double writeSpeed) {
        this.writeSpeed = writeSpeed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChartData{" +
                "readSpeed=" + readSpeed +
                "writeSpeed=" + writeSpeed + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}