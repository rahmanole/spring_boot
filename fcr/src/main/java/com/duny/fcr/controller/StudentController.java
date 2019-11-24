package com.duny.fcr.controller;

import com.duny.fcr.entity.Student;
import com.duny.fcr.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class StudentController {
    @Autowired
    StudentRepo studentRepo;

    @GetMapping("student/registration")
    public String registrationForm(Model model){
        model.addAttribute("student",new Student());
        return "pages/forms/sif";
    }


    @PostMapping("student/save")
    public String save(Student student, @RequestParam("d") String d) throws Exception{
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
        Date date = sf.parse(d);
        System.out.println(date);
        student.setDob(sf.parse(d));
        student.setStatus("applied");
        student.setFeePolicy("nd");
        int student_id = studentRepo.getStudentId();
        student.setStudentId(++student_id);
        studentRepo.save(student);
        return "redirect:/student/registration";
    }

    @GetMapping("student/pending")
    public String pendingApplications(Model model){
        return "pages/tables/pending";
    }

}
