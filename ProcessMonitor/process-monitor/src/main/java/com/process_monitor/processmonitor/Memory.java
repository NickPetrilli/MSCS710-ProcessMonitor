package com.process_monitor.processmonitor;

import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.PhysicalMemory;

public class Memory {

    static SystemInfo systemInfo = new SystemInfo();
    static GlobalMemory memory = systemInfo.getHardware().getMemory();
    static List<PhysicalMemory> physicalMemoryList = memory.getPhysicalMemory();

    public static long getTotalMemory() {
        return memory.getTotal();
        
    }

    public static long getAvailableMemory() {
        return memory.getAvailable();
    }

    public static String getMemoryType() {
        String output = "";
        for (int i = 0; i < physicalMemoryList.size(); i++) {
            output += physicalMemoryList.get(i);
        }
        return output;
    }
    
    
}
