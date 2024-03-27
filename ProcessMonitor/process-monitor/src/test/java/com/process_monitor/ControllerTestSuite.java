package com.process_monitor;

import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectClasses;

@Suite
@SuiteDisplayName("Controller Test Suite")
@SelectClasses({CpuControllerTests.class, MemoryControllerTests.class, DiskControllerTests.class, ProcessControllerTests.class})
public class ControllerTestSuite {
    
}
