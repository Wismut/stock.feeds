package com.example.stock.feeds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableCaching
@EnableWebFlux
@EnableScheduling
@SpringBootApplication
public class StockFeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockFeedApplication.class, args);
    }
}
