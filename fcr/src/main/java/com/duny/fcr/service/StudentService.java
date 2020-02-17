package com.duny.fcr.service;

import com.duny.fcr.repo.StudentRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface StudentService {
    void uploadExcel(MultipartFile file,StudentRepo studentRepo);
    boolean isStudentExists(String name,String fName,String mName);
}
