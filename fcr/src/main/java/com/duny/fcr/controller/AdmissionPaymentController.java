package com.duny.fcr.controller;

import com.duny.fcr.entity.AdmissionPayment;
import com.duny.fcr.entity.Cash;
import com.duny.fcr.repo.AdmissionPaymentRepo;
import com.duny.fcr.repo.StudentRepo;
import com.duny.fcr.service.AdmissionPaymentService;
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
public class AdmissionPaymentController {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    AdmissionPaymentService admissionPaymentService;
    @Autowired
    AdmissionPaymentRepo admissionPaymentRepo;

    @GetMapping("/admissionFee")
    public String colectAdmisnFee(Model model) {
        List<String> idlist = studentRepo.getStudentIds();
        model.addAttribute("studentIDS",idlist);
        return "/pages/financial/admissionFee";
    }

    @GetMapping("/admissionFee/getPaymentId")
    @ResponseBody
    public String getPaymentId() {
        return admissionPaymentService.getAdmissionFeePaymentId();
    }

    @PostMapping(value = "/admFee/save", consumes = "application/json", produces = "application/json")
    public String saveCash(@RequestBody String admPaymentJson){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        AdmissionPayment admissionPayment = gson.fromJson(admPaymentJson, AdmissionPayment.class);
        //admissionPaymentRepo.save(admissionPayment);
        return "redirect:/admissionFee";
    }


}
