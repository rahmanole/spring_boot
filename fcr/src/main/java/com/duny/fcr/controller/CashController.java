package com.duny.fcr.controller;

import com.duny.fcr.entity.Cash;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CashController {

    @PostMapping(value = "/cash/save", consumes = "application/json", produces = "application/json")
    public String saveCash(@RequestBody String cashJson){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        Cash cash = gson.fromJson(cashJson,Cash.class);
        System.out.println(cash.getAmount());
        return "redirect:/admissionFee";
    }
}
