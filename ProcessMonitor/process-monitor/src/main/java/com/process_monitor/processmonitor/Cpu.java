package com.process_monitor.processmonitor;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

public class Cpu {
    
    static SystemInfo systemInfo = new SystemInfo();
    static CentralProcessor processor = systemInfo.getHardware().getProcessor();

    public static String getName() {
        return processor.getProcessorIdentifier().getName();
    }

    // TODO: May need to be scrapped, all cores are reported @ max frequency, even though this does not match the performance graphs
    // given by Windows (Task Manager, Resource Monitor)
    public static long getCurrentFreq() {
        long[] currFreqs = processor.getCurrentFreq();

        long sumFreqs = 0L;
        for (long speed : currFreqs) {
            sumFreqs += speed;
        }
        
        long avgFreq = sumFreqs / processor.getLogicalProcessorCount();
        return avgFreq;
    }

    public static long getMaxFreq() {;
        return processor.getProcessorIdentifier().getVendorFreq();
    }

    public static int getCoreCount() {
        return processor.getPhysicalProcessorCount();
    }

    public static int getThreadCount() {
        // What about hyperthreading/multi-threading??? Any way to check?
        return processor.getLogicalProcessorCount();
    }

    public static long getContextSwitches() {
        return processor.getContextSwitches();
    }

    public static long getInterrupts() {
        return processor.getInterrupts();
    }
}
