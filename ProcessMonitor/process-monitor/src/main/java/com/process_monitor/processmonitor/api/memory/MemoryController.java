package com.process_monitor.processmonitor.api.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.process_monitor.processmonitor.api.util.ChartData;
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
     * GET Request endpoint to handle getting Memory average utilization in past 5 min
     * @return average memory utilization
     */
    @GetMapping(path = "avg-util-5min")
    public Map<String, Double> getAverageUtilization5Min() {
        return memoryService.getAverageUtilization5Min();
    }

    /**
     * GET Request endpoint to handle getting Memory average utilization in past 10 min
     * @return average memory utilization
     */
    @GetMapping(path = "avg-util-10min")
    public Map<String, Double> getAverageUtilization10Min() {
        return memoryService.getAverageUtilization10Min();
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
     * GET Request endpoint to handle getting Memory average utilization in past 30 min
     * @return average memory utilization
     */
    @GetMapping(path = "avg-util-30min")
    public Map<String, Double> getAverageUtilization30Min() {
        return memoryService.getAverageUtilization30Min();
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
     * GET Request endpoint to handle getting Memory average utilization in past 2 hours
     * @return average memory utilization
     */
    @GetMapping(path = "avg-util-2hour")
    public Map<String, Double> getAverageUtilization2Hours() {
        return memoryService.getAverageUtilization2Hours();
    }

    /**
     * GET Request endpoint to handle getting Memory average utilization in past 4 hours
     * @return average memory utilization
     */
    @GetMapping(path = "avg-util-4hour")
    public Map<String, Double> getAverageUtilization4Hours() {
        return memoryService.getAverageUtilization4Hours();
    }

    /**
     * GET Request endpoint to handle getting Memory average utilization in past 6 hours
     * @return average memory utilization
     */
    @GetMapping(path = "avg-util-6hour")
    public Map<String, Double> getAverageUtilization6Hours() {
        return memoryService.getAverageUtilization6Hours();
    }

    /**
     * GET Request endpoint to handle getting Memory average utilization in past 12 hours
     * @return average memory utilization
     */
    @GetMapping(path = "avg-util-12hour")
    public Map<String, Double> getAverageUtilization12Hours() {
        return memoryService.getAverageUtilization12Hours();
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
