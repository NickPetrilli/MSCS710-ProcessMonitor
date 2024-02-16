package com.process_monitor.processmonitor.collector;


import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import com.process_monitor.processmonitor.api.process.model.Process;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle collection of running process metrics on the user's computer.
 * Important metrics are:
 *      - process name
 *      - process status
 *      - process cpu usage/consumption
 *      - process memory usage/consumption
 *      - a process's total bytes read from disk
 *      - a process's total bytes written to disk
 */
public class ProcessCollector {

    private SystemInfo systemInfo = new SystemInfo();
    private OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
    private List<OSProcess> allOSProcesses = operatingSystem.getProcesses();

    /**
     * Gets running process metrics using the Oshi library to interact with Operating System.
     * @return List of all the running process on the user's computer.
     */
    public List<Process> getProcessMetrics() {

        List<Process> processList = new ArrayList<>();

        for (OSProcess process : allOSProcesses) {
            int id = process.getProcessID();
            String name = process.getName();
            String status = process.getState().toString();
            double cpuUsage = process.getProcessCpuLoadBetweenTicks(null);
            long memoryUsage = process.getResidentSetSize();
            long diskBytesRead = process.getBytesRead();
            long diskBytesWritten = process.getBytesWritten();

            processList.add(new Process(
                    id,
                    null,  // Null because the timestamp is handled by the DatabaseFunctions object.
                    name,
                    status,
                    cpuUsage,
                    memoryUsage,
                    diskBytesRead,
                    diskBytesWritten
            ));
        }

        return processList;
    }
}
