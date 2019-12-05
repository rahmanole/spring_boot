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

    @GetMapping("/fee/report/{id}")
    public String generateFee(Model model, @PathVariable long id) {
        model.addAttribute("studentIDS", studentRepo.getStudentIds());
        return "pages/feeReport";
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
    public String insertCollection(@PathVariable long collection,@PathVariable int fin_id) {
        finDtlsOfStudentRepo.insertColletion(collection,fin_id);
        return "";
    }




}
