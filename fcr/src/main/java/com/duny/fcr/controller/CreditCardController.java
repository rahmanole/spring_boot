package com.duny.fcr.controller;

import com.duny.fcr.entity.CreditCard;
import com.duny.fcr.repo.AdmissionPaymentRepo;
import com.duny.fcr.repo.CreditCardRepo;
import com.duny.fcr.repo.TuitionFeePaymentRepo;
import com.duny.fcr.serviceImp.FeeServiceImp;
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
public class CreditCardController {
    @Autowired
    CreditCardRepo creditCardRepo;
    @Autowired
    private FeeServiceImp feeService;
    @Autowired
    private TuitionFeePaymentRepo tuitionFeePaymentRepo;
    @Autowired
    private AdmissionPaymentRepo admissionPaymentRepo;

    @PostMapping(value = "/cc/save", consumes = "application/json", produces = "application/json")
    public String saveCash(@RequestBody String cCard){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        CreditCard creditCard = gson.fromJson(cCard,CreditCard.class);
        creditCard.setYear(LocalDate.now().getMonth()+"");
        creditCard.setMonth(LocalDate.now().getMonth().toString());

        creditCardRepo.save(creditCard);
        return "redirect:/admissionFee";
    }

    @GetMapping(value = "/cc/delete/{id}")
    public String deleteCash(@PathVariable Long id){
        CreditCard cc = creditCardRepo.getOne(id);
        double deletedAmount = cc.getAmount();
        String pid = cc.getPaymentId();

        String url="redirect:/tf/details/"+pid;

        creditCardRepo.deleteById(id);

        feeService.updateTuitionOrAdmissionFee(pid,deletedAmount,tuitionFeePaymentRepo,admissionPaymentRepo);

        return url;
    }
}
