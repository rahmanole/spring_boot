package com.duny.fcr.controller;

import com.duny.fcr.dto.ITuitionFeeDueReport;
import com.duny.fcr.dto.TuitionFeeDueReport;
import com.duny.fcr.entity.TuitionFeePayment;
import com.duny.fcr.repo.*;
import com.duny.fcr.service.TuitionFeePaymentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    CashRepo cashRepo;
    @Autowired
    ChequeRepo chequeRepo;
    @Autowired
    CreditCardRepo creditCardRepo;
    @Autowired
    MoneyOrderRepo moneyOrderRepo;
    @Autowired
    FromSalRepo fromSalRepo;
    @Autowired
    ZelleReo zelleReo;



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

    @GetMapping("/tuitionFee/{stId}/{year}/{month}")
    @ResponseBody
    public double getAmount(@PathVariable String stId, @PathVariable String year, @PathVariable String month) {

        double totalAmount;

        Object amount = cashRepo.getAmount(stId,year,month);
        double cash = (amount != null? Double.parseDouble(amount.toString()):0);
        System.out.println(amount);

        amount = chequeRepo.getAmount(stId,year,month);
        double cheque = (amount != null? Double.parseDouble(amount.toString()):0);
        System.out.println(amount);

        amount = creditCardRepo.getAmount(stId,year,month);
        double cc = (amount != null? Double.parseDouble(amount.toString()):0);

        amount = fromSalRepo.getAmount(stId,year,month);
        double fromSal = (amount !=null? Double.parseDouble(amount.toString()):0);

        amount = moneyOrderRepo.getAmount(stId,year,month);
        double mo = (amount !=null? Double.parseDouble(amount.toString()):0);

        amount = zelleReo.getAmount(stId,year,month);
        double zelle = ( amount !=null? Double.parseDouble(amount.toString()):0);

        totalAmount = cash+cheque+cc+fromSal+mo+zelle;

        return totalAmount;
    }

}
