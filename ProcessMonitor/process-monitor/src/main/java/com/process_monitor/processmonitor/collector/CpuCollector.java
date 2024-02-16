package com.process_monitor.processmonitor.collector;

import java.util.List;

import com.process_monitor.processmonitor.api.cpu.model.Cpu;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class CpuCollector {
    
    private static SystemInfo systemInfo = new SystemInfo();
    private static CentralProcessor processor = systemInfo.getHardware().getProcessor();
    private static OperatingSystem os = systemInfo.getOperatingSystem();
    private static List<OSProcess> allOSProcesses = os.getProcesses();
    
    public String getName() {
        return processor.getProcessorIdentifier().getName();
    }

    // TODO: May need to be scrapped, all cores are reported @ max frequency, even though this does not match the performance graphs
    // given by Windows (Task Manager, Resource Monitor)
    public long getCurrentFreq() {
        long[] currFreqs = processor.getCurrentFreq();

        long sumFreqs = 0L;
        for (long speed : currFreqs) {
            sumFreqs += speed;
        }
        
        long avgFreq = sumFreqs / processor.getLogicalProcessorCount();
        return avgFreq;
    }

    public long getMaxFreq() {;
        return processor.getProcessorIdentifier().getVendorFreq();
    }

    public int getCoreCount() {
        return processor.getPhysicalProcessorCount();
    }

    public int getProcessCount() {
        return allOSProcesses.size();
    }

    public int getThreadCount() {
        return os.getThreadCount();
    }

}
