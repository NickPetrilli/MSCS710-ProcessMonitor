package com.process_monitor.processmonitor;

import java.util.List;

import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class Process {

   static SystemInfo systemInfo = new SystemInfo();
   static OperatingSystem os = systemInfo.getOperatingSystem();
   
   private static List<OSProcess> allOSProcesses = os.getProcesses();

   public static int getOSProcessesLength() {return allOSProcesses.size();}

   public static void updateProcesses() {
      for (OSProcess proc : allOSProcesses) {proc.updateAttributes();}
   }

   public static String getProcessName(int proc) {return allOSProcesses.get(proc).getName();} 

   public static double getCpuUsage(int proc) {return allOSProcesses.get(proc).getProcessCpuLoadBetweenTicks(null);}
   
   public static double getCpuCumulative() {return os.getProcesses().get(1).getProcessCpuLoadCumulative();}
   
   public static long getResidentSetSize(int proc) {return allOSProcesses.get(proc).getResidentSetSize();}

   public static long getDiskReadBytes(int proc) {return allOSProcesses.get(proc).getBytesRead();}

   public static long getDiskWriteBytes(int proc) {return allOSProcesses.get(proc).getBytesWritten();}
}
