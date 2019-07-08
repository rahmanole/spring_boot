package com.minhaz.myapp.controller;

import com.minhaz.myapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    PostService postService;
    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("postList",postService.findAllAndOrderByDateTimeDesc());
        model.addAttribute("func",postService);
        return "index";
    }
}
