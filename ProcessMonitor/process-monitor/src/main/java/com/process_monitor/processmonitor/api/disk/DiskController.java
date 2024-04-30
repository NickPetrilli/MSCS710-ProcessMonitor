package com.process_monitor.processmonitor.api.disk;

import java.util.List;
import java.util.Map;

import com.process_monitor.processmonitor.api.util.DiskAverages;
import com.process_monitor.processmonitor.api.util.DiskChartData;

import org.springframework.web.bind.annotation.*;

import com.process_monitor.processmonitor.api.disk.model.Disk;
import com.process_monitor.processmonitor.api.process.model.Process;

/**
 * RESTful Controller for Disk.
 * Handles all API requests passed to URL 'http://localhost:8080/api/v1/disk'.
 */
@RestController
@RequestMapping(path = "api/v1/disk")
@CrossOrigin
public class DiskController {
    
    private DiskService diskService = null;

    public DiskController(DiskService diskService) {
        this.diskService = diskService;
    }


    /**
     * GET Request endpoint to handle getting and returning current disk information.
     * @return Most recent disk data.
     */
    @GetMapping
    public List<Disk> getDiskData() {
        return diskService.getDiskData();
    }


    /**
     * GET Request endpoint to handles getting and returning the top-3 processes based on disk usage.
     * @return List of processes with most disk usage.
     */
    @GetMapping(path = "top-processes")
    public List<Process> getTopProcesses() {
        return diskService.getTopProcessesByDiskUsage();
    }


    /**
     * GET Request endpoint to handle getting and returning processes based on their disk usage.
     * @return List of current running processes.
     */
    @GetMapping(path = "processes")
    public List<Process> getProcessesByDiskSpeed() {
        return diskService.getProcessesByDiskUsage();
    }

    /**
     * GET Request endpoint to handle getting and returning processes based on their disk usage in ascending order.
     * @return List of current running processes.
     */
    @GetMapping(path = "processes-asc")
    public List<Process> getProcessByDiskSpeedAsc() {
        return diskService.getProcessesByDiskUsageAsc();
    }

    /**
     * GET Request endpoint to handle getting disk chart metrics.
     * @param name Disk name
     * @return List of utilization metrics
     */
    @GetMapping(path = "chart/{name}")
    public List<DiskChartData> getChartMetrics(@PathVariable("name") String name) {
        return diskService.getChartMetrics(name);
    }

    /**
     * GET Request endpoint to handle getting disk average speeds in past 5 minutes
     * @return DiskAverages object containing average read and write speed
     */
    @GetMapping(path = "avg-speeds-5min")
    public DiskAverages getAverageSpeeds5Min() {
        return diskService.getAverageSpeeds5Min();
    }

    /**
     * GET Request endpoint to handle getting disk average speeds in past 10 minutes
     * @return DiskAverages object containing average read and write speed
     */
    @GetMapping(path = "avg-speeds-10min")
    public DiskAverages getAverageSpeeds10Min() {
        return diskService.getAverageSpeeds10Min();
    }

    /**
     * GET Request endpoint to handle getting disk average speeds in past 15 minutes
     * @return DiskAverages object containing average read and write speed
     */
    @GetMapping(path = "avg-speeds-15min")
    public DiskAverages getAverageSpeeds15Min() {
        return diskService.getAverageSpeeds15Min();
    }

    /**
     * GET Request endpoint to handle getting disk average speeds in past 30 minutes
     * @return DiskAverages object containing average read and write speed
     */
    @GetMapping(path = "avg-speeds-30min")
    public DiskAverages getAverageSpeeds30Min() {
        return diskService.getAverageSpeeds30Min();
    }

    /**
     * GET Request endpoint to handle getting disk average speeds in past 1 hour
     * @return DiskAverages object containing average read and write speed
     */
    @GetMapping(path = "avg-speeds-1hour")
    public DiskAverages getAverageSpeeds1Hour() {
        return diskService.getAverageSpeeds1Hour();
    }

    /**
     * GET Request endpoint to handle getting disk average speeds in past 2 hours
     * @return DiskAverages object containing average read and write speed
     */
    @GetMapping(path = "avg-speeds-2hour")
    public DiskAverages getAverageSpeeds2Hours() {
        return diskService.getAverageSpeeds2Hours();
    }

    /**
     * GET Request endpoint to handle getting disk average speeds in past 4 hours
     * @return DiskAverages object containing average read and write speed
     */
    @GetMapping(path = "avg-speeds-4hour")
    public DiskAverages getAverageSpeeds4Hours() {
        return diskService.getAverageSpeeds4Hours();
    }

    /**
     * GET Request endpoint to handle getting disk average speeds in past 6 hours
     * @return DiskAverages object containing average read and write speed
     */
    @GetMapping(path = "avg-speeds-6hour")
    public DiskAverages getAverageSpeeds6Hours() {
        return diskService.getAverageSpeeds6Hours();
    }

    /**
     * GET Request endpoint to handle getting disk average speeds in past 12 hours
     * @return DiskAverages object containing average read and write speed
     */
    @GetMapping(path = "avg-speeds-12hour")
    public DiskAverages getAverageSpeeds12Hours() {
        return diskService.getAverageSpeeds12Hours();
    }

    /**
     * GET Request endpoint to handle getting disk average speeds in past 24 hours
     * @return DiskAverages object containing average read and write speed
     */
    @GetMapping(path = "avg-speeds-24hour")
    public DiskAverages getAverageSpeeds24Hours() {
        return diskService.getAverageSpeeds24Hours();
    }

    /**
     * GET Request endpoint to handle getting disk average utilization in past 5 minutes
     * @return disk average utilization
     */
    @GetMapping(path = "avg-util-5min")
    public Map<String, Double> getAverageUtilization5Min() {
        return diskService.getAverageUtilization5Min();
    }

    /**
     * GET Request endpoint to handle getting disk average utilization in past 10 minutes
     * @return disk average utilization
     */
    @GetMapping(path = "avg-util-10min")
    public Map<String, Double> getAverageUtilization10Min() {
        return diskService.getAverageUtilization10Min();
    }

    /**
     * GET Request endpoint to handle getting disk average utilization in past 15 minutes
     * @return disk average utilization
     */
    @GetMapping(path = "avg-util-15min")
    public Map<String, Double> getAverageUtilization15Min() {
        return diskService.getAverageUtilization15Min();
    }

    /**
     * GET Request endpoint to handle getting disk average utilization in past 30 minutes
     * @return disk average utilization
     */
    @GetMapping(path = "avg-util-30min")
    public Map<String, Double> getAverageUtilization30Min() {
        return diskService.getAverageUtilization30Min();
    }

    /**
     * GET Request endpoint to handle getting disk average utilization in past 1 hour
     * @return disk average utilization
     */
    @GetMapping(path = "avg-util-1hour")
    public Map<String, Double> getAverageUtilization1Hour() {
        return diskService.getAverageUtilization1Hour();
    }

    /**
     * GET Request endpoint to handle getting disk average utilization in past 2 hours
     * @return disk average utilization
     */
    @GetMapping(path = "avg-util-2hour")
    public Map<String, Double> getAverageUtilization2Hours() {
        return diskService.getAverageUtilization2Hours();
    }

    /**
     * GET Request endpoint to handle getting disk average utilization in past 4 hours
     * @return disk average utilization
     */
    @GetMapping(path = "avg-util-4hour")
    public Map<String, Double> getAverageUtilization4Hours() {
        return diskService.getAverageUtilization4Hours();
    }

    /**
     * GET Request endpoint to handle getting disk average utilization in past 6 hours
     * @return disk average utilization
     */
    @GetMapping(path = "avg-util-6hour")
    public Map<String, Double> getAverageUtilization6Hours() {
        return diskService.getAverageUtilization6Hours();
    }

    /**
     * GET Request endpoint to handle getting disk average utilization in past 12 hours
     * @return disk average utilization
     */
    @GetMapping(path = "avg-util-12hour")
    public Map<String, Double> getAverageUtilization12Hours() {
        return diskService.getAverageUtilization12Hours();
    }
    
    /**
     * GET Request endpoint to handle getting disk average utilization in past 24 hours
     * @return disk average utilization
     */
    @GetMapping(path = "avg-util-24hour")
    public Map<String, Double> getAverageUtilization24Hours() {
        return diskService.getAverageUtilization24Hours();
    }
}
