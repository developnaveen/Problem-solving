package com.example.sampleservlet.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {
    private static DataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DatabaseConfig.getUrl());
        config.setUsername(DatabaseConfig.getUsername());
        config.setPassword(DatabaseConfig.getPassword());
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setPoolName("Naveen Hikari pool");

        dataSource = new HikariDataSource(config);
    }

    private ConnectionProvider(){}

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

