package com.process_monitor;

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

@SpringBootTest(classes = ProcessMonitorApplication.class)
@AutoConfigureMockMvc
public class MemoryControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetMemoryData() throws Exception {
        // Perform a GET request to /api/v1/memory
        mockMvc.perform(get("/api/v1/memory"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetMemoryTopProcesses() throws Exception {
        // Perform a GET request to /api/v1/memory/top-processes
        mockMvc.perform(get("/api/v1/memory/top-processes"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetMemoryProcessesDesc() throws Exception {
        // Perform a GET request to /api/v1/memory/processes
        mockMvc.perform(get("/api/v1/memory/processes"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetMemoryProcessesAsc() throws Exception {
        // Perform a GET request to /api/v1/memory/processes-asc
        mockMvc.perform(get("/api/v1/memory/processes-asc"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetMemoryChartData() throws Exception {
        // Perform a GET request to /api/v1/memory/chart
        mockMvc.perform(get("/api/v1/memory/chart"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
