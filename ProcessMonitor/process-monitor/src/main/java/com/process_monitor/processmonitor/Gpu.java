package com.process_monitor.processmonitor;

import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;

public class Gpu {
    
    static SystemInfo systemInfo = new SystemInfo();
    private static List<GraphicsCard> graphicsCards = systemInfo.getHardware().getGraphicsCards();

    public static int getGPULength() {return graphicsCards.size();}
    
    public static String getGPUName(int cardIndex) {return graphicsCards.get(cardIndex).getName();}

    public static String getGPUManufacturer(int cardIndex) {return graphicsCards.get(cardIndex).getVendor();}

    public static long getGPUVRAM(int cardIndex) {return graphicsCards.get(cardIndex).getVRam();}
}
