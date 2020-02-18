package com.duny.fcr.controller;

import com.duny.fcr.entity.AdmissionPayment;
import com.duny.fcr.entity.Cash;
import com.duny.fcr.entity.TuitionFeePayment;
import com.duny.fcr.repo.AdmissionPaymentRepo;
import com.duny.fcr.repo.CashRepo;
import com.duny.fcr.repo.TuitionFeePaymentRepo;
import com.duny.fcr.service.FeeService;
import com.duny.fcr.serviceImp.UtilityClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Controller
public class CashController {
    @Autowired
    CashRepo cashRepo;
    @Autowired
    FeeService feeService;
    @Autowired
    TuitionFeePaymentRepo tuitionFeePaymentRepo;
    @Autowired
    AdmissionPaymentRepo admissionPaymentRepo;

    @PostMapping(value = "/cash/save", consumes = "application/json", produces = "application/json")
    public String saveCash(@RequestBody String cashJson){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        Cash cash = gson.fromJson(cashJson,Cash.class);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        cash.setYear(calendar.get(Calendar.YEAR)+"");
        cash.setMonth(LocalDate.now().getMonth().toString());
        cashRepo.save(cash);
        return "redirect:/admissionFee";
    }

    @GetMapping(value = "/cash/delete/{id}")
    public String deleteCash(@PathVariable Long id){
        Cash cash = cashRepo.getOne(id);
        double deletedAmount = cash.getAmount();
        String pid = cash.getPaymentId();

        String url="redirect:/tf/details/"+pid;

        cashRepo.deleteById(id);
        feeService.updateTuitionOrAdmissionFee(pid,deletedAmount,tuitionFeePaymentRepo,admissionPaymentRepo);

        return url;
    }
}
