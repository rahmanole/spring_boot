package com.duny.fcr.controller;

import com.duny.fcr.entity.Cash;
import com.duny.fcr.entity.MoneyOrder;
import com.duny.fcr.entity.Zelle;
import com.duny.fcr.repo.AdmissionPaymentRepo;
import com.duny.fcr.repo.TuitionFeePaymentRepo;
import com.duny.fcr.repo.ZelleReo;
import com.duny.fcr.serviceImp.FeeServiceImp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Calendar;
import java.util.Date;

@Controller
public class ZelleController {
    @Autowired
    ZelleReo zelleReo;
    @Autowired
    private FeeServiceImp feeService;
    @Autowired
    private TuitionFeePaymentRepo tuitionFeePaymentRepo;
    @Autowired
    private AdmissionPaymentRepo admissionPaymentRepo;

    @PostMapping(value = "/zelle/save", consumes = "application/json", produces = "application/json")
    public String saveZelle(@RequestBody String zelleJson){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        Zelle zelle = gson.fromJson(zelleJson,Zelle.class);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        zelle.setYear(calendar.get(Calendar.YEAR)+"");
        zelleReo.save(zelle);
        return "redirect:/admissionFee";
    }


    @GetMapping(value = "/zelle/delete/{id}")
    public String deleteCash(@PathVariable Long id){
        Zelle zelle = zelleReo.getOne(id);
        double deletedAmount = zelle.getAmount();
        String pid = zelle.getPaymentId();

        String url="redirect:/tf/details/"+pid;

        zelleReo.deleteById(id);

        feeService.updateTuitionOrAdmissionFee(pid,deletedAmount,tuitionFeePaymentRepo,admissionPaymentRepo);

        return url;
    }
}
