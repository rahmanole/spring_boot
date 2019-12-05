package com.duny.fcr.controller;

import com.duny.fcr.HibernateProxyTypeAdapter;
import com.duny.fcr.entity.FinDtlsOfStudent;
import com.duny.fcr.entity.Student;
import com.duny.fcr.repo.StudentRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentRepo studentRepo;

    @GetMapping("/student/registration")
    public String registrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "/pages/forms/sif";
    }

    @PostMapping("/student/save")
    public String save(Student student, @RequestParam("d") String d) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
        Date date = sf.parse(d);
        System.out.println(date);
        student.setDob(sf.parse(d));
        student.setStatus("applied");
        int application_id = studentRepo.getMaxApplicationId() + 1;
        student.setApplicationId(application_id);

        int student_id = Integer.parseInt(studentRepo.getMaxStudentId()) + 1;
        student.setStudentId(student_id + "");


        student.setFinDtlsOfStudent(new FinDtlsOfStudent());
    //        student.setStudentId("111");
//        student.setApplicationId(1000);
        studentRepo.save(student);
        return "redirect:/student/registration";
    }

    @GetMapping("/student/pending")
    public String pendingApplications(Model model) {
        model.addAttribute("appliesStList", studentRepo.findAll());
        return "/pages/tables/pending";
    }

    @GetMapping("/student/details/{id}")
    @ResponseBody
    public Student getStudentById(@PathVariable long id) {
        return studentRepo.getOne(id);
    }

    @GetMapping("/student/json/{studentId}")
    @ResponseBody
    public String getStudentByIdForJson(@PathVariable String studentId) {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.create();
        return gson.toJson(studentRepo.getStudentByStudentId(studentId));
    }


}
