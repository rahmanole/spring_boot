package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseDao extends JpaRepository<Course,Long> {

    String QUERY_FOR_COURSE_NAMES = "Select course_name from courses";

    Course findCourseByCourseName(String courseName);

    @Query(nativeQuery = true,value = QUERY_FOR_COURSE_NAMES)
    List<String> courseNames();

}
