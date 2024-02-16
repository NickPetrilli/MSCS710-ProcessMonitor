package com.process_monitor.processmonitor.api.process.model;


public class Process {
    int id;
    String timestamp;
    String name;
    String status;
    double cpuUsage;
    long memoryUsage;
    long bytesRead;
    long bytesWritten;

    public Process(int id,
                   String timestamp,
                   String name,
                   String status,
                   double cpuUsage,
                   long memoryUsage,
                   long bytesRead,
                   long bytesWritten) {
        this.id = id;
        this.timestamp = timestamp;
        this.name = name;
        this.status = status;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.bytesRead = bytesRead;
        this.bytesWritten = bytesWritten;
    }

    public Process(String timestamp,
                   String name,
                   String status,
                   double cpuUsage,
                   long memoryUsage,
                   long bytesRead,
                   long bytesWritten) {
        this.timestamp = timestamp;
        this.name = name;
        this.status = status;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.bytesRead = bytesRead;
        this.bytesWritten = bytesWritten;
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

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public long getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(long memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public long getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }

    public long getBytesWritten() {
        return bytesWritten;
    }

    public void setBytesWritten(long bytesWritten) {
        this.bytesWritten = bytesWritten;
    }

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", timestamp='" + timestamp + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", cpuUsage=" + cpuUsage +
                ", memoryUsage=" + memoryUsage +
                ", bytesRead=" + bytesRead +
                ", bytesWritten=" + bytesWritten +
                '}';
    }
}
