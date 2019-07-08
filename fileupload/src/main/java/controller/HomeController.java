package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repo.UserRepo;

@Controller
public class HomeController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("list",userRepo.findAll());
        return "index";
    }


}
