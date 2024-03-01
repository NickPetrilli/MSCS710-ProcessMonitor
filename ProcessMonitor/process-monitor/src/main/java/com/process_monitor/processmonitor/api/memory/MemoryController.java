package com.process_monitor.processmonitor.api.memory;

import java.util.ArrayList;
import java.util.List;

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

}
