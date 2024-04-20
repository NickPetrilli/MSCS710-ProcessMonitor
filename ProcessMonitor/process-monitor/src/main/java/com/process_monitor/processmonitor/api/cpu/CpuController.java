package com.process_monitor.processmonitor.api.cpu;

import java.util.List;
import java.util.Map;

import com.process_monitor.processmonitor.api.util.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.process_monitor.processmonitor.api.cpu.model.Cpu;
import com.process_monitor.processmonitor.api.process.model.Process;

@RestController
@RequestMapping(path = "api/v1/cpu")
@CrossOrigin
public class CpuController {

    private CpuService cpuService = null;

    @Autowired
    public CpuController(CpuService cpuService) {
        this.cpuService = cpuService;
    }

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
     * GET Request endpoint to handle getting CPU average utilization in the past 15 min
     * @return average cpu utilization
     */
    @GetMapping(path = "avg-util-15min")
    public Map<String, Double> getAverageUtilization15Min() {
        return cpuService.getAverageUtilization15Min();
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
     * GET Request endpoint to handle getting CPU average utilization in the past 24 hours
     * @return average cpu utilization
     */
    @GetMapping(path = "avg-util-24hour")
    public Map<String, Double> getAverageUtilization24Hours() {
        return cpuService.getAverageUtilization24Hours();
    }
}
