package com.duny.fcr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeeController {

    @GetMapping("fee/report")
    public String generateFee(){
        return "pages/feeReport";
    }
}
