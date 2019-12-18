package com.duny.fcr.controller;

import com.duny.fcr.entity.Cash;
import com.duny.fcr.entity.CreditCard;
import com.duny.fcr.repo.CreditCardRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CreditCardController {
    @Autowired
    CreditCardRepo creditCardRepo;

    @PostMapping(value = "/cc/save", consumes = "application/json", produces = "application/json")
    public String saveCash(@RequestBody String cCard){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        CreditCard creditCard = gson.fromJson(cCard,CreditCard.class);
        creditCardRepo.save(creditCard);
        return "redirect:/admissionFee";
    }
}
