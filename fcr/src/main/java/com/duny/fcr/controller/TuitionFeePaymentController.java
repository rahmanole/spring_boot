package com.duny.fcr.controller;

import com.duny.fcr.dto.ITuitionFeeDueReport;
import com.duny.fcr.dto.TuitionFeeDueReport;
import com.duny.fcr.entity.TuitionFeePayment;
import com.duny.fcr.repo.StudentRepo;
import com.duny.fcr.repo.TuitionFeePaymentRepo;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class TuitionFeePaymentController {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TuitionFeePaymentService tuitionFeePaymentService;

    @Autowired
    TuitionFeePaymentRepo tuitionFeePaymentRepo;



    @GetMapping("/tuitionFee")
    public String colectTuitnFee(Model model) {
        List<String> idlist = studentRepo.getStudentIds();
        model.addAttribute("studentIDS",idlist);
        return "/pages/financial/tuitionFee";
    }

    @GetMapping("/tuitionFee/due")
    public String tuitnFeeDue() {
        return "/pages/financial/StWithTuitionDue";
    }

    @GetMapping("/tuitionFee/getPaymentId")
    @ResponseBody
    public String getPaymentId() {
        return tuitionFeePaymentService.getTuitionFeePaymentId();
    }

    @PostMapping(value = "/tuitionFee/save", consumes = "application/json", produces = "application/json")
    public String saveCash(@RequestBody String tuitionFeePaymentJson){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        TuitionFeePayment tuitionFeePayment = gson.fromJson(tuitionFeePaymentJson, TuitionFeePayment.class);
        System.out.println(tuitionFeePayment.getTuitionFeeDue());
        tuitionFeePaymentRepo.save(tuitionFeePayment);
        return "redirect:/tuitionFee";
    }


    @GetMapping("/tuitionFee/tfDue")
    @ResponseBody
    public List<TuitionFeeDueReport> getStudentsWtihTuionDue(Model model) {
        List<ITuitionFeeDueReport>  p = tuitionFeePaymentRepo.getTuitionFeeDueReport();
        List<TuitionFeeDueReport> studentListWithDue = new ArrayList<>();
        for (ITuitionFeeDueReport s:p) {
            TuitionFeeDueReport tfp = new TuitionFeeDueReport();
            tfp.setName(s.getName());
            tfp.setStID(s.getStudentId());
            tfp.setDob(s.getDob());
            tfp.setDue(s.getDue());
            studentListWithDue.add(tfp);
        }
        model.addAttribute("studentListWithDues","studentListWithDue");

        System.out.println(studentListWithDue.size());
        return studentListWithDue;
    }

}
