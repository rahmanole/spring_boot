package com.duny.fcr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan({"com.duny.fcr","com.duny.fcr.service","com.duny.fcr.serviceImp","com.duny.fcr.controller","com.duny.fcr.repo"})
@SpringBootApplication(scanBasePackages = {"com.duny.fcr"})
public class FcrApplication {

    public static void main(String[] args) {
        SpringApplication.run(FcrApplication.class, args);
    }

}
