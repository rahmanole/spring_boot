package com.duny.fcr.controller;

import com.duny.fcr.entity.MoneyOrder;
import com.duny.fcr.repo.MoneyOrderRepo;
import com.duny.fcr.serviceImp.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Controller
public class MoneyOrderController {
    @Autowired
    MoneyOrderRepo moneyOrderRepo;

    @PostMapping("/mo/save")
    public String saveCheque(@RequestParam("paymentId")String pid,
                             @RequestParam("studentId") String stId,
                             @RequestParam("moneyOrderDate") String date,
                             @RequestParam("moneyOrderNum") String mOnum,
                             @RequestParam("amount") String amnt,
                             @RequestParam("moneyOrderImg") MultipartFile file) throws Exception {

        MoneyOrder moneyOrder = new MoneyOrder();

        moneyOrder.setAmount(Double.parseDouble(amnt));
        moneyOrder.setPaymentId(pid);
        moneyOrder.setStudentId(stId);
        moneyOrder.setMoneyOrderNum(mOnum);
        moneyOrder.setYear(LocalDate.now().getYear()+"");
        moneyOrder.setMonth(LocalDate.now().getMonth().toString());
        moneyOrder.setMoneyOrderImg("data:image/jpg;base64,"+Base64.getEncoder().encodeToString(file.getBytes()));
        moneyOrder.setMoneyOrderDate(new SimpleDateFormat("yyyy-mm-dd").parse(date));

        moneyOrderRepo.save(moneyOrder);
        return "redirect:/admissionFee";
    }
}
