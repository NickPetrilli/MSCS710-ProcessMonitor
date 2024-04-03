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
 * Class for testing Disk Table initialization, insertions, fetches, and deletions.
 */
@SpringBootTest(classes = DataSourceConfig.class)
@ActiveProfiles("test")
public class DiskDatabaseTest implements DatabaseTest {

    @Autowired
    private DataSource dataSource;

    private String createSql = """
                CREATE TABLE IF NOT EXISTS disk (
                        timestamp TEXT,
                        name TEXT,
                        model TEXT,
                        swapTotal INTEGER,
                        swapUsed INTEGER,
                        swapUtilization REAL,
                        totalReadBytes INTEGER,
                        totalWriteBytes INTEGER,
                        readSpeed INTEGER,
                        writeSpeed INTEGER,
                        utilization REAL,
                        PRIMARY KEY (timestamp, name)
                )""";
    private String dropSql = "DROP TABLE IF EXISTS disk";
    private String insertSql = """
                INSERT INTO disk (
                        timestamp,
                        name,
                        model,
                        swapTotal,
                        swapUsed,
                        swapUtilization,
                        totalReadBytes,
                        totalWriteBytes,
                        readSpeed,
                        writeSpeed,
                        utilization
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;
    private String readSql = "SELECT * FROM disk WHERE timestamp = ?;";
    private String deleteSql = "DELETE FROM disk WHERE timestamp = ?;";


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
            ps.setString(2, "PHYSICALDRIVE0");
            ps.setString(3, "SAMSUNG M-000H1 (Standard disk drives)");
            ps.setLong(4, 5100273664L);
            ps.setLong(5, 61349888L);
            ps.setDouble(6, 0.012);
            ps.setLong(7, 61556468224L);
            ps.setLong(8, 93388031488L);
            ps.setLong(9, 0);
            ps.setLong(10, 507904L);
            ps.setDouble(11, 0.60);
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
            ps.setString(2, "PHYSICALDRIVE0");
            ps.setString(3, "SAMSUNG M-000H1 (Standard disk drives)");
            ps.setLong(4, 5100273664L);
            ps.setLong(5, 61349888L);
            ps.setDouble(6, 0.012);
            ps.setLong(7, 61556468224L);
            ps.setLong(8, 93388031488L);
            ps.setLong(9, 0);
            ps.setLong(10, 507904L);
            ps.setDouble(11, 0.60);
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
        String sql = "INSERT INTO disk (timestamp, name) VALUES (?, ?);";

        // Preparing the database with sample data
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "2024-03-10 00:11:11");
            ps.setString(2, "SAMSUNG M-000H1 (Standard disk drives)");
            ps.executeUpdate();

            ps.setString(1, "2024-03-10 02:11:11");
            ps.setString(2, "SAMSUNG M-000H2 (Standard disk drives)");
            ps.executeUpdate();

            ps.setString(1, "2024-03-10 04:11:11");
            ps.setString(2, "SAMSUNG M-000H3 (Standard disk drives)");
            ps.executeUpdate();
        }

        // Retrieving all records from the Disk table
        List<String> timestamps = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT timestamp, name FROM disk")) {
            while (rs.next()) {
                timestamps.add(rs.getString("timestamp"));
            }
        }

        // Asserting the number of records fetched
        assertEquals(3, timestamps.size(), "The number of timestamps retrieved from the database does not match the expected value.");
    }
}

