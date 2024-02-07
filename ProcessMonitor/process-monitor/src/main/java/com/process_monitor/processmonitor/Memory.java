package com.process_monitor.processmonitor;

import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.PhysicalMemory;

public class Memory {

    static SystemInfo systemInfo = new SystemInfo();
    static GlobalMemory memory = systemInfo.getHardware().getMemory();
    public static List<PhysicalMemory> physicalMemoryList = memory.getPhysicalMemory();

    public static long getTotalMemory() {
        return memory.getTotal();
        
    }

    public static long getAvailableMemory() {
        return memory.getAvailable();
    }

    public static int getMemStickID(PhysicalMemory mem) {return physicalMemoryList.indexOf(mem);}

    public static String getMemStickBankLabel(PhysicalMemory mem) {return mem.getBankLabel();}

    public static String getMemStickManufacturer(PhysicalMemory mem) {return mem.getManufacturer();}

    public static long getMemStickCapacity(PhysicalMemory mem) {return mem.getCapacity();}

    public static String getMemStickType(PhysicalMemory mem) {return mem.getMemoryType();}

    public static long getMemStickSpeed(PhysicalMemory mem) {return mem.getClockSpeed();}
}
