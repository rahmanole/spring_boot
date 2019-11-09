package com.globalbookshop.gbs.serviceImp;

import com.globalbookshop.gbs.dao.CourseDao;
import com.globalbookshop.gbs.entity.Book;
import com.globalbookshop.gbs.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImp implements CourseService {
    @Autowired
    CourseDao courseDao;
    @Override
    public List<Book> getBooksByCourse(String courseName) {
        return courseDao.findCourseByCourseName(courseName).getBooks();
    }
}
