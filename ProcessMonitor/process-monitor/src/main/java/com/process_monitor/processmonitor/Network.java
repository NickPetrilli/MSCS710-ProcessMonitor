package com.process_monitor.processmonitor;

import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

public class Network {

    static SystemInfo systemInfo = new SystemInfo();
    static List<NetworkIF> networkAdapters = systemInfo.getHardware().getNetworkIFs();

    public static String getName() {
        String output = "";
        for (int i = 0; i < networkAdapters.size(); i++) {
            output += networkAdapters.get(i).getDisplayName();
        }
        return output;
    }

    public static String getNetworkInfo() {
        String output = "";
        for (int i = 0; i < networkAdapters.size(); i++) {
            output += networkAdapters.get(i);
        }
        return output;
    }

    public static long getSpeed() {
        return networkAdapters.get(0).getSpeed();
    }


}
