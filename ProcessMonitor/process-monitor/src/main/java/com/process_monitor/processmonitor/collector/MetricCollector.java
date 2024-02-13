package com.process_monitor.processmonitor.collector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Class to collect computer metrics.
 * It is scheduled to collect every 30 seconds.
 *
 * Documentation used to implement schedule:
 * https://medium.com/@AlexanderObregon/scheduling-tasks-in-spring-applications-a-practical-guide-8d28d35493c6#:~:text=The%20simplest%20way%20to%20schedule,according%20to%20the%20specified%20schedule.
 */
@Component
public class MetricCollector {

    private static final Logger logger = LoggerFactory.getLogger(MetricCollector.class);

    // Collects metrics every 30 seconds.
    @Scheduled(fixedRate = 30000)
    public void collectMetrics() {
        logger.info("Metric Collection performed at {}", LocalDateTime.now());

        System.out.println("METRICS COLLECTION");

        // TODO: implement metric collection
    }

}
