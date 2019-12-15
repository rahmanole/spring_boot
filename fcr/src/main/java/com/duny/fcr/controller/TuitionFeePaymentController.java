package com.duny.fcr.controller;

import com.duny.fcr.entity.AdmissionPayment;
import com.duny.fcr.repo.StudentRepo;
import com.duny.fcr.service.AdmissionPaymentService;
import com.duny.fcr.service.TuitionFeePaymentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TuitionFeePaymentController {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TuitionFeePaymentService tuitionFeePaymentService;



    @GetMapping("/tuitionFee")
    public String colectTuitnFee(Model model) {
        model.addAttribute("studentIDS", studentRepo.getStudentIds());
        return "/pages/financial/tuitionFee";
    }

    @GetMapping("/tuitionFee/getPaymentId")
    @ResponseBody
    public String getPaymentId() {
        return tuitionFeePaymentService.getTuitionFeePaymentId();
    }

    @PostMapping(value = "/tuitionFee/save", consumes = "application/json", produces = "application/json")
    public String saveCash(@RequestBody String admPaymentJson){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        AdmissionPayment admissionPayment = gson.fromJson(admPaymentJson, AdmissionPayment.class);
        System.out.println(admissionPayment.getAdmissionFeeDue());
        return "redirect:/tuitionFee";
    }


}
