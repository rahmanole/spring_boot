package com.security.bootsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class Manager {

    @GetMapping("/manager")
    public String home(){
        return "manager";
    }
}
