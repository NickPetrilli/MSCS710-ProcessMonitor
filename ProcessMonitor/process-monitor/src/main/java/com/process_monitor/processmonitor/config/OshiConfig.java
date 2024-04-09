package com.process_monitor.processmonitor.config;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import oshi.util.GlobalConfig;

@Component
public class OshiConfig {
    
    @PostConstruct
    public void init() {
        // Enable the setting for OSHI's CPU utility on Windows to match Task Manager
        GlobalConfig.set(GlobalConfig.OSHI_OS_WINDOWS_CPU_UTILITY, true);
        //General TODO:
        //CPU utilization still isn't matching task manager correctly
        //Also, when adding utilization metrics into the graph, the newest points should be on the
        //right most side, they are currently being added to the left most side
        //When only including this OSHI Config code, utilization is still higher than task manager
        //Either alter the query to change how it is returned, or reverse the order on the frontend

        //But when also using the code to remove the IDLE process, CPU usage is very low
        //Typically shows 55% when usage is around 27%, should we divide it by 2??
    }
}
