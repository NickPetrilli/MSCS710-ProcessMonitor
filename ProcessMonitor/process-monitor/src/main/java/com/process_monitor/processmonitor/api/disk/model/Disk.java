package com.process_monitor.processmonitor.api.disk.model;

/**
 * Model class for Disk data.
 */
public class Disk {

    private String timestamp;
    private String name;
    private String model;
    private long swapTotal;
    private long swapUsed;
    private double swapUtilization;
    private long totalReadBytes;
    private long totalWriteBytes;
    private long readSpeed;
    private long writeSpeed;
    private double utilization;

    public Disk(String timestamp,
                String name,
                String model,
                long swapTotal,
                long swapUsed,
                double swapUtilization,
                long totalReadBytes,
                long totalWriteBytes,
                long readSpeed,
                long writeSpeed) {

        this.timestamp = timestamp;
        this.name = name;
        this.model = model;
        this.swapTotal = swapTotal;
        this.swapUsed = swapUsed;
        this.swapUtilization = swapUtilization;
        this.totalReadBytes = totalReadBytes;
        this.totalWriteBytes = totalWriteBytes;
        this.readSpeed = readSpeed;
        this.writeSpeed = writeSpeed;
    }

    /*
     * This second constructor is used when retrieving disk data from the database
     * So this one has utilization and the above one doesn't
     */
    public Disk(String timestamp,
                String name,
                String model,
                long swapTotal,
                long swapUsed,
                double swapUtilization,
                long totalReadBytes,
                long totalWriteBytes,
                long readSpeed,
                long writeSpeed,
                double utilization) {

        this.timestamp = timestamp;
        this.name = name;
        this.model = model;
        this.swapTotal = swapTotal;
        this.swapUsed = swapUsed;
        this.swapUtilization = swapUtilization;
        this.totalReadBytes = totalReadBytes;
        this.totalWriteBytes = totalWriteBytes;
        this.readSpeed = readSpeed;
        this.writeSpeed = writeSpeed;
        this.utilization = utilization;
    }
    public String getTimestamp() {
        return this.timestamp;
    }

    public String getName() {
        return this.name;
    }

    public String getModel() {
        return this.model;
    }

    public long getSwapTotal() {
        return this.swapTotal;
    }

    public long getSwapUsed() {
        return this.swapUsed;
    }

    public double getSwapUtilization() {
        return this.swapUtilization;
    }

    public long getTotalReadBytes() {
        return this.totalReadBytes;
    }

    public long getTotalWriteBytes() {
        return this.totalWriteBytes;
    }

    public long getReadSpeed() {
        return this.readSpeed;
    }

    public long getWriteSpeed() {
        return this.writeSpeed;
    }

    public double getUtilization() {
        return this.utilization;
    }
    
}
