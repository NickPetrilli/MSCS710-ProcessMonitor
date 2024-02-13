package com.process_monitor.processmonitor;

import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

public class Network {

    static SystemInfo systemInfo = new SystemInfo();

    // TODO: What happens if a new interface is added after service startup? Should we have a function to cover that?
    private static List<NetworkIF> networkAdapters = systemInfo.getHardware().getNetworkIFs();

    public static void updateNetworkAdapters() {
        for (NetworkIF networkAdapter : networkAdapters) {networkAdapter.updateAttributes();}
    }

    public static int getNetworkAdaptersLength() {return networkAdapters.size();}
    
    public static String getName(int net) {return networkAdapters.get(net).getName();}

    public static String getNetworkInfo(int net) {return networkAdapters.get(net).getDisplayName();}

    public static long getSpeed(int net) {return networkAdapters.get(net).getSpeed();}

    public static long getUpload(int net) {return networkAdapters.get(net).getBytesSent();}

    public static long getDownload(int net) {return networkAdapters.get(net).getBytesRecv();}
}
