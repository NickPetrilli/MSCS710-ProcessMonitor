package com.process_monitor.processmonitor;

import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.PhysicalMemory;

public class Memory {

    static SystemInfo systemInfo = new SystemInfo();
    static GlobalMemory memory = systemInfo.getHardware().getMemory();
    private static List<PhysicalMemory> physicalMemoryList = memory.getPhysicalMemory();

    public static int getMemoryCardsLength() {return physicalMemoryList.size();}
    
    public static long getTotalMemory() {return memory.getTotal();}

    public static long getAvailableMemory() {return memory.getAvailable();}

    // May not need this anymore
    // public static int getMemStickID(int mem) {return physicalMemoryList.indexOf(mem);}

    public static String getMemStickBankLabel(int mem) {return physicalMemoryList.get(mem).getBankLabel();}

    public static String getMemStickManufacturer(int mem) {return physicalMemoryList.get(mem).getManufacturer();}

    public static long getMemStickCapacity(int mem) {return physicalMemoryList.get(mem).getCapacity();}

    public static String getMemStickType(int mem) {return physicalMemoryList.get(mem).getMemoryType();}

    public static long getMemStickSpeed(int mem) {return physicalMemoryList.get(mem).getClockSpeed();}
}
