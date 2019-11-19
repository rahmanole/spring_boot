package com.globalbookshop.gbs.controller;

import com.globalbookshop.gbs.entity.Book;
import com.globalbookshop.gbs.entity.User;
import com.globalbookshop.gbs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    SliderImageService sliderImageService;


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("sliderList",sliderImageService.getAllSliders());
        return "index";
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
        return departmentService.getBooksByDept("Physics");
    }

    @GetMapping("signUp")
    public String userReg(Model model, User user) {
        user = new User();
        model.addAttribute(user);

        return "userReg";
    }
}
