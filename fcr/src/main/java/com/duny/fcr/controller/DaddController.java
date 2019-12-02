package com.duny.fcr.controller;

import com.duny.fcr.entity.Dadd;
import com.duny.fcr.entity.Sponsor;
import com.duny.fcr.repo.DaddRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DaddController {
    @Autowired
    DaddRepo daddRepo;
    @GetMapping("/dadd/add")
    public String daddForm(Model model){
        model.addAttribute("dadd",new Dadd());
        return "/pages/sponsor/daddForm";
    }

    @PostMapping("/dadd/save")
    public String daddSave(Dadd dadd){
        daddRepo.save(dadd);
        return "/pages/sponsor/daddForm";
    }

    @GetMapping("/dadd/list")
    @ResponseBody
    public List<String> getDaddNames(){
        return daddRepo.getDaddNames();
    }

    @GetMapping("/dadd/{name}")
    @ResponseBody
    public Dadd getSponsorByname(@PathVariable String name){
        return daddRepo.findDaddByName(name);
    }
}
