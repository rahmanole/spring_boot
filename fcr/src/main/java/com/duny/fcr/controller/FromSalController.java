package com.duny.fcr.controller;

import com.duny.fcr.entity.Cheque;
import com.duny.fcr.entity.FromSal;
import com.duny.fcr.repo.ChequeRepo;
import com.duny.fcr.repo.FromSalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Controller
public class FromSalController {

    @Autowired
    FromSalRepo fromSalRepo;

    @PostMapping("/fromSal/save")
    public String saveCheque(@RequestParam("paymentId")String pid,
                             @RequestParam("studentId") String stId,
                             @RequestParam("chequeNum") String cNum,
                             @RequestParam("payPeriod") String payPeriod,
                             @RequestParam("amount") String amount,
                             @RequestParam("fromSalChequeImg") MultipartFile file) throws Exception {
        if(file.isEmpty()){
            return "";
        }

        FromSal fromSal = new FromSal();
        fromSal.setPaymentId(pid);
        fromSal.setStudentId(stId);
        fromSal.setChequeNum(cNum);
        fromSal.setAmount(Double.parseDouble(amount));
        fromSal.setFromSalChequeImg("data:image/jpg;base64,"+Base64.getEncoder().encodeToString(file.getBytes()));
        fromSal.setDate(new SimpleDateFormat("yyyy-mm-dd").parse(payPeriod));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        fromSal.setYear(calendar.get(Calendar.YEAR)+"");
        fromSal.setMonth(LocalDate.now().getMonth().toString());
        fromSalRepo.save(fromSal);

//        System.out.println(pid+cNum+payPeriod+amount);
//        System.out.println(Base64.getEncoder().encodeToString(file.getBytes()));

        return "redirect:/admissionFee";
    }
}
