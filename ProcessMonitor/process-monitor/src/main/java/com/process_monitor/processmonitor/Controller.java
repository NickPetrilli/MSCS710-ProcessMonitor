package com.process_monitor.processmonitor;

import org.springframework.web.bind.annotation.RestController;

import oshi.hardware.GraphicsCard;

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
        int logicalProcessorCount = Cpu.getThreadCount();

        //Context Switches Count
        long contextSwitches = Cpu.getContextSwitches();
        //Interrupt Count
        long interrupts = Cpu.getInterrupts();

        return "Processor Name: " + processorName + "\n" + " Processor Current Speed (Hz): " + processorSpeed 
        + "\n" + " Processor Max Speed (Hz): " + processorMaxSpeed + "\n" +
        " Physical Processor Count (Cores): " + physicalProcessorCount + "\n" + " Logical Processor Count (Threads): " + 
        logicalProcessorCount + "\n" + " Context Switches: " + 
        contextSwitches + "\n" + " Interrupts: " + interrupts;
    }

    @GetMapping("api/gpu")
    public String getGPUInfo() {

        String output = "";

        for (GraphicsCard card : Gpu.graphicsCards) {

            // GPU Manufacturer
            String gpuManufacturer = Gpu.getGPUManufacturer(card);
            
            // GPU Name
            String gpuName = Gpu.getGPUName(card);

            // Processor Max Speed
            // String VRAM;
            long gpuVRAM = Gpu.getGPUVRAM(card);
            /* if (gpuVRAM >= 1000000000) {VRAM = (gpuVRAM / 1000000000L) + " GB";}
            else {VRAM = (gpuVRAM / 1000000L) + " MB";} */

            output += "GPU Name: " + gpuName + " \n" 
            + " GPU Manufacturer: " + gpuManufacturer + "\n" 
            + " GPU VRAM (Bytes): " + gpuVRAM + "\n";
        }

        return output;
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
