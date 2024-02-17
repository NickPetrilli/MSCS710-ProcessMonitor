package com.process_monitor.processmonitor.collector;

import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

/**
 * Class to handle collection of central processing unit metrics on the user's computer.
 * Important metrics are:
 *      - processor name
 *      - processor current frequency
 *      - processor max frequency
 *      - number of cores
 *      - number of processes running
 *      - number of threads running
 */
public class CpuCollector {
    
    private static SystemInfo systemInfo = new SystemInfo();
    private static CentralProcessor processor = systemInfo.getHardware().getProcessor();
    private static OperatingSystem os = systemInfo.getOperatingSystem();
    private static List<OSProcess> allOSProcesses = os.getProcesses();

    /**
     * Gets the CPU name
     * @return CPU name (eg. 12th Gen Intel(R) Core(TM) i7-1255U)
     */
    public String getName() {
        return processor.getProcessorIdentifier().getName();
    }

    // TODO: May need to be scrapped, all cores are reported @ max frequency, even though this does not match the performance graphs
    // given by Windows (Task Manager, Resource Monitor)
    /**
     * Gets the current frequency of all logical processors on this CPU
     * @return an average of all the locigcal processor's frequencies (in Hz)
     */
    public long getCurrentFreq() {
        long[] currFreqs = processor.getCurrentFreq();

        long sumFreqs = 0L;
        for (long speed : currFreqs) {
            sumFreqs += speed;
        }
        
        long avgFreq = sumFreqs / processor.getLogicalProcessorCount();
        return avgFreq;
    }

    /**
     * Gets the max frequency of the logical processors on this CPU
     * @return maximum frequency 
     */
    public long getMaxFreq() {;
        return processor.getMaxFreq();
    }

    /**
     * Gets the amount of physical processors on this CPU
     * @return the amount of cores
     */
    public int getCoreCount() {
        return processor.getPhysicalProcessorCount();
    }

    /**
     * Gets the total number of currently running processes on the OS
     * @return the number of processes currently running
     */
    public int getProcessCount() {
        return allOSProcesses.size();
    }

    /**
     * Gets the total number of threads currently running
     * @return the number of threads currently running
     */
    public int getThreadCount() {
        return os.getThreadCount();
    }

    /**
     * Gets the number of logical processors used to calculate actual cpu usage
     * Static method allows to call without creating another object in MetricCollector
     * @return the number of logical processors
     */
    public static int getLogicalProcessorCount() {
        return processor.getLogicalProcessorCount();
    }

}
