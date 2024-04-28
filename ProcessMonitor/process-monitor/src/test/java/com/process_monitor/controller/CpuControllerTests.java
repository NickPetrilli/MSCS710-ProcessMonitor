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
 * Class for testing all API endpoints for Cpu Controller
 */
@SpringBootTest(classes = ProcessMonitorApplication.class)
@AutoConfigureMockMvc
public class CpuControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCpuData() throws Exception {
        // Perform a GET request to /api/v1/cpu
        mockMvc.perform(get("/api/v1/cpu"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               // Assert that the response body is not empty
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetCpuTopProcesses() throws Exception {
        // Perform a GET request to /api/v1/cpu/top-processes
        mockMvc.perform(get("/api/v1/cpu/top-processes"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the response body is not empty
                //[*] ensures that the result is an array of values
                .andExpect(jsonPath("$[*]").isNotEmpty());
    }

    @Test
    public void testGetCpuProcessesDesc() throws Exception {
        // Perform a GET request to /api/v1/cpu/processes
        mockMvc.perform(get("/api/v1/cpu/processes"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the response body is not empty
                .andExpect(jsonPath("$[*]").isNotEmpty());
    }

    @Test
    public void testGetCpuProcessesAsc() throws Exception {
        // Perform a GET request to /api/v1/cpu/processes-asc
        mockMvc.perform(get("/api/v1/cpu/processes-asc"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the response body is not empty
                .andExpect(jsonPath("$[*]").isNotEmpty());
    }

    @Test
    public void testGetCpuChartData() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/cpu/chart"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the response body is not empty
                .andExpect(jsonPath("$[*]").isNotEmpty());
    }

    @Test
    public void testGetCpuAvgUtil5Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/cpu/avg-util-5min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
                // Assert that the response body is not empty
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetCpuAvgUtil10Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/cpu/avg-util-10min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
                // Assert that the response body is not empty
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetCpuAvgUtil15Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/cpu/avg-util-15min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
                // Assert that the response body is not empty
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetCpuAvgUtil30Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/cpu/avg-util-30min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
                // Assert that the response body is not empty
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetCpuAvgUtil1Hour() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/cpu/avg-util-1hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
                // Assert that the response body is not empty
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetCpuAvgUtil2Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/cpu/avg-util-2hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
                // Assert that the response body is not empty
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetCpuAvgUtil4Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/cpu/avg-util-4hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
                // Assert that the response body is not empty
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetCpuAvgUtil6Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/cpu/avg-util-6hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
                // Assert that the response body is not empty
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetCpuAvgUtil12Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/cpu/avg-util-12hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
                // Assert that the response body is not empty
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetCpuAvgUtil24Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/cpu/avg-util-24hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
                // Assert that the response body is not empty
                .andExpect(jsonPath("$").isNotEmpty());
    }
}
