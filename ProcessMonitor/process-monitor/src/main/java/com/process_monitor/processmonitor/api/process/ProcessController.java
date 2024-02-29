package com.process_monitor.processmonitor.api.process;

import com.process_monitor.processmonitor.api.process.model.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for handling requests and responses for Process.
 */
@RestController
@RequestMapping(path = "api/v1/process")
@CrossOrigin
public class ProcessController {

    private final ProcessService processService;

    @Autowired
    public ProcessController(ProcessService service) {
        this.processService = service;
    }

    /**
     * Gets list of most recent processes stored in database.
     * @return List of most recent processes (in JSON)
     */
    @GetMapping
    public List<Process> listProcesses() {
        return processService.getProcesses();
    }

    /**
     * Gets process data with specified name.
     * @param name Process name (ex: chrome)
     * @return List of process data
     */
    @GetMapping(path = "{name}")
    public List<Process> getProcessHistoryByName(@PathVariable("name") String name) {
        return processService.getProcessByName(name);
    }

}
