package com.duny.fcr.controller;

import com.duny.fcr.entity.Teacher;
import com.duny.fcr.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TeacherController {
    @Autowired
    TeacherRepo teacherRepo;

    @GetMapping(value = "/teacher/add")
    public String addTeacher(Model model){
        model.addAttribute("teacher",new Teacher());
        return "/pages/forms/addTeacher";
    }

    @PostMapping(value = "/teacher/save")
    public String saveTeacher(Teacher teacher){
        teacherRepo.save(teacher);
        return "redirect:/teacher/add";
    }

    @GetMapping(value = "/teacher/update/{id}")
    public String updateTeacher(Model model,@PathVariable Long id){
        Teacher teacher = teacherRepo.getOne(id);
        model.addAttribute("teacher",teacher);
        return "/pages/forms/updateTeacher";
    }

    @GetMapping(value = "/teacher/all")
    public String teacherList(Model model){
        model.addAttribute("teachers",teacherRepo.findAll());
        return "/pages/tables/allTeachers";
    }


    @GetMapping(value = "/teacher/delete/{id}")
    public String updateTeacher(@PathVariable Long id){
        Teacher teacher = teacherRepo.getOne(id);
        teacherRepo.delete(teacher);
        return "redirect:/teacher/add";
    }

    @GetMapping(value = "/teacher/getTeacherByCourse/{courseName}")
    @ResponseBody
    public Teacher getTeacherByCourse(@PathVariable String courseName){
        System.out.println(courseName);
        return teacherRepo.findTeacherByClassName(courseName);
    }






}
