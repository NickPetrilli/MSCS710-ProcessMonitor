package com.process_monitor.processmonitor.api.memory.model;

/**
 * Model class for Memory data.
 */
public class Memory {

    String timestamp;
    long totalMemory;
    long availableMemory;
    long usedMemory;
    double utilization;

    public Memory(String timestamp, long totalMemory, long availableMemory, long usedMemory, double utilization) {
        this.timestamp = timestamp;
        this.totalMemory = totalMemory;
        this.availableMemory = availableMemory;
        this.usedMemory = usedMemory;
        this.utilization = utilization;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public long getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(long usedMemory) {
        this.usedMemory = usedMemory;
    }

    public long getAvailableMemory() {
        return availableMemory;
    }

    public void setAvailableMemory(long availableMemory) {
        this.availableMemory = availableMemory;
    }

    public double getUtilization() {
        return utilization;
    }

    public void setUsage(double utilization) {
        this.utilization = utilization;
    }

    @Override
    public String toString() {
        return "Memory{" +
                ", timestamp='" + timestamp + '\'' +
                ", totalMemory=" + totalMemory +
                ", freeMemory=" + availableMemory +
                ", usedMemory=" + usedMemory +
                ", utilization=" + utilization +
                '}';
    }
}
