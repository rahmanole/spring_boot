package com.duny.fcr.controller;

import com.duny.fcr.entity.Sponsor;
import com.duny.fcr.entity.Student;
import com.duny.fcr.repo.SponsorRepo;
import com.duny.fcr.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SponsorController {
    @Autowired
    SponsorRepo sponsorRepo;
    @Autowired
    StudentRepo studentRepo;


    @GetMapping("sponsor/add")
    public String add(Model model){
        model.addAttribute("sponsor", new Sponsor());
        return "/pages/sponsor/addSponsor";
    }

    @PostMapping("sponsor/save")
    public String save(Sponsor sponsor){
        sponsorRepo.save(sponsor);
        return "/pages/sponsor/addSponsor";
    }

    @GetMapping("sponsor/list")
    @ResponseBody
    public List<String> getSponsorList(){
       return sponsorRepo.getSponsorNames();
    }

    @GetMapping("/sponsor/{name}")
    @ResponseBody
    public Sponsor getSponsorByname(@PathVariable String name){
       return sponsorRepo.findSponsorByName(name);
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Sponsor getStudent(@PathVariable Long id){
       return sponsorRepo.getOne(id);
    }

}
