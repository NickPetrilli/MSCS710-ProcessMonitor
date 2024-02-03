package com.process_monitor.processmonitor;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

public class Cpu {
    
    static SystemInfo systemInfo = new SystemInfo();
    static CentralProcessor processor = systemInfo.getHardware().getProcessor();

    public static String getName() {
        return processor.getProcessorIdentifier().getName();
    }

    public static long[] getCurrentFreq() {
        return processor.getCurrentFreq();
    }

    public static long getMaxFreq() {;
        return processor.getMaxFreq();
    }

    public static int getProcessorCount() {
        return processor.getPhysicalProcessorCount();
    }

    public static long getContextSwitches() {
        return processor.getContextSwitches();
    }

    public static long getInterrupts() {
        return processor.getInterrupts();
    }
}
