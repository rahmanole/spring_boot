package com.globalbookshop.gbs.controller;

import com.globalbookshop.gbs.service.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class HomeController {

    @Autowired
    FileUpload fileUpload;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("/upload")
    public RedirectView uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            System.out.println("=============== not saved ============");
            return new RedirectView("/");
        }

        boolean flag = fileUpload.uploadFile(file,redirectAttributes);
        if(flag){
            redirectAttributes.addFlashAttribute("message",
                    "You've successfully uploaded '" + file.getOriginalFilename()+"'");
        }else{
            redirectAttributes.addFlashAttribute("message",
                    "File not upload'" + file.getOriginalFilename()+"'");
        }

        return new RedirectView("/");
    }
}
