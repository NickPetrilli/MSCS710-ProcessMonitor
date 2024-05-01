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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Class for testing all API endpoints for Memory Controller
 */
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
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the response body is not empty
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetMemoryTopProcesses() throws Exception {
        // Perform a GET request to /api/v1/memory/top-processes
        mockMvc.perform(get("/api/v1/memory/top-processes"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the response body is not empty
                .andExpect(jsonPath("$[*]").isNotEmpty());
    }

    @Test
    public void testGetMemoryProcessesDesc() throws Exception {
        // Perform a GET request to /api/v1/memory/processes
        mockMvc.perform(get("/api/v1/memory/processes"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the response body is not empty
                .andExpect(jsonPath("$[*]").isNotEmpty());
    }

    @Test
    public void testGetMemoryProcessesAsc() throws Exception {
        // Perform a GET request to /api/v1/memory/processes-asc
        mockMvc.perform(get("/api/v1/memory/processes-asc"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the response body is not empty
                .andExpect(jsonPath("$[*]").isNotEmpty());
    }

    @Test
    public void testGetMemoryChartData() throws Exception {
        // Perform a GET request to /api/v1/memory/chart
        mockMvc.perform(get("/api/v1/memory/chart"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the response body is not empty
                .andExpect(jsonPath("$[*]").isNotEmpty());
    }

    @Test
    public void testGetMemoryAvgUtil5Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/memory/avg-util-5min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetMemoryAvgUtil10Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/memory/avg-util-10min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetMemoryAvgUtil15Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/memory/avg-util-15min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetMemoryAvgUtil30Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/memory/avg-util-30min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetMemoryAvgUtil1Hour() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/memory/avg-util-1hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetMemoryAvgUtil2Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/memory/avg-util-2hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetMemoryAvgUtil4Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/memory/avg-util-4hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetMemoryAvgUtil6Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/memory/avg-util-6hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetMemoryAvgUtil12Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/memory/avg-util-12hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetMemoryAvgUtil24Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/memory/avg-util-24hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }
}
