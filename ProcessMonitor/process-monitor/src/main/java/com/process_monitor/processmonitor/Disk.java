package com.process_monitor.processmonitor;

import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

public class Disk {
    
    static SystemInfo systemInfo = new SystemInfo();
    private static List<HWDiskStore> hardDisks = systemInfo.getHardware().getDiskStores();

    public static void updateDiskData() {
        for (HWDiskStore disk : hardDisks) {disk.updateAttributes();}
    }
    
    public static int getDiskLength() {return hardDisks.size();}
    
    public static String getDiskName(int cardIndex) {return hardDisks.get(cardIndex).getName();}

    public static long getdiskReadBytes(int cardIndex) {return hardDisks.get(cardIndex).getReadBytes();}

    public static long getDiskReadSpeed(int cardIndex) {
        long initReadBytes = hardDisks.get(cardIndex).getReadBytes();

        try {
            // Delay for 1 seconds
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hardDisks.get(cardIndex).updateAttributes();

        long retestReadBytes =  hardDisks.get(cardIndex).getReadBytes();

        return (retestReadBytes - initReadBytes);
    }

    public static long getdiskWriteBytes(int cardIndex) {return hardDisks.get(cardIndex).getWriteBytes();}

    public static long getDiskWriteSpeed(int cardIndex) {
        long initWriteBytes = hardDisks.get(cardIndex).getWriteBytes();

        try {
            // Delay for 1 seconds
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hardDisks.get(cardIndex).updateAttributes();

        long retestWriteBytes =  hardDisks.get(cardIndex).getWriteBytes();

        return (retestWriteBytes - initWriteBytes);
    }
}
