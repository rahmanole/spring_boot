package com.globalbookshop.gbs.serviceImp;

import com.globalbookshop.gbs.dao.CourseDao;
import com.globalbookshop.gbs.entity.Book;
import com.globalbookshop.gbs.entity.Course;
import com.globalbookshop.gbs.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class CourseServiceImp implements CourseService {
    @Autowired
    CourseDao courseDao;
    @Override
    public List<Book> getBooksByCourse(String courseName) {
        return courseDao.findCourseByCourseName(courseName).getBooks();
    }

    @Override
    public List<String> courseNames() {
        List<String> authorNames = courseDao.courseNames();
        Collections.sort(authorNames);
        return authorNames;
    }


    @Override
    public List<Course> getCourseList(List<String> coursNames) {
        List<Course> courseList = new ArrayList<>();
        coursNames.forEach(coursName ->{
            courseList.add(courseDao.findCourseByCourseName(coursName));
        });
        return courseList;
    }
}
