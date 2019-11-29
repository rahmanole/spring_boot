package com.duny.fcr.controller;

import com.duny.fcr.entity.Sponsor;
import com.duny.fcr.repo.SponsorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeeController {
    @Autowired
    SponsorRepo sponsorRepo;

    @GetMapping("fee/report")
    public String generateFee(Model model){
        model.addAttribute("sponsor",new Sponsor());
        return "pages/feeReport";
    }
}
