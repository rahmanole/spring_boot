package com.duny.fcr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication(scanBasePackages = {"com.duny.fcr","com.duny.fcr.service"})
public class FcrApplication {

    public static void main(String[] args) {
        SpringApplication.run(FcrApplication.class, args);
    }

}
