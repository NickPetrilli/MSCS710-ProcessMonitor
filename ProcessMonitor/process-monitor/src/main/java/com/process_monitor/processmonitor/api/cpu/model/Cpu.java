package com.process_monitor.processmonitor.api.cpu.model;

public class Cpu {

    int processId;
    String timestamp;
    long speed;
    long maxSpeed;
    int numCores;
    int numProcesses;
    int numThreads;

    public Cpu(int processId, String timestamp, long speed, long maxSpeed, int numCores, int numProcesses, int numThreads) {
        this.processId = processId;
        this.timestamp = timestamp;
        this.speed = speed;
        this.maxSpeed = maxSpeed;
        this.numCores = numCores;
        this.numProcesses = numProcesses;
        this.numThreads = numThreads;
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

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public long getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(long maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getNumCores() {
        return numCores;
    }

    public void setNumCores(int numCores) {
        this.numCores = numCores;
    }

    public int getNumProcesses() {
        return numProcesses;
    }
    
    public void setNumProcesses(int numProcesses) {
        this.numProcesses = numProcesses;
    }

    public int getNumThreads() {
        return numThreads;
    }

    public void setNumThreads(int numThreads) {
        this.numThreads = numThreads;
    }
    @Override
    public String toString() {
        return "Cpu{" +
                "processId=" + processId +
                ", timestamp='" + timestamp + '\'' +
                ", speed=" + speed +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
