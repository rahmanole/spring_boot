package com.duny.fcr.controller;

import com.duny.fcr.entity.FinDtlsOfStudent;
import com.duny.fcr.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class FeeController {


    @Autowired
    SponsorRepo sponsorRepo;
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    FeeRepo feeRepo;
    @Autowired
    DaddRepo daddRepo;

    @Autowired
    FinDtlsOfStudentRepo finDtlsOfStudentRepo;

    @GetMapping("/updateFinancialDetails")
    public String generateFee(Model model) {
        model.addAttribute("studentIDS", studentRepo.getStudentIds());
        return "/pages/financial/updateFinDetails";
    }

    @GetMapping("/monthlyFee")
    public String collectMontlyFee(Model model) {
        model.addAttribute("studentIDS", studentRepo.getStudentIds());
        return "/pages/financial/monthlyFee";
    }



    @GetMapping("/findetails/{id}")
    @ResponseBody
    public FinDtlsOfStudent getFindetails(@PathVariable long id) {
        return finDtlsOfStudentRepo.getOne(id);
    }


    @GetMapping("/sponsor/remover/{fin_id}/{sp_id}")
    public String removeSponsorFromFinDtls(@PathVariable("fin_id") long id,
                                         @PathVariable("sp_id") int sp_id) {
        finDtlsOfStudentRepo.removeSponsor(id);
        sponsorRepo.removeStudentFromSponsor(sp_id);
        return "redirect:/pages/report/1";
    }


    @GetMapping("/findetails/{collection}/{fin_id}")
    @ResponseBody
    public String insertCollection(@PathVariable double collection,@PathVariable int fin_id) {
        finDtlsOfStudentRepo.insertColletion(collection,fin_id);
        return "";
    }


    @GetMapping("/findetails/zakat/{zakat}/{fin_id}")
    @ResponseBody
    public String insertZakat(@PathVariable double zakat,@PathVariable int fin_id) {
        finDtlsOfStudentRepo.insertZakat(zakat,fin_id);
        return "";
    }

    @GetMapping("/findetails/addStaff/{fin_id}")
    @ResponseBody
    public String insertStaff(@PathVariable int fin_id) {
        finDtlsOfStudentRepo.insertStaff(fin_id);
        return "";
    }

    @GetMapping("/findetails/removeStaff/{fin_id}")
    @ResponseBody
    public String removeStaff(@PathVariable int fin_id) {
        finDtlsOfStudentRepo.removeStaff(fin_id);
        return "";
    }


    @GetMapping("/findetails/selfFund/{fin_id}")
    @ResponseBody
    public String insertSelfFunded(@PathVariable int fin_id) {
        finDtlsOfStudentRepo.insertSelfFunded(fin_id);
        return "";
    }

    @GetMapping("/findetails/removeSelfFund/{fin_id}")
    @ResponseBody
    public String removeSelfFund(@PathVariable int fin_id) {
        finDtlsOfStudentRepo.removeSelfFunded(fin_id);
        return "";
    }

    @GetMapping("/findetails/insertSibling/{siblingIds}/{siblingNum}/{fin_id}")
    @ResponseBody
    public String inseertSibling(@PathVariable String siblingIds,@PathVariable int siblingNum,@PathVariable int fin_id) {
        finDtlsOfStudentRepo.insertSibling(siblingIds,siblingNum,fin_id);
        return "";
    }

    @GetMapping("/findetails/mandFees/{mandFees}/{fin_id}")
    @ResponseBody
    public String inseertMandFees(@PathVariable double mandFees,@PathVariable int fin_id) {
        finDtlsOfStudentRepo.insertMandFees(mandFees,fin_id);
        return "";
    }

    @GetMapping("/findetails/mandFeesDue/{mandFeesDue}/{fin_id}")
    @ResponseBody
    public String inseertMandFeesDue(@PathVariable double mandFeesDue,@PathVariable int fin_id) {
        finDtlsOfStudentRepo.insertMandFeesDue(mandFeesDue,fin_id);
        return "";
    }


}
