package com.minhaz.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableScheduling
//@EnableAsync
public class MyappApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyappApplication.class, args);
    }

//    @Bean("threadPoolTaskExecutor")
//    public TaskExecutor getAyncExecutor(){
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//
//        executor.setCorePoolSize(20);
//        executor.setMaxPoolSize(100);
//        executor.setWaitForTasksToCompleteOnShutdown(true);
//        executor.setThreadNamePrefix("Async-");
//
//        return executor;
//    }

}
