package com.example.thymeleaf.controller;

import com.example.thymeleaf.dao.RoleRepository;
import com.example.thymeleaf.dao.UserRepository;
import com.example.thymeleaf.entity.Role;
import com.example.thymeleaf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping("/role")
    public String role(Model model){
        model.addAttribute("role",new Role());
        return "role";
    }

    @RequestMapping("/role/save")
    public String roleSave(Model model, Role role){
        if(roleRepository.findByRoleName(role.getRoleName()) == null){
            model.addAttribute("role",role);
            roleRepository.save(role);
        }
        return "role";
    }

    @GetMapping("/user-reg")
    public String user(Model model, User user){
            model.addAttribute("user",user);
            model.addAttribute("roleList",roleRepository.findAll());
        return "userReg";
    }

}
