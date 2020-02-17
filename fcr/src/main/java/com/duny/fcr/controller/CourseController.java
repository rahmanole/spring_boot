package com.duny.fcr.controller;

import com.duny.fcr.entity.Course;
import com.duny.fcr.entity.Teacher;
import com.duny.fcr.repo.CourseRepo;
import com.duny.fcr.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    CourseRepo courseRepo;

    @GetMapping(value = "/course/add")
    public String addCourse(Model model){
        model.addAttribute("course",new Course());
        return "/pages/forms/addCourses";
    }

    @PostMapping(value = "/course/save")
    public String saveCourse(Course course){
        courseRepo.save(course);
        return "redirect:/course/add";
    }

    @GetMapping(value = "/course/{studentType}")
    @ResponseBody
    public List<String> saveTeacher(@PathVariable String studentType){
        return courseRepo.getCourseNames(studentType);
    }


}
