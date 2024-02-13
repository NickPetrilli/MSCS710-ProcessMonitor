package com.process_monitor.processmonitor.api.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/process")
public class ProcessController {

    private final ProcessService processService;

    @Autowired
    public ProcessController(ProcessService service) {
        this.processService = service;
    }

    @GetMapping
    public List<Process> listProcesses() {
        return processService.getProcesses();
    }

    @GetMapping("{id}")
    public Process getProcessById(@PathVariable("id") Integer id) {
        return processService.getProcess(id);
    }

}
