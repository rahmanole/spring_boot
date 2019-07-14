package com.security.bootsecurity.controller;

import com.security.bootsecurity.dao.PriviligeRepository;
import com.security.bootsecurity.entity.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/privilege")
public class PrivilegeController {
    @Autowired
    PriviligeRepository priviligeRepository;

    @GetMapping("/add")
    public String dashBoard(Model model) {
        model.addAttribute("privilege",new Privilege());
        return "privilege";
    }

    @PostMapping("/save")
    public String savePriv(Privilege privilege) {
        priviligeRepository.save(privilege);
        return "privilege";
    }


}
