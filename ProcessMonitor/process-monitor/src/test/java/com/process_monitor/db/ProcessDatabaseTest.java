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
 * Class for testing Process Table initialization, insertions, fetches, and deletions.
 */
@SpringBootTest(classes = DataSourceConfig.class)
@ActiveProfiles("test")
public class ProcessDatabaseTest implements DatabaseTest {

    @Autowired
    private DataSource dataSource;

    private String createSql = """
                CREATE TABLE IF NOT EXISTS process (
                    process_id INTEGER,
                    timestamp TEXT,
                    name TEXT,
                    status TEXT,
                    cpuPercentage REAL,
                    memUsageBytes INTEGER,
                    memPercentage REAL,
                    diskSpeed REAL,
                    diskPercentage REAL,
                    PRIMARY KEY (process_id, timestamp)
                )""";
    private String dropSql = "DROP TABLE IF EXISTS process";
    private String insertSql = """
                INSERT INTO process (
                    process_id,
                    timestamp,
                    name,
                    status,
                    cpuPercentage,
                    memUsageBytes,
                    memPercentage,
                    diskSpeed,
                    diskPercentage
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;
    private String readSql = "SELECT * FROM process WHERE process_id = ?;";
    private String deleteSql = "DELETE FROM process WHERE process_id = ?;";


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
            ps.setInt(1, 1);
            ps.setString(2, "2024-03-30 11:11:11");
            ps.setString(3, "chrome");
            ps.setString(4, "RUNNING");
            ps.setDouble(5, 11.11);
            ps.setInt(6, 11);
            ps.setDouble(7, 11.11);
            ps.setInt(8, 0);
            ps.setDouble(9, 0);
            ps.executeUpdate();
        }

        // Verify the record was inserted
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(readSql)) {
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
        }
    }

    @Test
    public void testDeleteData() throws Exception {
        // Insert a test record
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setInt(1, 1);
            ps.setString(2, "2024-03-10 11:11:11");
            ps.setString(3, "chrome");
            ps.setString(4, "RUNNING");
            ps.setDouble(5, 11.11);
            ps.setInt(6, 11);
            ps.setDouble(7, 11.11);
            ps.setInt(8, 0);
            ps.setDouble(9, 0);
            ps.executeUpdate();
        }

        // Delete the test record
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteSql)) {
            ps.setInt(1, 1);
            ps.executeUpdate();
        }

        // Verify the record was deleted
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(readSql)) {
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            assertFalse(rs.next(), "The record was deleted.");
        }
    }

    @Test
    public void testInsertAndReadMultipleData() throws Exception {
        String sql = "INSERT INTO process (process_id, timestamp, name) VALUES (?, ?, ?);";

        // Preparing the database with sample data
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, 1);
            ps.setString(2, "2024-03-10 00:11:11");
            ps.setString(3, "chrome");
            ps.executeUpdate();

            ps.setInt(1, 2);
            ps.setString(2, "2024-03-10 02:11:11");
            ps.setString(3, "Discord");
            ps.executeUpdate();

            ps.setInt(1, 3);
            ps.setString(2, "2024-03-10 04:11:11");
            ps.setString(3, "test");
            ps.executeUpdate();
        }

        // Retrieving all records from the Process table
        List<String> names = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM process")) {
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        }

        // Asserting the number of records fetched
        assertEquals(3, names.size(), "The number of processes retrieved from the database does not match the expected value.");
    }
}

