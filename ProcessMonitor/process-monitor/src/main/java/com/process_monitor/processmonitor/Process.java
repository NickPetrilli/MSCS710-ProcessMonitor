package com.process_monitor.processmonitor;

import java.util.List;

import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class Process {

   static SystemInfo systemInfo = new SystemInfo();
   static OperatingSystem os = systemInfo.getOperatingSystem();
   
   public static List<OSProcess> getProcesses() {
    return os.getProcesses();
   }

   public static String getProcessNames() {
    List<OSProcess> osProcesses = os.getProcesses();
    String output = "";
    for (int i = 0; i < osProcesses.size(); i++) {
        output += osProcesses.get(i).getName() + " ";
    }
    return output;
   } 

    public static double getCpuUsage() {
        return os.getProcesses().get(3).getProcessCpuLoadBetweenTicks(null);
    }

    public static double getCpuCumulative() {
        return os.getProcesses().get(1).getProcessCpuLoadCumulative();
    }

    public static long getResidentSetSize() {
        return os.getProcesses().get(0).getResidentSetSize();
    }

}
