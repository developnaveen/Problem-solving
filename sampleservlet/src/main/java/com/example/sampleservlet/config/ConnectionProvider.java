package com.example.sampleservlet.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConnectionProvider {
    private static DataSource dataSource;
    private static final Properties PROPS = new Properties();
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionProvider.class);

    private ConnectionProvider(){}

    static {

        try {
        	PROPS.load(getClass().getClassLoader().getResourceAsStream("liquibase.properties"));
        
            LOGGER.info("Entered into pool");
            
            HikariConfig config = new HikariConfig();
            config.setDriverClassName(PROPS.getProperty("driver"));
            config.setJdbcUrl(PROPS.getProperty("url"));
            // root
            config.setUsername(PROPS.getProperty("username"));
            // password / bluescope
            config.setPassword(PROPS.getProperty("password"));
            // max connection
            config.setMaximumPoolSize(Integer.parseInt(PROPS.getProperty("max")));
            // min connection
            config.setMinimumIdle(Integer.parseInt(PROPS.getProperty("min")));
            // to restore the unused pool to min
            config.setIdleTimeout(Integer.parseInt(PROPS.getProperty("idletimeOut")));
            // to prevent db time out connection
            config.setMaxLifetime(Integer.parseInt(PROPS.getProperty("maxtimeOut")));
            // if all thread in use means then it happen
            config.setConnectionTimeout(Integer.parseInt(PROPS.getProperty("connectionTime")));
            dataSource = new HikariDataSource(config);
            LOGGER.info("out of the pool");
        } catch (IOException e){
            LOGGER.error("Error from the pool", e);
        }
    }



    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    public static void closeConnection() {
    	if(dataSource instanceof HikariDataSource) {
    		((HikariDataSource) dataSource).close();
    	}
    }
}

