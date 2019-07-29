package com.dawntechbd.idb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RoleController {
    @Autowired
    private RoleRepo repo;

    @GetMapping(value = "/role")
    public String displayRole(Model model){
        model.addAttribute("role", new Role());

        return "role";
    }

    @PostMapping(value = "/role")
    public String displayRole(@Valid Role role, BindingResult bindingResult, Model model){
        this.repo.save(role);
        model.addAttribute("sucMsg", "Success !");

        return "role";
    }
}
