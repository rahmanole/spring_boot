package com.globalBookShop.gsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewsAggregationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsAggregationApplication.class, args);
    }

}
