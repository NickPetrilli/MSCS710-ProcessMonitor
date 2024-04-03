package com.process_monitor.db;

import org.junit.jupiter.api.Test;

public interface DatabaseTest {
    void setUp() throws Exception;
    void tearDown() throws Exception;
    void testInsertAndReadData() throws Exception;
    void testDeleteData() throws Exception;
    void testInsertAndReadMultipleData() throws Exception;
}
