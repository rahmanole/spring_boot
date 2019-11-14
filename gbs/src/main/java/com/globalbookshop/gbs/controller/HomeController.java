package com.globalbookshop.gbs.controller;

import com.globalbookshop.gbs.dao.AuthorDao;
import com.globalbookshop.gbs.dao.CourseDao;
import com.globalbookshop.gbs.dao.DepartmentDao;
import com.globalbookshop.gbs.dao.PublisherDao;
import com.globalbookshop.gbs.entity.Book;
import com.globalbookshop.gbs.entity.Department;
import com.globalbookshop.gbs.entity.Publisher;
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
public class HomeController {

    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    PublisherService publisherService;
    @Autowired
    AuthorService authorService;
    @Autowired
    CourseService courseService;
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/upload")
    public RedirectView uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            System.out.println("=============== not saved ============");
            return new RedirectView("/");
        }

        boolean flag = fileUploadService.uploadFile(file, redirectAttributes);
        if (flag) {
            redirectAttributes.addFlashAttribute("message",
                    "You've successfully uploaded '" + file.getOriginalFilename() + "'");
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "File not upload'" + file.getOriginalFilename() + "'");
        }
        return new RedirectView("/");
    }

    @ResponseBody
    @GetMapping("publisher/{publisherName}")
    public List<Book> getBooksByPublisher(@PathVariable String publisherName) {
        return publisherService.getBooksByTitle(publisherName);
    }

    @ResponseBody
    @GetMapping("author/{authorName}")
    public List<Book> getBooksByAuthor(@PathVariable String authorName) {
        return authorService.getBookByAuthor(authorName);
    }

    @ResponseBody
    @GetMapping("course/{courseName}")
    public List<Book> getBooksByCourse(@PathVariable String courseName) {
        return courseService.getBooksByCourse(courseName);
    }

    @ResponseBody
    @GetMapping("dept/{deptName}")
    public List<Book> getBooksByDept(@PathVariable String deptName) {
        return departmentService.getBooksByDept(deptName);
    }

    @ResponseBody
    @GetMapping("test")
    public List<Book> test() {
        System.out.println("Done");
        return departmentService.getBooksByDept("Physics");
    }
}
