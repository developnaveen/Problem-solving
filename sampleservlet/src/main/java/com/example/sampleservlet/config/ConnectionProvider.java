package com.example.sampleservlet.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionProvider {

    private static DataSource dataSource;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ConnectionProvider.class);

    private ConnectionProvider() {}

    static {
        initDataSource();
    }

    private static void initDataSource() {
        Properties props = new Properties();

        try (InputStream is = ConnectionProvider.class
                .getClassLoader()
                .getResourceAsStream("db/liquibase.properties")) {

            if (is == null) {
                throw new IllegalStateException(
                        "db/liquibase.properties not found in classpath");
            }

            props.load(is);

            HikariConfig config = new HikariConfig();
            config.setDriverClassName(props.getProperty("driver"));
            config.setJdbcUrl(props.getProperty("url"));
            config.setUsername(props.getProperty("username"));
            config.setPassword(props.getProperty("password"));

            config.setMaximumPoolSize(
                    Integer.parseInt(props.getProperty("max")));
            config.setMinimumIdle(
                    Integer.parseInt(props.getProperty("min")));
            config.setIdleTimeout(
                    Long.parseLong(props.getProperty("idletimeOut")));
            config.setMaxLifetime(
                    Long.parseLong(props.getProperty("maxtimeOut")));
            config.setConnectionTimeout(
                    Long.parseLong(props.getProperty("connectionTime")));

            dataSource = new HikariDataSource(config);

            LOGGER.info("HikariCP pool initialized successfully");

        } catch (Exception e) {
            LOGGER.error("Failed to initialize datasource", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new IllegalStateException("Datasource not initialized");
        }
        return dataSource.getConnection();
    }

    public static void shutdown() {
        if (dataSource instanceof HikariDataSource hikari) {
            hikari.close();
            LOGGER.info("HikariCP pool closed");
        }
    }
}
