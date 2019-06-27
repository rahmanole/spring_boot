package com.minhaz.boot_crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private  StudentRepository repository;

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping( value = "/students")
    public String studentList(Model model){
        List<Student> list = this.repository.findAll();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping(value ="registration")
    public String regForm(Model model){
        model.addAttribute("student",new Student());
        return "reg-form";
    }

    @RequestMapping(value = "/save" , method = RequestMethod.POST)
    public String saveStudent(Model model,Student student){

       // model.addAttribute("student",new Student());
        this.repository.save(student);
        return "redirect:/students";
    }

    @RequestMapping("/update/{id}")
    public String getUpdateForm(Model model,@PathVariable Long id){
        Student student = this.repository.getOne(id);
        model.addAttribute("student",student);
        return "update-form";
    }

    @RequestMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        this.repository.deleteById(id);
        return "redirect:/students";
    }
}
