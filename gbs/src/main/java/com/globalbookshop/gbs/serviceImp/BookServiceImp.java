package com.globalbookshop.gbs.serviceImp;

import com.globalbookshop.gbs.dao.AuthorDao;
import com.globalbookshop.gbs.dao.CourseDao;
import com.globalbookshop.gbs.dao.DepartmentDao;
import com.globalbookshop.gbs.dao.PublisherDao;
import com.globalbookshop.gbs.entity.*;
import com.globalbookshop.gbs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    AuthorDao authorDao;
    @Autowired
    PublisherDao publisherDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    CourseDao courseDao;

 @Override
    public void saveBook(Book book) {

    }


}
