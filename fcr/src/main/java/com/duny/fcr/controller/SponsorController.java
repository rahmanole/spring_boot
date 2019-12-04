package com.duny.fcr.controller;

import com.duny.fcr.dto.SpAssignDTO;
import com.duny.fcr.entity.Sponsor;
import com.duny.fcr.entity.Student;
import com.duny.fcr.repo.SponsorRepo;
import com.duny.fcr.repo.StudentRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SponsorController {
    @Autowired
    SponsorRepo sponsorRepo;
    @Autowired
    StudentRepo studentRepo;


    @PostMapping(value = "/fee/spAssing")
    public String spAssign(@RequestBody String spAssingDTO) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        SpAssignDTO dto = gson.fromJson(spAssingDTO, SpAssignDTO.class);
        sponsorRepo.inserSpID(dto.getSp_id(), dto.getFin_dtl_id());
        sponsorRepo.inserStID(dto.getSt_id(), dto.getSp_id());
        return "redirect:/pages/report/1";
    }


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

    @GetMapping("/sponsor/student/{st_id}")
    @ResponseBody
    public Sponsor getSponsorOfStudent(@PathVariable int st_id){
       return sponsorRepo.getSponsorOfStudent(st_id);
    }



}
