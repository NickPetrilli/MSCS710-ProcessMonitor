package com.process_monitor.processmonitor.api.disk;

import java.util.List;

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
    public List<Disk> getDiskData() {
         List<Disk> disks = diskService.getDiskData();

        return disks;
    }
}
