package com.process_monitor.processmonitor.api.cpu;

import java.util.List;

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
     * GET Request endpoint to handles getting and returning the top-3 processes based on disk usage.
     * @return List of processes with most disk usage.
     */
    @GetMapping(path = "top-processes")
    public List<Process> getTopProcesses() {
        return cpuService.getTopProcessesByCpUsage();
    }

}
