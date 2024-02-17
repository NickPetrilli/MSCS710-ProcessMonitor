package com.process_monitor.processmonitor.collector;

import java.util.ArrayList;
import java.util.List;

import com.process_monitor.processmonitor.api.disk.model.Disk;

import oshi.SystemInfo;
import oshi.hardware.VirtualMemory;
import oshi.hardware.HWDiskStore;

/**
 * Class to handle collection of disk metrics on the user's computer.
 * Important metrics are:
 *      - disk name
 *      - disk model
 *      - current size of swap files in bytes
 *      - current memory committed to the swap files in bytes
 *      - utilization of current swap files
 *      - total number of bytes read from the disk
 *      - total number of bytes written to the disk
 *      - disk read speed
 *      - disk write speed
 *      - percent utilization of the disk
 */
public class DiskCollector {

    private static SystemInfo systemInfo = new SystemInfo();
    private static List<HWDiskStore> diskStores = systemInfo.getHardware().getDiskStores();
    private static VirtualMemory virtualMemory = systemInfo.getHardware().getMemory().getVirtualMemory();
    

    public List<Disk> getDiskMetrics() {

        List<Disk> disks = new ArrayList<>();
        int index = 0;
        
        for (HWDiskStore disk : diskStores) {
            String name = disk.getName();
            String model = disk.getModel();
            long swapTotal = virtualMemory.getSwapTotal();
            long swapUsed = virtualMemory.getSwapUsed();
            double swapUtilization = ((double) swapUsed / swapTotal);
            long totalReadBytes = disk.getReadBytes();
            long totalWriteBytes = disk.getWriteBytes();
            long readSpeed = this.getDiskReadSpeed(index);
            long writeSpeed = this.getDiskWriteSpeed(index);

            disks.add(new Disk(
                null,
                name, 
                model, 
                swapTotal, 
                swapUsed, 
                swapUtilization, 
                totalReadBytes, 
                totalWriteBytes,
                readSpeed,
                writeSpeed
            ));

            index++;
        }

        return disks;
    }

    public void updateDiskData() {
        for (HWDiskStore disk : diskStores) {
            disk.updateAttributes();
        }
    }

    public long getDiskReadSpeed(int index) {
        long initReadBytes = diskStores.get(index).getReadBytes();

        try {
            // Delay for 1 seconds
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        diskStores.get(index).updateAttributes();

        long retestReadBytes =  diskStores.get(index).getReadBytes();

        return (retestReadBytes - initReadBytes);
    }

    public long getDiskWriteSpeed(int index) {
        long initWriteBytes = diskStores.get(index).getWriteBytes();

        try {
            // Delay for 1 seconds
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        diskStores.get(index).updateAttributes();

        long retestWriteBytes =  diskStores.get(index).getWriteBytes();

        return (retestWriteBytes - initWriteBytes);
    }
}
