package com.vixenit.diningproject.diningproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String addDept(){
        return "index";
    }
}
