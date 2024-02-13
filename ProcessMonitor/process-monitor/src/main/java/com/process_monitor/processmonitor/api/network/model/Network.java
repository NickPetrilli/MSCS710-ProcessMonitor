package com.process_monitor.processmonitor.api.network.model;

public class Network {

    int processId;
    String timestamp;
    String cardName;
    double speed;

    public Network(int processId, String timestamp, String cardName, double speed) {
        this.processId = processId;
        this.timestamp = timestamp;
        this.cardName = cardName;
        this.speed = speed;
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

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Network{" +
                "processId=" + processId +
                ", timestamp='" + timestamp + '\'' +
                ", cardName='" + cardName + '\'' +
                ", speed=" + speed +
                '}';
    }
}
