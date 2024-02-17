package com.process_monitor.processmonitor.collector;

import com.process_monitor.processmonitor.api.cpu.model.Cpu;
import com.process_monitor.processmonitor.api.disk.model.Disk;
import com.process_monitor.processmonitor.api.memory.model.Memory;
import com.process_monitor.processmonitor.api.process.model.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.process_monitor.processmonitor.db.DatabaseFunctions;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class to collect computer metrics.
 * It is scheduled to collect every 30 seconds.
 *
 * Documentation used to implement schedule:
 * https://medium.com/@AlexanderObregon/scheduling-tasks-in-spring-applications-a-practical-guide-8d28d35493c6#:~:text=The%20simplest%20way%20to%20schedule,according%20to%20the%20specified%20schedule.
 */
@Component
public class MetricCollector {

    private static final Logger logger = LoggerFactory.getLogger(MetricCollector.class);

    // Collects metrics every 30 seconds.
    @Scheduled(fixedRate = 10000)
    public void collectMetrics() {
        LocalDateTime currentTimestamp = LocalDateTime.now();
        logger.info("Metric Collection performed at {}", currentTimestamp);

        // Object to handle database functions
        DatabaseFunctions databaseFunctions = new DatabaseFunctions(currentTimestamp.toString());

        // Object to handle collecting running process metrics
        ProcessCollector processCollector = new ProcessCollector();

        List<Process> processList = processCollector.getProcessMetrics();

        logger.info("Begin insert of process list at {}", LocalDateTime.now());
        for (Process process : processList) {
            databaseFunctions.insertProcess(process);
        }
        //CPU and Disk utilization are summed in the ProcessCollector
        double cpuUtilization = processCollector.getTotalCpuPercentage();
        //CPU usage can exceed 100% due to multithreading
        //To handle this, divide by the number of logical processors to match Task Manager
        int logicalProcessors = CpuCollector.getLogicalProcessorCount();
        cpuUtilization = cpuUtilization / logicalProcessors;
        double diskUtilization = processCollector.getTotalDiskPercentage();

        logger.info("Finished insert of process list at {}", LocalDateTime.now());

        // Object to handle collecting CPU metrics
        CpuCollector cpuCollector = new CpuCollector();
        String name = cpuCollector.getName();
        long speed = cpuCollector.getCurrentFreq();
        long maxSpeed = cpuCollector.getMaxFreq();
        int numCores = cpuCollector.getCoreCount();
        int numProcesses = cpuCollector.getProcessCount();
        int numThreads = cpuCollector.getThreadCount();

        //Timestamp is null because DatabaseFunctions handles it
        Cpu cpu = new Cpu(null, name, speed, maxSpeed, numCores, numProcesses, numThreads, cpuUtilization);

        logger.info("Begin insert of cpu metrics at {}", LocalDateTime.now());
        databaseFunctions.insertCpu(cpu);
        logger.info("Finished insert of cpu metrics at {}", LocalDateTime.now());


        //Object to handle collecting memory metrics
        MemoryCollector memoryCollector = new MemoryCollector();

        long totalMemory = memoryCollector.getTotalMemory();
        long availableMemory = memoryCollector.getAvailableMemory();
        long usedMemory = memoryCollector.getUsedMemory();
        double memoryUtilization = memoryCollector.getUtilization();

        //Timestamp is null because DatabaseFunctions handles it
        Memory memory = new Memory(null, totalMemory, availableMemory, usedMemory, memoryUtilization);

        logger.info("Begin insert of memory metrics at {}", LocalDateTime.now());
        databaseFunctions.insertMemory(memory);
        logger.info("Finished insert of memory metrics at {}", LocalDateTime.now());

        //Object to handle collecting disk metrics
        DiskCollector diskCollector = new DiskCollector();

        List<Disk> diskStores = diskCollector.getDiskMetrics();

        logger.info("Begin insert of disk metrics at {}", LocalDateTime.now());
        for (Disk disk : diskStores) {
            databaseFunctions.insertDisk(disk, diskUtilization);
        }
        logger.info("Finished insert of disk metrics at {}", LocalDateTime.now());
    }

}
