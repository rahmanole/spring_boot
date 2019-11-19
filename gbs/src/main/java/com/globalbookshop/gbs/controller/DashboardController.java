package com.globalbookshop.gbs.controller;

import com.globalbookshop.gbs.entity.Book;
import com.globalbookshop.gbs.entity.SliderImage;
import com.globalbookshop.gbs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class DashboardController {

    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    SliderImageService sliderImageService;
    @Autowired
    AuthorService authorService;




    @GetMapping("dashboard")
    public String dashboard() {
        return "dashboardPages/dashboard";
    }

    @GetMapping("uploadExcel")
    public String uploadExcel() {
        return "dashboardPages/uploadExcel";
    }

    @PostMapping("/upload")
    public RedirectView uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a files to upload");
            System.out.println("=============== not saved ============");
            return new RedirectView("redirect:/uploadExcel");
        }

        boolean flag = fileUploadService.uploadFile(file, redirectAttributes);
        if (flag) {
            redirectAttributes.addFlashAttribute("message",
                    "You've successfully uploaded '" + file.getOriginalFilename() + "'");
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "File not upload'" + file.getOriginalFilename() + "'");
        }
        return new RedirectView("redirect:/uploadExcel");
    }

}
