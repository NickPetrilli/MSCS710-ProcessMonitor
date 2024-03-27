package com.process_monitor.processmonitor.api.util;

/**
 * Utility class for chart data (utilization and timestamp).
 */
public class ChartData {

    private double utilization;
    private String timestamp;
    private String diskModel;  // ALWAYS NULL, except for disk

    public ChartData(double utilization, String timestamp, String diskModel) {
        this.utilization = utilization;
        this.timestamp = timestamp;
        this.diskModel = diskModel;
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

    public String getDiskModel() {
        return diskModel;
    }

    public void setDiskModel(String diskModel) {
        this.diskModel = diskModel;
    }

    @Override
    public String toString() {
        return "ChartData{" +
                "utilization=" + utilization +
                ", timestamp='" + timestamp + '\'' +
                ", diskModel='" + diskModel + '\'' +
                '}';
    }
}
