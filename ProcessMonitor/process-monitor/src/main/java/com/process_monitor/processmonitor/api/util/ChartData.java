package com.process_monitor.processmonitor.api.util;

/**
 * Utility class for chart data (utilization and timestamp).
 */
public class ChartData {

    private double utilization;
    private String timestamp;

    public ChartData(double utilization, String timestamp) {
        this.utilization = utilization;
        this.timestamp = timestamp;
    }

    public double getUtilization() {
        return utilization;
    }

    public void setUtilization(double utilization) {
        this.utilization = utilization;
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
                "utilization=" + utilization +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
