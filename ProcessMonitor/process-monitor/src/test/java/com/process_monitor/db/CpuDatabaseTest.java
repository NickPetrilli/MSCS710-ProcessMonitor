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
 * Class for testing Cpu Table initialization, insertions, fetches, and deletions.
 */
@SpringBootTest(classes = DataSourceConfig.class)
@ActiveProfiles("test")
public class CpuDatabaseTest implements DatabaseTest {

    @Autowired
    private DataSource dataSource;

    private String createSql = """
                CREATE TABLE IF NOT EXISTS cpu (
                        timestamp TEXT PRIMARY KEY,
                        name TEXT,
                        speed INTEGER,
                        maxSpeed INTEGER,
                        cores INTEGER,
                        processes INTEGER,
                        threads INTEGER,
                        utilization REAL
                        );
                )""";
    private String dropSql = "DROP TABLE IF EXISTS cpu";
    private String insertSql = """
                INSERT INTO cpu (
                        timestamp,
                        name,
                        speed,
                        maxSpeed,
                        cores,
                        processes,
                        threads,
                        utilization
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                """;
    private String readSql = "SELECT * FROM cpu WHERE timestamp = ?;";
    private String deleteSql = "DELETE FROM cpu WHERE timestamp = ?;";


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
            ps.setString(2, "Intel(R) Core(TM) i7-6600U CPU @ 2.60GHz");
            ps.setLong(3, 20);
            ps.setLong(4, 200);
            ps.setInt(5, 2);
            ps.setInt(6, 30);
            ps.setInt(7, 2500);
            ps.setDouble(8, 60.5);
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
            ps.setString(2, "Intel(R) Core(TM) i7-6600U CPU @ 2.60GHz");
            ps.setLong(3, 20);
            ps.setLong(4, 200);
            ps.setInt(5, 2);
            ps.setInt(6, 30);
            ps.setInt(7, 2500);
            ps.setDouble(8, 60.5);
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
        String sql = "INSERT INTO cpu (timestamp, name) VALUES (?, ?);";

        // Preparing the database with sample data
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "2024-03-10 00:11:11");
            ps.setString(2, "Intel(R) Core(TM) i7-6600U CPU @ 2.60GHz");
            ps.executeUpdate();

            ps.setString(1, "2024-03-10 02:11:11");
            ps.setString(2, "Intel(R) Core(TM) i7-6600U CPU @ 2.60GHz");
            ps.executeUpdate();

            ps.setString(1, "2024-03-10 04:11:11");
            ps.setString(2, "Intel(R) Core(TM) i7-6600U CPU @ 2.60GHz");
            ps.executeUpdate();
        }

        // Retrieving all records from the Cpu table
        List<String> timestamps = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT timestamp, name FROM cpu")) {
            while (rs.next()) {
                timestamps.add(rs.getString("timestamp"));
            }
        }

        // Asserting the number of records fetched
        assertEquals(3, timestamps.size(), "The number of timestamps retrieved from the database does not match the expected value.");
    }
}

