package com.process_monitor.processmonitor.api.disk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.process_monitor.processmonitor.api.disk.model.Disk;

@RestController
@RequestMapping(path = "api/v1/disk")
public class DiskController {
    
    private DiskService diskService = null;

    @Autowired
    public DiskController(DiskService diskService) {
        this.diskService = diskService;
    }

    @GetMapping
    public Disk getDiskData() {
        Disk disk = diskService.getDiskData();

        return disk;
    }
}
