package com.example.csvwrite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class CsvwriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvwriteApplication.class, args);
	}

}
