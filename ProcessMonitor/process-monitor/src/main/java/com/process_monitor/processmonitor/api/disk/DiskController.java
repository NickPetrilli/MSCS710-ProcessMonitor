package com.process_monitor.processmonitor.api.disk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.process_monitor.processmonitor.api.disk.model.Disk;
import com.process_monitor.processmonitor.api.process.model.Process;

/**
 * RESTful API for Disk information.
 */
@RestController
@RequestMapping(path = "api/v1/disk")
@CrossOrigin
public class DiskController {
    
    private DiskService diskService = null;

    @Autowired
    public DiskController(DiskService diskService) {
        this.diskService = diskService;
    }


    /**
     * GET Request endpoint to handle getting and returning disk information.
     * @return
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
}
