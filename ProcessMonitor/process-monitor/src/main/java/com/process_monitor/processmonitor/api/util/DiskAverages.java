package com.process_monitor.processmonitor.api.util;

/**
 * Class to store the average read and write speed of disks.
 * This class is necessary to package the average read and write speeds of disks
 * together into a single object.
 */
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
