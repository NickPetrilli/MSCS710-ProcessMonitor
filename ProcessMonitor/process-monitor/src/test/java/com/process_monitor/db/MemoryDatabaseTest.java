package com.process_monitor.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing Memory Table initialization, insertions, fetches, and deletions.
 */
@SpringBootTest(classes = DataSourceConfig.class)
@ActiveProfiles("test")
public class MemoryDatabaseTest implements DatabaseTest {

    @Autowired
    private DataSource dataSource;

    private String createSql = """
                CREATE TABLE IF NOT EXISTS memory (
                        timestamp TEXT PRIMARY KEY,
                        totalMemory INTEGER,
                        availableMemory INTEGER,
                        usedMemory INTEGER,
                        utilization REAL
                )""";
    private String dropSql = "DROP TABLE IF EXISTS memory";
    private String insertSql = """
                INSERT INTO memory (
                        timestamp,
                        totalMemory,
                        availableMemory,
                        usedMemory,
                        utilization
                )
                VALUES (?, ?, ?, ?, ?);
                """;
    private String readSql = "SELECT * FROM memory WHERE timestamp = ?;";
    private String deleteSql = "DELETE FROM memory WHERE timestamp = ?;";


    @BeforeEach
    public void setUp() throws Exception {
        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement()) {
            statement.execute(createSql);
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement()) {
            statement.execute(dropSql);
        }
    }

    @Test
    public void testInsertAndReadData() throws Exception {
        // Insert a test record
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setString(1, "2024-03-13 15:20:00");
            ps.setLong(2, 2000);
            ps.setLong(3, 1500);
            ps.setLong(4, 500);
            ps.setDouble(5, 0.25);
            ps.executeUpdate();
        }

        // Verify the record was inserted
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(readSql)) {
            ps.setString(1, "2024-03-13 15:20:00");
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
        }
    }

    @Test
    public void testDeleteData() throws Exception {
        // Insert a test record
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setString(1, "2024-03-13 15:20:00");
            ps.setLong(2, 2000);
            ps.setLong(3, 1500);
            ps.setLong(4, 500);
            ps.setDouble(5, 0.25);
            ps.executeUpdate();
        }

        // Delete the test record
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteSql)) {
            ps.setString(1, "2024-03-13 15:20:00");
            ps.executeUpdate();
        }

        // Verify the record was deleted
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(readSql)) {
            ps.setString(1, "2024-03-13 15:20:00");
            ResultSet rs = ps.executeQuery();
            assertFalse(rs.next(), "The record was deleted.");
        }
    }

    @Test
    public void testInsertAndReadMultipleData() throws Exception {
        String sql = "INSERT INTO memory (timestamp, utilization) VALUES (?, ?);";

        // Preparing the database with sample data
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "2024-03-10 00:11:11");
            ps.setDouble(2, 0.25);
            ps.executeUpdate();

            ps.setString(1, "2024-03-10 02:11:11");
            ps.setDouble(2, 0.30);
            ps.executeUpdate();

            ps.setString(1, "2024-03-10 04:11:11");
            ps.setDouble(2, 0.35);
            ps.executeUpdate();
        }

        // Retrieving all records from the Memory table
        List<String> timestamps = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT timestamp, utilization FROM memory")) {
            while (rs.next()) {
                timestamps.add(rs.getString("timestamp"));
            }
        }

        // Asserting the number of records fetched
        assertEquals(3, timestamps.size(), "The number of timestamps retrieved from the database does not match the expected value.");
    }
}

