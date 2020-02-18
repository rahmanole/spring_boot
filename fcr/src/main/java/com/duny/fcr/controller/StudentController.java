package com.duny.fcr.controller;

import com.duny.fcr.HibernateProxyTypeAdapter;
import com.duny.fcr.LocalDateAdapdar;
import com.duny.fcr.entity.FinDtlsOfStudent;
import com.duny.fcr.entity.Student;
import com.duny.fcr.repo.StudentRepo;
import com.duny.fcr.service.StudentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class StudentController {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    StudentService studentService;

    @GetMapping("/student/registration")
    public String registrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "/pages/forms/sif";
    }

    @PostMapping("/student/save")
    public String save(Student student, @RequestParam("d") String dob) throws Exception {

        student.setStatus("applied");

        student.setDob(LocalDate.parse(dob,DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        int application_id;
        try{
            application_id = studentRepo.getMaxApplicationId() + 1;

        }catch (Exception e){
            application_id = 1000;
        }
        student.setApplicationId(application_id);

        int student_id;

        if(studentRepo.getMaxStudentId() == null){
            student_id = 101;
        }else{
            student_id = Integer.parseInt(studentRepo.getMaxStudentId()) + 1;
        }



        student.setStudentId(student_id + "");

        student.setFinDtlsOfStudent(new FinDtlsOfStudent());

//        student.setStudentId("111");
//        student.setApplicationId(1000);

        studentRepo.save(student);
        return "redirect:/student/registration";
    }

    @GetMapping("/student/all")
    public String pendingApplications(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        return "/pages/tables/student";
    }

    @GetMapping("/student/update/{id}")
    public String updateStudent(Model model,@PathVariable long id) {
        model.addAttribute("student",studentRepo.getOne(id));
        return "/pages/forms/updateForm";
    }

    @GetMapping("/student/details/{id}")
    @ResponseBody
    public Student getStudentById(@PathVariable long id) {
        return studentRepo.getOne(id);
    }

    @GetMapping("/student/admit/{id}")
    @ResponseBody
    public String admitStudent(@PathVariable long id) {
        studentRepo.admitStudent(id);
        return "";
    }

    @GetMapping("/student/json/{studentId}")
    @ResponseBody
    public String getStudentByIdForJson(@PathVariable String studentId) {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        b.registerTypeAdapter(LocalDate.class, new LocalDateAdapdar().nullSafe());
        Gson gson = b.create();
        return gson.toJson(studentRepo.getStudentByStudentId(studentId));
    }

    @GetMapping("/student/excel")
    public String studentExcel(Model model) {
        return "/pages/forms/excel";
    }

    @PostMapping("/student/excel/upload")
    public String studentExcelUpload(@RequestParam("excel") MultipartFile file){
        studentService.uploadExcel(file,studentRepo);
        return "redirect:/student/excel";
    }

    @GetMapping("/student/updateCourse/{course}/{id}")
    public String updateCourse(@PathVariable String course,@PathVariable long id){
        studentRepo.updateCourse(course,id);
        return "redirect:/updateFinancialDetails";
    }

    @GetMapping("/student/updateBoarding/{boarding}/{id}")
    public String updateBoarding(@PathVariable String boarding,@PathVariable long id){
        studentRepo.updateBoarding(boarding,id);
        return "redirect:/updateFinancialDetails";
    }

    @GetMapping("/student/updateYear/{year}/{id}")
    public String updateYear(@PathVariable String year,@PathVariable long id){
        studentRepo.updateYear(year,id);
        return "redirect:/updateFinancialDetails";
    }

    @GetMapping("/student/studentDetails/{id}")
    public String studentDetails(Model model,@PathVariable long id) {
        model.addAttribute("student",studentRepo.getOne(id));
        return "/pages/forms/student_details";
    }

    @GetMapping("/student/isExists/{name}/{fName}/{mName}")
    @ResponseBody
    public boolean isStudentExists(@PathVariable String name,@PathVariable String fName,@PathVariable String mName) {
        return studentService.isStudentExists(name,fName,mName);
    }



}
