package com.duny.fcr.controller;

import com.duny.fcr.entity.Cash;
import com.duny.fcr.entity.Zelle;
import com.duny.fcr.repo.ZelleReo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ZelleController {
    @Autowired
    ZelleReo zelleReo;

    @PostMapping(value = "/zelle/save", consumes = "application/json", produces = "application/json")
    public String saveZelle(@RequestBody String zelleJson){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        Zelle zelle = gson.fromJson(zelleJson,Zelle.class);
        zelleReo.save(zelle);
        return "redirect:/admissionFee";
    }
}
