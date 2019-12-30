package com.duny.fcr.controller;

import com.duny.fcr.entity.AdmissionPayment;
import com.duny.fcr.repo.AdmissionPaymentRepo;
import com.duny.fcr.repo.StudentRepo;
import com.duny.fcr.service.AdmissionPaymentService;
import com.duny.fcr.service.OpeningDueFeePaymentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OpeningDueFeePaymentController {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    OpeningDueFeePaymentService openingDueFeePaymentService;
    @Autowired
    AdmissionPaymentRepo admissionPaymentRepo;

    @GetMapping("/openingDueFee")
    public String colectOpeningDueFee(Model model) {
        List<String> idlist = studentRepo.getStudentWithOpeningDue();
        model.addAttribute("studentIDS",idlist);
        return "/pages/financial/openingDueCollection";
    }

    @GetMapping("/openingDueFee/getPaymentId")
    @ResponseBody
    public String getPaymentId() {
        return openingDueFeePaymentService.getOpeningDueFeePaymentId();
    }

    @PostMapping(value = "/openingDueFee/save", consumes = "application/json", produces = "application/json")
    public String saveCash(@RequestBody String admPaymentJson){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        AdmissionPayment admissionPayment = gson.fromJson(admPaymentJson, AdmissionPayment.class);
        admissionPaymentRepo.save(admissionPayment);
        return "redirect:/openingDueFee";
    }


}
