package com.globalbookshop.gbs.controller;

import com.globalbookshop.gbs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class DashboardController {

    @Autowired
    FileUploadService fileUploadService;

    @GetMapping("dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("addBook")
    public String bookUploadForm() {
        return "bookUploadForm";
    }

    @GetMapping("uploadExcel")
    public String uploadExcel() {
        return "uploadExcel";
    }

    @PostMapping("/upload")
    public RedirectView uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a files to upload");
            System.out.println("=============== not saved ============");
            return new RedirectView("uploadExcel");
        }

        boolean flag = fileUploadService.uploadFile(file, redirectAttributes);
        if (flag) {
            redirectAttributes.addFlashAttribute("message",
                    "You've successfully uploaded '" + file.getOriginalFilename() + "'");
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "File not upload'" + file.getOriginalFilename() + "'");
        }
        return new RedirectView("uploadExcel");
    }

    @GetMapping("sliders")
    public String sliders() {
        return "sliders";
    }

}
