package com.security.bootsecurity.controller;

import com.security.bootsecurity.dao.RoleRepository;
import com.security.bootsecurity.dao.UserRepository;
import com.security.bootsecurity.entity.Privilege;
import com.security.bootsecurity.entity.Role;
import com.security.bootsecurity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/save")
    public String save(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByRoleName("USER").get());
        user.setRoles(roles);
        userRepository.save(user);
        return "/signUp";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    @GetMapping("/login")
    public String logIn() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashBoard() {
        return "dashboard";
    }


}
