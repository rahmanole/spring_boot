package com.duny.fcr.controller;

import com.duny.fcr.entity.Cash;
import com.duny.fcr.entity.CreditCard;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CreditCardController {

    @PostMapping(value = "/cc/save", consumes = "application/json", produces = "application/json")
    public String saveCash(@RequestBody String cCard){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        CreditCard creditCard = gson.fromJson(cCard,CreditCard.class);
        System.out.println(creditCard.getAmount());
        return "redirect:/admissionFee";
    }
}
