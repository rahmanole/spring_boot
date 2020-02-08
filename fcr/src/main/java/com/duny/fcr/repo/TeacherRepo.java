package com.duny.fcr.repo;

import com.duny.fcr.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepo  extends JpaRepository<Teacher,Long> {


    Teacher findTeacherByClassName(String courseName);
}
