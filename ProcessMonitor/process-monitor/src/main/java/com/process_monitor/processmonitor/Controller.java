package com.process_monitor.processmonitor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Controller {

    @GetMapping("/api/os")
    public String getOS() {
        return System.getProperty("os.name");
    }

    @GetMapping("api/processor")
    public String getProcessorInfo() {
        // Processor Name
        String processorName = Cpu.getName();

        // Processor Speed (current)
        long processorSpeed = Cpu.getCurrentFreq();

        // Processor Max Speed
        long processorMaxSpeed = Cpu.getMaxFreq();

        //Physical Processor Count
        int physicalProcessorCount = Cpu.getCoreCount();
        int logicalProcessorCount = Cpu.getLogicalProcessorCount();

        //Context Switches Count
        long contextSwitches = Cpu.getContextSwitches();
        //Interrupt Count
        long interrupts = Cpu.getInterrupts();

        return "Processor Name: " + processorName + "\n" + " Processor Current Speed: " + processorSpeed 
        + " Hz" + "\n" + " Processor Max Speed: " + processorMaxSpeed + " Hz" + "\n" +
        " Physical Processor Count: " + physicalProcessorCount + "\n" + " Logical Processor Count: " + 
        logicalProcessorCount + "\n" + " Context Switches: " + 
        contextSwitches + "\n" + " Interrupts: " + interrupts;
    }
    
    @GetMapping("/api/memory")
    public String getMemoryInfo() {
        //Total Memory
        long totalMemory = Memory.getTotalMemory();

        //Available Memory
        long availableMemory = Memory.getAvailableMemory();

        //Memory Types
        String memoryTypes = Memory.getMemoryType();

        return "Total Memory: " + totalMemory + " Bytes " + " Available Memory: " + availableMemory + " Bytes" + " Memory Types: " + memoryTypes;

    }

    @GetMapping("/api/network")
    public String getNetworkInfo() {
        //Network info
        String networkInfo = Network.getNetworkInfo();
        
        //Network adapter names
        String networkName = Network.getName();

        //Network Speed
        long networkSpeed = Network.getSpeed();

        return "Network Info: " + networkInfo + "Network Name: " + networkName + "Network Speed: " + networkSpeed + " bits/s";
    }
    
    
}
