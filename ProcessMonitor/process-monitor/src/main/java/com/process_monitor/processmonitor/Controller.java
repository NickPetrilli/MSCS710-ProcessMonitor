package com.process_monitor.processmonitor;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

// import oshi.hardware.PhysicalMemory;
// import oshi.hardware.NetworkIF;
// import oshi.hardware.GraphicsCard;
// import oshi.software.os.OSProcess;

// import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

/*
 * @RestController
public class Controller {

    @GetMapping("/api/os")
    public String getOS() {
        return System.getProperty("os.name");
    }

    // @GetMapping("api/processor")
    // public String getProcessorInfo() {
    //     // Processor Name
    //     String processorName = Cpu.getName();

    //     // Processor Speed (current)
    //     long processorSpeed = Cpu.getCurrentFreq();

    //     // Processor Max Speed
    //     long processorMaxSpeed = Cpu.getMaxFreq();

    //     //Physical Processor Count
    //     int physicalProcessorCount = Cpu.getCoreCount();
    //     int logicalProcessorCount = Cpu.getThreadCount();

    //     //Context Switches Count
    //     long contextSwitches = Cpu.getContextSwitches();
    //     //Interrupt Count
    //     long interrupts = Cpu.getInterrupts();

    //     return "Processor Name: " + processorName + "\n" 
    //     + " Processor Current Speed (Hz): " + processorSpeed + "\n" 
    //     + " Processor Max Speed (Hz): " + processorMaxSpeed + "\n" 
    //     + " Physical Processor Count (Cores): " + physicalProcessorCount + "\n" 
    //     + " Logical Processor Count (Threads): " + logicalProcessorCount + "\n" 
    //     + " Context Switches: " + contextSwitches + "\n" 
    //     + " Interrupts: " + interrupts;
    // }

    @GetMapping("api/disk")
    public String getGPUInfo() {

        Disk.updateDiskData();

        String output = "";

        for (int diskNum = 0; diskNum < Disk.getDiskLength(); diskNum++) {

            // Disk Name
            String diskName = Disk.getDiskName(diskNum);

            // Disk Read Speed
            long diskReadSpeed = Disk.getDiskReadSpeed(diskNum);

            // Disk Write Speed
            long diskWriteSpeed = Disk.getDiskWriteSpeed(diskNum);

            output += "Disk Name: " + diskName + " \n" 
            + " Disk Read Speed (B/s): " + diskReadSpeed + "\n" 
            + " Disk Write Speed (B/s): " + diskWriteSpeed + "\n";
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

        float memoryUsed = (float)availableMemory / totalMemory;
        memoryUsed *= 100;

        output += "Total Memory (B): " + totalMemory + "\n"
        + " Available Memory (B): " + availableMemory 
            + " (" + memoryUsed + "% Used)" + "\n";

        for (int memStick = 0; memStick < Memory.getMemoryCardsLength(); memStick++) {

            int id = memStick;

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

    /* @GetMapping("/api/processes")
    public List<Object> getProcesses() {return Process.getProcesses();} */
    
    /*@GetMapping("/api/processnames")
    public String getProcessNames() {
        String output = "";

        for (OSProcess process : Process.getProcesses()) {
            output += Process.getProcessName(process) + "\n";
        }
        
        return output;
    }

    @GetMapping("/api/processCpu")
    public String getProcessCpuUsage() {
        //double cpuUsage = Process.getCpuUsage();
        double cpuUsage = Process.getCpuCumulative();

        long memUsage = Process.getResidentSetSize();

        return "Cumulative CPU Usage: " + cpuUsage + "\n" + "Memory Usage (Bytes): " + memUsage;
    } 

    
    @GetMapping("/api/processStats")
    public String getProcessStats() {
        String output = "";

        ArrayList<String> procNames = new ArrayList<>();

        ArrayList<Double> cpuUsages = new ArrayList<>();

        ArrayList<Float> memUsagesBytes = new ArrayList<>();
        ArrayList<Float> memUsagesPer = new ArrayList<>();

        ArrayList<Long> initReads = new ArrayList<>();
        ArrayList<Long> initWrites = new ArrayList<>();

        ArrayList<Long> diskUsagesBytes = new ArrayList<>();

        for (int procNum = 0; procNum < Process.getOSProcessesLength(); procNum++) {
            procNames.add(Process.getProcessName(procNum));

            initReads.add(Process.getDiskReadBytes(procNum));
            initWrites.add(Process.getDiskWriteBytes(procNum));
        }

        try {
            // Delay for 1 seconds
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Process.updateProcesses();

        for (int procNum = 0; procNum < Process.getOSProcessesLength(); procNum++) {
            cpuUsages.add(Process.getCpuUsage(procNum) * 100);

            float memUsage = (float)Process.getResidentSetSize(procNum);
            memUsagesBytes.add(memUsage);
            memUsagesPer.add((memUsage / Memory.getTotalMemory()) * 100);

            long secRead = Process.getDiskReadBytes(procNum);
            long secWrite = Process.getDiskWriteBytes(procNum);

            long procReadSpeed = secRead - initReads.get(procNum);
            long procWriteSpeed = secWrite - initWrites.get(procNum);

            diskUsagesBytes.add(procReadSpeed + procWriteSpeed);
        }

        long diskTotSpeed = 0;

        Disk.updateDiskData();
        for (int diskNum = 0; diskNum < Disk.getDiskLength(); diskNum++) {diskTotSpeed += Disk.getdiskReadBytes(diskNum) + Disk.getdiskWriteBytes(diskNum);}

        float totalDiskUsagePer = 0.0f;

        for (int procNum = 0; procNum < Process.getOSProcessesLength(); procNum++) {

            float diskUsagePer = ((float)diskUsagesBytes.get(procNum) / diskTotSpeed) * 100;

            totalDiskUsagePer += diskUsagePer;

            output += "Process Name: " + procNames.get(procNum) + "\n" 
            + " CPU Usage: " + cpuUsages.get(procNum) + "%" + "\n"
            + " Memory Usage (Bytes): " + memUsagesBytes.get(procNum) + " (" + memUsagesPer.get(procNum) + "%)" + "\n"
            + " Disk Usage (B/s): " + diskUsagesBytes.get(procNum) + " (" + diskUsagePer + "%)" + "\n";
        }

        output += "TOTAL DISK USAGE BY ALL PROCESSES: " + (int)totalDiskUsagePer + "% \n";
        

        return output;
    }
}

 */
