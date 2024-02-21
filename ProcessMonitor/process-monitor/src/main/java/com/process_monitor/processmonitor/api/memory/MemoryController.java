package com.process_monitor.processmonitor.api.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.process_monitor.processmonitor.api.memory.model.Memory;


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

}
