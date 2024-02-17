package com.process_monitor.processmonitor.collector;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

/**
 * Class to handle collection of physical memory metrics on the user's computer.
 * Important metrics are:
 *      - total memory in bytes
 *      - available memory in bytes
 *      - used memory in bytes
 *      - utilization percentage
 */
public class MemoryCollector {

    private static SystemInfo systemInfo = new SystemInfo();
    private static GlobalMemory memory = systemInfo.getHardware().getMemory();

    /**
     * Gets the amount of actual physical memory, in bytes
     * @return the amount of physical memory in bytes
     */
    public long getTotalMemory() {
        return memory.getTotal();
    }

    /**
     * Gets the amount of physical memory currently available in bytes
     * @return the amount of physical memory currently available in bytes
     */
    public long getAvailableMemory() {
        return memory.getAvailable();
    }

    /**
     * Subtracts available memory from total memory to get used memory
     * @return the amount of physical memory currently being used
     */
    public long getUsedMemory() {
        return this.getTotalMemory() - this.getAvailableMemory();
    }

    /**
     * Divides the used memory by the total memory to get utilization
     * @return memory utilization percentage
     */
    public double getUtilization() {
        long usedMemory = this.getUsedMemory();
        long totalMemory = this.getTotalMemory();

        return ((double) usedMemory / totalMemory) * 100;
    }
}
