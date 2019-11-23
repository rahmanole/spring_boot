package com.duny.fcr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/basicForm")
    public String getForms(){
        return "pages/forms/basic-form-elements";
    }
}
