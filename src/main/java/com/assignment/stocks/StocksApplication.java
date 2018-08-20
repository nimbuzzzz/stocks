package com.assignment.stocks;

import com.assignment.stocks.services.BootUpDataLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class StocksApplication {

	@Autowired
	private BootUpDataLoaderService bootUpDataLoaderService;

	@EventListener(ContextRefreshedEvent.class)
	public void loadStartupData() {
		bootUpDataLoaderService.loadBootUpData();
	}

	public static void main(String[] args) {
		SpringApplication.run(StocksApplication.class, args);
	}
}
