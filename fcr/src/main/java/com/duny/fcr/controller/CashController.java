package com.duny.fcr.controller;

import com.duny.fcr.entity.Cash;
import com.duny.fcr.repo.CashRepo;
import com.duny.fcr.serviceImp.UtilityClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Calendar;
import java.util.Date;

@Controller
public class CashController {
    @Autowired
    CashRepo cashRepo;

    @PostMapping(value = "/cash/save", consumes = "application/json", produces = "application/json")
    public String saveCash(@RequestBody String cashJson){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        Cash cash = gson.fromJson(cashJson,Cash.class);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        cash.setYear(calendar.get(Calendar.YEAR)+"");
        cash.setMonth(UtilityClass.getMonthName(calendar.get(Calendar.MONTH)));
        cashRepo.save(cash);
        return "redirect:/admissionFee";
    }
}
