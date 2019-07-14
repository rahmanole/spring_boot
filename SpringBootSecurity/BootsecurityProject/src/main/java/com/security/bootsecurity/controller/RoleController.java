package com.security.bootsecurity.controller;

import com.security.bootsecurity.dao.PriviligeRepository;
import com.security.bootsecurity.dao.RoleRepository;
import com.security.bootsecurity.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PriviligeRepository priviligeRepository;

    @GetMapping("/add")
    public String dashBoard(Model model) {
        model.addAttribute("role",new Role());
        model.addAttribute("priv_list",priviligeRepository.findAll());
        return "role";
    }

    @PostMapping("/save")
    public String savePriv(Role role) {
        roleRepository.save(role);
        return "redirect:/role/add/";
    }

    @GetMapping("/update/{id}")
    public String update(Model model,@PathVariable Long id) {
        model.addAttribute("role",roleRepository.findById(id));
        return "roleUpdate";
    }

    @GetMapping("/roles")
    public String roles(Model model) {
        model.addAttribute("allRoles",roleRepository.findAll());
        return "roleList";
    }


}
