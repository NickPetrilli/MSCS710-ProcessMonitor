package com.process_monitor.processmonitor;

import org.springframework.web.bind.annotation.RestController;

import oshi.hardware.PhysicalMemory;
import oshi.hardware.NetworkIF;
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

        return "Processor Name: " + processorName + "\n" 
        + " Processor Current Speed (Hz): " + processorSpeed + "\n" 
        + " Processor Max Speed (Hz): " + processorMaxSpeed + "\n" 
        + " Physical Processor Count (Cores): " + physicalProcessorCount + "\n" 
        + " Logical Processor Count (Threads): " + logicalProcessorCount + "\n" 
        + " Context Switches: " + contextSwitches + "\n" 
        + " Interrupts: " + interrupts;
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

        String output = "";

        //Total Memory
        long totalMemory = Memory.getTotalMemory();

        //Available Memory
        long availableMemory = Memory.getAvailableMemory();

        float memoryUsed = ((float)availableMemory / totalMemory);
        memoryUsed *= 100;

        output += "Total Memory (B): " + totalMemory + "\n"
        + " Available Memory (B): " + availableMemory 
            + " (" + memoryUsed + "% Used)" + "\n";

        for (PhysicalMemory memStick : Memory.physicalMemoryList) {

            int id = Memory.getMemStickID(memStick);

            String bankLabel = Memory.getMemStickBankLabel(memStick);

            String manufacturer = Memory.getMemStickManufacturer(memStick);

            long size = Memory.getMemStickCapacity(memStick);

            String type = Memory.getMemStickType(memStick);

            long speed = Memory.getMemStickSpeed(memStick);

            output += "Card ID: " + id + "\n"
            + " Bank Label: " + bankLabel + "\n"
            + " Manufacturer: " + manufacturer + "\n" 
            + " Size (B): " + size + "\n"
            + " Type: " + type + "\n"
            + " Speed (Hz): " + speed + "\n";
        }

        return output;
    }

    @GetMapping("/api/network")
    public String getNetworkInfo() {

        String output = "";

        for (NetworkIF network : Network.networkAdapters) {
            // Updates to the most recent network stats for the given network card
            network.updateAttributes();

            //Network adapter name
            String networkName = Network.getName(network);

            // Network Card name
            String networkCardName = Network.getNetworkInfo(network);

            // Network Speed *ADVERTISED*
            long networkSpeed = Network.getSpeed(network);

            // Network Upload/Download Speed
            long networkUpload = Network.getUpload(network);
            long networkDownload = Network.getDownload(network);

            output += "Network Name: " + networkName + "\n" 
            + " Network Card Name: " + networkCardName + "\n" 
            + " Network Speed (bits/s): " + networkSpeed + "\n"
            + " Bytes Sent (bits): " + networkUpload + "\n"
            + " Bytes Recieved (bits): " + networkDownload + "\n";
        }

        return output;
    }
}
