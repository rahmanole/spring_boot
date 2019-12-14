package com.duny.fcr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
public class MoneyOrderController {

    @PostMapping("/mo/save")
    public String saveCheque(@RequestParam("paymentID")String pid,
                             @RequestParam("moneyOrderDate") String date,
                             @RequestParam("moneyOrderNum") String mOnum,
                             @RequestParam("amount") String amnt,
                             @RequestParam("moneyOrderImg") MultipartFile file) throws IOException {
        System.out.println(pid+date+mOnum+amnt);
        System.out.println(Base64.getEncoder().encodeToString(file.getBytes()));
        return "redirect:/admissionFee";
    }
}
