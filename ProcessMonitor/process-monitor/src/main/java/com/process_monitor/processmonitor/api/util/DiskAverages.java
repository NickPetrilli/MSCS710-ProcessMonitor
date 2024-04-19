package com.process_monitor.processmonitor.api.util;

public class DiskAverages {

    private long average_readSpeed;
    private long average_writeSpeed;

    public DiskAverages() {
        
    }
    public DiskAverages(long readSpeed, long writeSpeed) {
        this.average_readSpeed = readSpeed;
        this.average_writeSpeed = writeSpeed;
    }

    public long getAverageReadSpeed() {
        return average_readSpeed;
    }

    public long getAverageWriteSpeed() {
        return average_writeSpeed;
    }

    public void setAverageReadSpeed(long readSpeed) {
        this.average_readSpeed = readSpeed;
    }

    public void setAverageWriteSpeed(long writeSpeed) {
        this.average_writeSpeed = writeSpeed;
    }
    
}
