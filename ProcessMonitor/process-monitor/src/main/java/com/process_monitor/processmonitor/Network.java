package com.process_monitor.processmonitor;

import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

public class Network {

    static SystemInfo systemInfo = new SystemInfo();
    public static List<NetworkIF> networkAdapters = systemInfo.getHardware().getNetworkIFs();

    public static String getName(NetworkIF net) {
        return net.getName();
    }

    public static String getNetworkInfo(NetworkIF net) {
        return net.getDisplayName();
    }

    public static long getSpeed(NetworkIF net) {
        return net.getSpeed();
    }

    public static long getUpload(NetworkIF net) {
        return net.getBytesSent();
    }

    public static long getDownload(NetworkIF net) {
        return net.getBytesRecv();
    }
}
