package com.assignment.stocks;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class StocksApplication {

	private static final Class<StocksApplication> applicationClass = StocksApplication.class;

	private static final Logger log = LoggerFactory.getLogger(applicationClass);

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}
}
