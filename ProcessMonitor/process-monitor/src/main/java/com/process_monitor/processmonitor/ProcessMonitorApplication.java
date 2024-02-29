package com.process_monitor.processmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The main class for running Spring Boot application backend.
 */
@SpringBootApplication
@EnableScheduling
public class ProcessMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessMonitorApplication.class, args);
	}

}
