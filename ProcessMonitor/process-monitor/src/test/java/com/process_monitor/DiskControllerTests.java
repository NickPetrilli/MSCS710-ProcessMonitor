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
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetDiskTopProcesses() throws Exception {
        // Perform a GET request to /api/v1/disk/top-processes
        mockMvc.perform(get("/api/v1/disk/top-processes"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetDiskProcessesDesc() throws Exception {
        // Perform a GET request to /api/v1/disk/processes
        mockMvc.perform(get("/api/v1/disk/processes"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetDiskProcessesAsc() throws Exception {
        // Perform a GET request to /api/v1/disk/processes-asc
        mockMvc.perform(get("/api/v1/disk/processes-asc"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    //TODO: Alter test below with disk name parameter
    /*
    @Test
    public void testGetDiskChartData() throws Exception {
        // Perform a GET request to /api/v1/disk/chart/{name}
        mockMvc.perform(get("/api/v1/disk/chart/{name}"))
               // Expect the status code 200 (OK)
               .andExpect(status().isOk())
               // Expect the content type to be JSON
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    } 
     */

}
