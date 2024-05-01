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
 * Class for testing all API endpoints for Disk Controller
 */
@SpringBootTest(classes = ProcessMonitorApplication.class)
@AutoConfigureMockMvc
public class DiskControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetDiskData() throws Exception {
        // Perform a GET request to /api/v1/disk
        mockMvc.perform(get("/api/v1/disk"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the response body is not empty
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetDiskTopProcesses() throws Exception {
        // Perform a GET request to /api/v1/disk/top-processes
        mockMvc.perform(get("/api/v1/disk/top-processes"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the response body is not empty
                .andExpect(jsonPath("$[*]").isNotEmpty());
    }

    @Test
    public void testGetDiskProcessesDesc() throws Exception {
        // Perform a GET request to /api/v1/disk/processes
        mockMvc.perform(get("/api/v1/disk/processes"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the response body is not empty
                .andExpect(jsonPath("$[*]").isNotEmpty());
    }

    @Test
    public void testGetDiskProcessesAsc() throws Exception {
        // Perform a GET request to /api/v1/disk/processes-asc
        mockMvc.perform(get("/api/v1/disk/processes-asc"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the response body is not empty
                .andExpect(jsonPath("$[*]").isNotEmpty());
    }

    @Test
    public void testGetDiskChartData() throws Exception {
        //Define a disk name as a parameter for the chart endpoint
        String diskName = "NVMe PC SN740 NVMe WD 512GB (Standard disk drives)";
        // Perform a GET request to /api/v1/disk/chart/{name}
        mockMvc.perform(get("/api/v1/disk/chart/{name}", diskName))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgUtil5Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-util-5min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgUtil10Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-util-10min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgUtil15Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-util-15min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgUtil30Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-util-30min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgUtil1Hour() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-util-1hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgUtil2Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-util-2hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgUtil4Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-util-4hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgUtil6Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-util-6hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgUtil12Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-util-12hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgUtil24Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-util-24hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgSpeeds5Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-speeds-5min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgSpeeds10Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-speeds-10min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgSpeeds15Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-speeds-15min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgSpeeds30Min() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-speeds-30min"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgSpeeds1Hour() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-speeds-1hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgSpeeds2Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-speeds-2hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgSpeeds4Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-speeds-4hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgSpeeds6Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-speeds-6hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgSpeeds12Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-speeds-12hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }

    @Test
    public void testGetDiskAvgSpeeds24Hours() throws Exception {
        // Perform a GET request to /api/v1/cpu/chart
        mockMvc.perform(get("/api/v1/disk/avg-speeds-24hour"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk());
    }
}
