package com.duny.fcr.controller;

import com.duny.fcr.entity.MoneyOrder;
import com.duny.fcr.repo.MoneyOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Controller
public class MoneyOrderController {
    @Autowired
    MoneyOrderRepo moneyOrderRepo;

    @PostMapping("/mo/save")
    public String saveCheque(@RequestParam("paymentID")String pid,
                             @RequestParam("studentId") String stId,
                             @RequestParam("moneyOrderDate") String date,
                             @RequestParam("moneyOrderNum") String mOnum,
                             @RequestParam("amount") String amnt,
                             @RequestParam("moneyOrderImg") MultipartFile file) throws Exception {

        MoneyOrder moneyOrder = new MoneyOrder();

        moneyOrder.setAmount(Double.parseDouble(amnt));
        moneyOrder.setPaymentID(pid);
        moneyOrder.setStudentId(stId);
        moneyOrder.setMoneyOrderNum(mOnum);
        moneyOrder.setMoneyOrderImg(file.getBytes());
        moneyOrder.setMoneyOrderDate(new SimpleDateFormat("yyyy-mm-dd").parse(date));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        moneyOrder.setYear(calendar.get(Calendar.YEAR)+"");
        moneyOrderRepo.save(moneyOrder);
        return "redirect:/admissionFee";
    }
}
