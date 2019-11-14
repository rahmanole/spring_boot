package com.globalbookshop.gbs.controller;

import com.globalbookshop.gbs.entity.Book;
import com.globalbookshop.gbs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
public class DashboardController {

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

}
