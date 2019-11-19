package com.globalbookshop.gbs.service;

import com.globalbookshop.gbs.entity.Book;
import com.globalbookshop.gbs.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CourseService {
    List<Book> getBooksByCourse(String courseName);
    List<String> courseNames();
    List<Course> getCourseList(List<String> coursNames);
}
