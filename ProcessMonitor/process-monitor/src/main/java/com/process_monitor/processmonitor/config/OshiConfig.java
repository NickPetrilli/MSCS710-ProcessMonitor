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
    }
}
