package com.process_monitor.processmonitor.api.memory.model;

public class Memory {
    int processId;
    String timestamp;
    double totalMemory;
    double usedMemory;
    double freeMemory;
    double usage;

    public Memory(int processId, String timestamp, double totalMemory, double usedMemory, double freeMemory, double usage) {
        this.processId = processId;
        this.timestamp = timestamp;
        this.totalMemory = totalMemory;
        this.usedMemory = usedMemory;
        this.freeMemory = freeMemory;
        this.usage = usage;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(double totalMemory) {
        this.totalMemory = totalMemory;
    }

    public double getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(double usedMemory) {
        this.usedMemory = usedMemory;
    }

    public double getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(double freeMemory) {
        this.freeMemory = freeMemory;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "processId=" + processId +
                ", timestamp='" + timestamp + '\'' +
                ", totalMemory=" + totalMemory +
                ", usedMemory=" + usedMemory +
                ", freeMemory=" + freeMemory +
                ", usage=" + usage +
                '}';
    }
}
