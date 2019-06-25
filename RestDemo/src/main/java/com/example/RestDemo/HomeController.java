package com.example.RestDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private  StudentRepository repository;

    @GetMapping("/")
    public String home(Model model){
        return "index";
    }

    @GetMapping("/students")
    public String studentList(Model model){
        List<Student> list = this.repository.findAll();
        model.addAttribute("list",list);
        return "list";
    }

    @GetMapping("/students/{id}")
    public String studentList(Model model,@PathVariable("id") Long id){
        Student student = this.repository.getOne(id);
        model.addAttribute("student",student);
        return "student";
    }

    @PostMapping("/registration")
    public String regForm(Model model){
        model.addAttribute("student",new Student());
        return "reg-form";
    }

    @PostMapping("/save")
    public String saveStudent(Model model,Student student){

       // model.addAttribute("student",new Student());
        this.repository.save(student);
        return "redirect:/students";
    }
}
