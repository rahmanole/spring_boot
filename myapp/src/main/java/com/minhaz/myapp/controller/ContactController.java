package com.minhaz.myapp.controller;

import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.entity.Email;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.service.ContactService;
import com.minhaz.myapp.service.PostService;
import com.minhaz.myapp.util.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ContactController {
    @Autowired
    ContactService contactService;

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("mail",new Email());
        return "contact";
    }

    @PostMapping("/sendMessage")
    public String sendMail(Email mail){
        contactService.sendMail(mail);
        return "redirect:/contact";
    }
}
