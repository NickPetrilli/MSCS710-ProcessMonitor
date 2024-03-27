package com.process_monitor.processmonitor.collector;


import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import com.process_monitor.processmonitor.api.process.model.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Class to handle collection of running process metrics on the user's computer.
 * Important metrics are:
 *      - process id
 *      - process name
 *      - process status (RUNNING vs STOPPED)
 *      - process CPU percentage
 *      - process memory usage in bytes
 *      - process memory percentage
 *      - process disk speed
 *      - process disk percentage
 */
public class ProcessCollector {

    private final SystemInfo systemInfo = new SystemInfo();
    private final OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
    private final List<OSProcess> allOSProcesses = operatingSystem.getProcesses();
    private double totalDiskUsagePercentage = 0.0;
    private double totalCpuUsagePercentage = 0.0;

    /**
     * Gets running process metrics using the Oshi library to interact with Operating System.
     * @return List of all the running process on the user's computer.
     */
    /*
     * Syncronized method to ensure that access to shared variable allOSProcesses is synchronized
     * to prevent multiple threads from accessing or modifying it
     * Needed because the thread sleeps for one second to calculate read/write speed
     * Prevents processes ending in that one second wait which causes a null pointer error 
     */
    public synchronized List<Process> getProcessMetrics() {

        List<Process> processList = new ArrayList<>();

        // Stores instantaneous CPU usage for each process (cumulative for all cores)
        ArrayList<Double> cpuUsages = new ArrayList<>();

        // Stores memory usage of each process in bytes
        ArrayList<Long> memUsagesBytes = new ArrayList<>();

        // Stores memory usage of each process as a percentage
        ArrayList<Double> memUsagesPer = new ArrayList<>();

        // Stores initial disk read for each process
        ArrayList<Long> initReads = new ArrayList<>();
        // Stores initial disk write for each process
        ArrayList<Long> initWrites = new ArrayList<>();

        // Stores disk speed (read + write) of each process
        ArrayList<Long> diskSpeeds = new ArrayList<>();

        for (OSProcess process : allOSProcesses) {
            initReads.add(process.getBytesRead());
            initWrites.add(process.getBytesWritten());
        }

        try {
            // Delay for 1 seconds
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Iterator<OSProcess> procIterator = allOSProcesses.iterator();

        // UPDATE NEEDED TO REFRESH PROCESSES
        while(procIterator.hasNext()) {

            OSProcess proc = procIterator.next();

            try {
                if (!proc.updateAttributes()) {System.out.println("Process info update FAIL: " + proc.getName());}
            }

            catch (NullPointerException e) {
                System.out.println("UH OH! Process w/ update null error: " + proc.getName());
                
                int index = allOSProcesses.indexOf(proc);
                procIterator.remove();
                initReads.remove(index);
                initWrites.remove(index);
            }
        }

        int procNum = 0;
        for (OSProcess process : allOSProcesses) {
            cpuUsages.add(process.getProcessCpuLoadCumulative() * 100);

            MemoryCollector memoryCollector = new MemoryCollector();
            long memUsage = process.getResidentSetSize();
            memUsagesBytes.add(memUsage);
            memUsagesPer.add(memUsage / (memoryCollector.getTotalMemory() * 1.0) * 100);

            long secRead = process.getBytesRead();
            long secWrite = process.getBytesWritten();

            long procReadSpeed = secRead - initReads.get(procNum);
            long procWriteSpeed = secWrite - initWrites.get(procNum);

            diskSpeeds.add(procReadSpeed + procWriteSpeed);
            procNum++;
        }


        long diskTotalSpeed = 0;

        List<HWDiskStore> hardDisks = systemInfo.getHardware().getDiskStores();
        for (HWDiskStore disk : hardDisks) {
            disk.updateAttributes();
        }

        for (HWDiskStore hardDisk : hardDisks) {
            diskTotalSpeed += hardDisk.getReadBytes() + hardDisk.getWriteBytes();
        }

        for (int i = 0; i < allOSProcesses.size(); i++) {

            float diskUsagePercentage = ((float)diskSpeeds.get(i) / diskTotalSpeed) * 100;

            totalDiskUsagePercentage += diskUsagePercentage;

            processList.add(new Process(
                    allOSProcesses.get(i).getProcessID(),
                    null,
                    allOSProcesses.get(i).getName(),
                    allOSProcesses.get(i).getState().toString(),
                    cpuUsages.get(i),
                    memUsagesBytes.get(i),
                    memUsagesPer.get(i),
                    diskSpeeds.get(i),
                    diskUsagePercentage
            ));

        }

        
        for (double processCpuUsage : cpuUsages) {
            totalCpuUsagePercentage += processCpuUsage;
        }

        //System.out.println("\n\n" + totalCpuUsagePercentage + "\n\n");

        //System.out.println("\n\n" + totalDiskUsagePercentage + "\n\n");

        return processList;
    }

    /**
     * Gets the sum of all processes disk usage
     * @return the total disk usage percentage
     */
    public double getTotalDiskPercentage() {
        return totalDiskUsagePercentage;
    }

    /**
     * Gets the sum of all processes cpu usage
     * @return the total cpu usage percentage
     */
    public double getTotalCpuPercentage() {
        return totalCpuUsagePercentage;
    }

}
