package com.process_monitor.processmonitor.api.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.process_monitor.processmonitor.api.util.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.process_monitor.processmonitor.api.memory.model.Memory;
import com.process_monitor.processmonitor.api.process.model.Process;


@RestController
@RequestMapping(path = "api/v1/memory")
@CrossOrigin
public class MemoryController {

    private MemoryService memoryService = null;

    @Autowired
    public MemoryController(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

    @GetMapping
    public Memory getMemoryData() {
        Memory memory = memoryService.getMemoryData();
        
        return memory;
    }

    @GetMapping("/top-processes")
    public ArrayList<Process> getMemoryTopProcesses() {
        ArrayList<Process> processList = memoryService.getMemoryTopProcesses();

        return processList;
    }

    /**
     * GET Request endpoint to handle getting and returning processes based on their memory usage.
     * @return List of processes
     */
    @GetMapping(path = "processes")
    public List<Process> getProcessesByMemory() {
        return memoryService.getProcessesByMemoryUsage();
    }

    /**
     * GET Request endpoint to handle getting and returning processes based on their memory usage ascending.
     * @return List of processes
     */
    @GetMapping(path = "processes-asc")
    public List<Process> getProcessByMemoryAsc() {
        return memoryService.getProcessesByMemoryUsageAsc();
    }

    /**
     * GET Request endpoint to handle getting Memory chart metrics
     * @return List of utilization metrics
     */
    @GetMapping(path = "chart")
    public List<ChartData> getChartUtilizationMetrics() {
        return memoryService.getUtilizationMetrics();
    }

    /**
     * GET Request endpoint to handle getting Memory average utilization in past 15 min
     * @return average memory utilization
     */
    @GetMapping(path = "avg-util-15min")
    public Map<String, Double> getAverageUtilization15Min() {
        return memoryService.getAverageUtilization15Min();
    }

    /**
     * GET Request endpoint to handle getting Memory average utilization in past 1 hour
     * @return average memory utilization
     */
    @GetMapping(path = "avg-util-1hour")
    public Map<String, Double> getAverageUtilization1Hour() {
        return memoryService.getAverageUtilization1Hour();
    }

    /**
     * GET Request endpoint to handle getting Memory average utilization in past 24 hours
     * @return average memory utilization
     */
    @GetMapping(path = "avg-util-24hour")
    public Map<String, Double> getAverageUtilization24Hours() {
        return memoryService.getAverageUtilization24Hours();
    }

}
