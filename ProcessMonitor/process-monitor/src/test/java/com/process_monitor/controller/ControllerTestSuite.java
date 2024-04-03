package com.process_monitor.controller;

import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectClasses;

/**
 * Suite for Cpu, Memory, Disk and Process Controller tests
 */
@Suite
@SuiteDisplayName("Controller Test Suite")
@SelectClasses({CpuControllerTests.class, MemoryControllerTests.class, DiskControllerTests.class, ProcessControllerTests.class})
public class ControllerTestSuite {
    
}
