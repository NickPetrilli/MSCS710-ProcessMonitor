package com.process_monitor.processmonitor;

import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;

public class Gpu {
    
    static SystemInfo systemInfo = new SystemInfo();
    public static List<GraphicsCard> graphicsCards = systemInfo.getHardware().getGraphicsCards();

    public static String getGPUName(GraphicsCard card) {
        return card.getName();
    }

    public static String getGPUManufacturer(GraphicsCard card) {
        return card.getVendor();
    }

    public static long getGPUVRAM(GraphicsCard card) {
        return card.getVRam();
    }
}
