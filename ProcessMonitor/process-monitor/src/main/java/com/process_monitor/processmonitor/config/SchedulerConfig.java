package com.process_monitor.processmonitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Configuration class needed for scheduling the MetricCollector.java class to
 * be scheduled to execute on a new thread every 10 seconds.
 */
@Configuration
public class SchedulerConfig {
    @Bean
    public TaskScheduler taskScheduler() {
        return new ThreadPoolTaskScheduler();
    }
}
