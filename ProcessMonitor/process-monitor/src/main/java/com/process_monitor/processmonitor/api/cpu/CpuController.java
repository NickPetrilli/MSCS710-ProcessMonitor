package com.process_monitor.processmonitor.api.cpu;

import java.util.List;
import java.util.Map;

import com.process_monitor.processmonitor.api.util.ChartData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.process_monitor.processmonitor.api.cpu.model.Cpu;
import com.process_monitor.processmonitor.api.process.model.Process;

/**
 * RESTful Controller for Cpu.
 * Handles all API requests passed to URL 'http://localhost:8080/api/v1/cpu'.
 */
@RestController
@RequestMapping(path = "api/v1/cpu")
@CrossOrigin
public class CpuController {

    private CpuService cpuService = null;

    public CpuController(CpuService cpuService) {
        this.cpuService = cpuService;
    }

    /**
     * GET Request endpoint to handle getting most recent Cpu data stored in database.
     * @return Most recent Cpu data.
     */
    @GetMapping
    public Cpu getCpuData() {
        Cpu cpu = cpuService.getCpuData();
        
        return cpu;
    }

    /**
     * GET Request endpoint to handle getting and returning the top-3 processes based on CPU usage.
     * @return List of processes with most cpu usage.
     */
    @GetMapping(path = "top-processes")
    public List<Process> getTopProcesses() {
        return cpuService.getTopProcessesByCpUsage();
    }


    /**
     * GET Request endpoint to handle getting and returning most recent processes based on CPU usage.
     * @return List of processes with most cpu usage.
     */
    @GetMapping(path = "processes")
    public List<Process> getProcessesByCpuUsage() {
        return cpuService.getProcessesOrderByCpuUsage();
    }

    /**
     * GET Request endpoint to handle getting and returning most recent processes based on CPU usage ascending
     * @return List of processes with least cpu usage
     */
    @GetMapping(path = "processes-asc")
    public List<Process> getProcessesByCpuUsageAsc() {
        return cpuService.getProcessesOrderByCpuUsageAsc();
    }

    /**
     * GET Request endpoint to handle getting CPU chart metrics
     * @return List of utilization metrics
     */
    @GetMapping(path = "chart")
    public List<ChartData> getChartUtilizationMetrics() {
        return cpuService.getUtilizationMetrics();
    }

    /**
     * GET Request endpoint to handle getting CPU average utilization in the past 5 min
     * @return average cpu utilization
     */
    @GetMapping(path = "avg-util-5min")
    public Map<String, Double> getAverageUtilization5Min() {
        return cpuService.getAverageUtilization5Min();
    }

    /**
     * GET Request endpoint to handle getting CPU average utilization in the past 10 min
     * @return average cpu utilization
     */
    @GetMapping(path = "avg-util-10min")
    public Map<String, Double> getAverageUtilization10Min() {
        return cpuService.getAverageUtilization10Min();
    }

    /**
     * GET Request endpoint to handle getting CPU average utilization in the past 15 min
     * @return average cpu utilization
     */
    @GetMapping(path = "avg-util-15min")
    public Map<String, Double> getAverageUtilization15Min() {
        return cpuService.getAverageUtilization15Min();
    }

    /**
     * GET Request endpoint to handle getting CPU average utilization in the past 30 min
     * @return average cpu utilization
     */
    @GetMapping(path = "avg-util-30min")
    public Map<String, Double> getAverageUtilization30Min() {
        return cpuService.getAverageUtilization30Min();
    }

    /**
     * GET Request endpoint to handle getting CPU average utilization in the past hour
     * @return average cpu utilization
     */
    @GetMapping(path = "avg-util-1hour")
    public Map<String, Double> getAverageUtilization1Hour() {
        return cpuService.getAverageUtilization1Hour();
    }

    /**
     * GET Request endpoint to handle getting CPU average utilization in the past 2 hours
     * @return average cpu utilization
     */
    @GetMapping(path = "avg-util-2hour")
    public Map<String, Double> getAverageUtilization2Hours() {
        return cpuService.getAverageUtilization2Hours();
    }

    /**
     * GET Request endpoint to handle getting CPU average utilization in the past 4 hours
     * @return average cpu utilization
     */
    @GetMapping(path = "avg-util-4hour")
    public Map<String, Double> getAverageUtilization4Hours() {
        return cpuService.getAverageUtilization4Hours();
    }

    /**
     * GET Request endpoint to handle getting CPU average utilization in the past 6 hours
     * @return average cpu utilization
     */
    @GetMapping(path = "avg-util-6hour")
    public Map<String, Double> getAverageUtilization6Hours() {
        return cpuService.getAverageUtilization6Hours();
    }

    /**
     * GET Request endpoint to handle getting CPU average utilization in the past 12 hours
     * @return average cpu utilization
     */
    @GetMapping(path = "avg-util-12hour")
    public Map<String, Double> getAverageUtilization12Hours() {
        return cpuService.getAverageUtilization12Hours();
    }

    /**
     * GET Request endpoint to handle getting CPU average utilization in the past 24 hours
     * @return average cpu utilization
     */
    @GetMapping(path = "avg-util-24hour")
    public Map<String, Double> getAverageUtilization24Hours() {
        return cpuService.getAverageUtilization24Hours();
    }
}
