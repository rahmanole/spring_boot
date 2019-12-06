package com.duny.fcr.controller;

import com.duny.fcr.dto.SpAssignDTO;
import com.duny.fcr.entity.Dadd;
import com.duny.fcr.repo.DaddRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DaddController {
    @Autowired
    DaddRepo daddRepo;

    @GetMapping("/dadd/add")
    public String daddForm(Model model) {
        model.addAttribute("dadd", new Dadd());
        return "/pages/sponsor/daddForm";
    }

    @PostMapping("/dadd/save")
    public String daddSave(Dadd dadd) {
        daddRepo.save(dadd);
        return "/pages/sponsor/daddForm";
    }

    @GetMapping("/dadd/list")
    @ResponseBody
    public List<String> getDaddNames() {
        return daddRepo.getDaddNames();
    }

    @GetMapping("/dadd/{name}")
    @ResponseBody
    public Dadd getSponsorByname(@PathVariable String name) {
        return daddRepo.findDaddByName(name);
    }

    //This controller to remove dadd

    @GetMapping("/dadd/remove/{fin_id}/{dadd_id}/{st_id}")
    public String removeDadd(@PathVariable("fin_id") int fin_id,
                             @PathVariable("dadd_id") int dadd_id,
                             @PathVariable("st_id") int st_id) {
        System.out.println("at dadd remove url" + daddRepo.getTotalDaddsOfSt(st_id));
        if (daddRepo.getTotalDaddsOfSt(st_id) == 1) {
            daddRepo.removeDaddFromFinDtl(fin_id);
        }
        daddRepo.removeStudentFromDadd(dadd_id);
        return "redirect:/fee/report/1";
    }


    // This controller to add dadd

    @PostMapping(value = "/fee/daddAssign")
    public String daddAssign(@RequestBody String spAssingDTO) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();

        SpAssignDTO dto = gson.fromJson(spAssingDTO, SpAssignDTO.class);

        daddRepo.inserDaddIDInFin(dto.getFin_dtl_id());
        daddRepo.inserStIDInDadd(dto.getSt_id(), dto.getDadd_id());
        return "redirect:/pages/report/1";
    }

    // This controller retrieves all dadds for  student

    @GetMapping(value = "/dadd/all/{st_id}")
    @ResponseBody
    public List<Dadd> daddAssign(@PathVariable int st_id) {
        return daddRepo.findAllBySt_id(st_id);
    }

    @GetMapping(value = "/dadd/byId/{id}")
    @ResponseBody
    public Dadd getDaddById(@PathVariable("id") long id) {
        return daddRepo.getOne(id);
    }

}
