package com.process_monitor.processmonitor.api.cpu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.process_monitor.processmonitor.api.cpu.model.Cpu;

@RestController
@RequestMapping(path = "api/v1/cpu")
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

}
