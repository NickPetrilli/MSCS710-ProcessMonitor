package com.process_monitor.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.process_monitor.processmonitor.ProcessMonitorApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Class for testing all API endpoints for Process Controller
 */
@SpringBootTest(classes = ProcessMonitorApplication.class)
@AutoConfigureMockMvc
public class ProcessControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetProcessData() throws Exception {
        // Perform a GET request to /api/v1/process
        mockMvc.perform(get("/api/v1/process"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
