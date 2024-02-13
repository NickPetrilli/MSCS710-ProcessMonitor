package com.process_monitor.processmonitor.api.cpu.model;

public class Cpu {

    int processId;
    String timestamp;
    double speed;
    double maxSpeed;

    public Cpu(int processId, String timestamp, double speed, double maxSpeed) {
        this.processId = processId;
        this.timestamp = timestamp;
        this.speed = speed;
        this.maxSpeed = maxSpeed;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
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
