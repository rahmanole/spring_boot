package com.duny.fcr.repo;

import com.duny.fcr.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {
    String GET_COURSE_NAMES_BY_GENDER = "select course_name from course where student_type=? or student_type='All'";

    @Query(nativeQuery = true,value = GET_COURSE_NAMES_BY_GENDER)
    List<String> getCourseNames(String studentType);
}
