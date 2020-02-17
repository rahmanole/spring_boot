package com.duny.fcr.serviceImp;

import com.duny.fcr.repo.TeacherRepo;
import com.duny.fcr.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

public class TeacherServiceImp implements TeacherService {

    @Autowired
    TeacherRepo teacherRepo;

    @Override
    public boolean isTeacherAssigned(String className) {
        return teacherRepo.isTeacherAssigned(className) != null;
    }
}
