package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CourseDao extends JpaRepository<Course,Long> {
    Course findCourseByCourseName(String courseName);
}
