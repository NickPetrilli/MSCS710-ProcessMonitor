package com.process_monitor.processmonitor;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

public class Cpu {
    
    static SystemInfo systemInfo = new SystemInfo();
    static CentralProcessor processor = systemInfo.getHardware().getProcessor();

    public static String getName() {
        return processor.getProcessorIdentifier().getName();
    }

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
        return processor.getMaxFreq();
    }

    public static int getCoreCount() {
        return processor.getPhysicalProcessorCount();
    }

    public static int getLogicalProcessorCount() {
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
