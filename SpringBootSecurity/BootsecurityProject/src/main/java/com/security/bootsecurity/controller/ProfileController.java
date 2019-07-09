package com.security.bootsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ProfileController {

    @GetMapping("/profile")
    public String home(){
        return "profile";
    }
}
