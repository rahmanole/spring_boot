package com.minhaz.MyProject;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {


    public void show() {
        System.out.println(new Date());
    }
}
