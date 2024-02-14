package com.process_monitor.processmonitor.api.cpu.model;

public class Cpu {

    String timestamp;
    long speed;
    long maxSpeed;
    int numCores;
    int numProcesses;
    int numThreads;
    double utilization;

    public Cpu(String timestamp, long speed, long maxSpeed, int numCores, int numProcesses, 
               int numThreads, double utilization) {
        this.timestamp = timestamp;
        this.speed = speed;
        this.maxSpeed = maxSpeed;
        this.numCores = numCores;
        this.numProcesses = numProcesses;
        this.numThreads = numThreads;
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

    public double getUtilization() {
        return utilization;
    }

    public void setUtilization(double utilization) {
        this.utilization = utilization;
    }

    @Override
    public String toString() {
        return "Cpu{" +
                ", timestamp='" + timestamp + '\'' +
                ", speed=" + speed +
                ", maxSpeed=" + maxSpeed +
                ", cores=" + numCores +
                ", processes=" + numProcesses +
                ", threads=" + numThreads +
                '}';
    }
}
