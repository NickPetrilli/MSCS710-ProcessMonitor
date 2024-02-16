package com.process_monitor.processmonitor.api.process.model;


public class Process {
    private int id;
    private String timestamp;
    private String name;
    private String status;
    private double cpuPercentage;
    private long memoryUsageBytes;
    private double memoryUsagePercentage;
    private double diskSpeed;
    private double diskPercentage;

    public Process(int id,
                   String timestamp,
                   String name,
                   String status,
                   double cpuPercentage,
                   long memoryUsageBytes,
                   double memoryUsagePercentage,
                   double diskSpeed,
                   double diskPercentage) {
        this.id = id;
        this.timestamp = timestamp;
        this.name = name;
        this.status = status;
        this.cpuPercentage = cpuPercentage;
        this.memoryUsageBytes = memoryUsageBytes;
        this.memoryUsagePercentage = memoryUsagePercentage;
        this.diskSpeed = diskSpeed;
        this.diskPercentage = diskPercentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCpuPercentage() {
        return cpuPercentage;
    }

    public void setCpuPercentage(double cpuPercentage) {
        this.cpuPercentage = cpuPercentage;
    }

    public long getMemoryUsageBytes() {
        return memoryUsageBytes;
    }

    public void setMemoryUsageBytes(long memoryUsageBytes) {
        this.memoryUsageBytes = memoryUsageBytes;
    }

    public double getMemoryUsagePercentage() {
        return memoryUsagePercentage;
    }

    public void setMemoryUsagePercentage(double memoryUsagePercentage) {
        this.memoryUsagePercentage = memoryUsagePercentage;
    }

    public double getDiskSpeed() {
        return diskSpeed;
    }

    public void setDiskSpeed(double diskSpeed) {
        this.diskSpeed = diskSpeed;
    }

    public double getDiskPercentage() {
        return diskPercentage;
    }

    public void setDiskPercentage(double diskPercentage) {
        this.diskPercentage = diskPercentage;
    }

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", timestamp='" + timestamp + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", cpuPercentage=" + cpuPercentage +
                ", memoryUsageBytes=" + memoryUsageBytes +
                ", memoryUsagePercentage=" + memoryUsagePercentage +
                ", diskSpeed=" + diskSpeed +
                ", diskPercentage=" + diskPercentage +
                '}';
    }
}
