package com.example.sampleservlet.config;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties PROPS = new Properties();

    static {
        try(InputStream is = DatabaseConfig.class.getClassLoader().getResourceAsStream("db/liquibase.properties")){

            if(is == null){
                throw new RuntimeException("liquibase.properties not found");
            }
            PROPS.load(is);
            Class.forName(PROPS.getProperty("driver"));

        } catch (Exception e){
            throw new RuntimeException("Database configuration failed", e);
        }
    }

    public static String getUrl(){
        return PROPS.getProperty("url");
    }
    public static String getUsername(){
        return PROPS.getProperty("username");
    }
    public static String getPassword(){
        return PROPS.getProperty("password");
    }
}
