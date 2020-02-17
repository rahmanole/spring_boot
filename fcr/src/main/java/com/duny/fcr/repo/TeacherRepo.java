package com.duny.fcr.repo;

import com.duny.fcr.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepo  extends JpaRepository<Teacher,Long> {
    String CHECK_DUPLICATE_ASSIGN = "select *  from teacher where class_name=? and  teacher_name is not null";


    Teacher findTeacherByClassName(String courseName);

    @Query(value = CHECK_DUPLICATE_ASSIGN,nativeQuery = true)
    Teacher isTeacherAssigned(String className);
}
