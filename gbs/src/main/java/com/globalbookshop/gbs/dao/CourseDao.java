package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<Course,Long> {

}
