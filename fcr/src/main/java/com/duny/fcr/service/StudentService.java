package com.duny.fcr.service;

import com.duny.fcr.repo.StudentRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface StudentService {
    void uploadExcel(MultipartFile file,StudentRepo studentRepo);
}
