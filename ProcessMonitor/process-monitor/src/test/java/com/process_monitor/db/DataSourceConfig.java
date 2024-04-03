package com.process_monitor.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Configuration class for the test database.
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("test") // This ensures that this configuration is only active when the "test" profile is active
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:TestDatabase.db");
        return dataSource;
    }
}