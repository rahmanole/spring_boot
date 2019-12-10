package com.duny.fcr.controller;

import com.duny.fcr.repo.StudentRepo;
import com.duny.fcr.service.AdmissionPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdmissionPaymentController {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    AdmissionPaymentService admissionPaymentService;

    @GetMapping("/admissionFee")
    public String colectAdmisnFee(Model model) {
        model.addAttribute("studentIDS", studentRepo.getStudentIds());
        return "/pages/financial/admissionFee";
    }

    @GetMapping("/admissionFee/getPaymentId")
    @ResponseBody
    public String getPaymentId() {
        return admissionPaymentService.getAdmissionPaymentId();
    }


}
