package com.globalbookshop.gbs.serviceImp;

import com.globalbookshop.gbs.dao.*;
import com.globalbookshop.gbs.entity.*;
import com.globalbookshop.gbs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Autowired
    BookDao bookDao;

    @Override
    public void saveBook(Book book) {
        bookDao.save(book);
    }

    public Book getBook(long id){
        return bookDao.getOne(id);
    }

    @Override
    public Page<Book> getAllBooks() {
        return bookDao.findAll(PageRequest.of(0,20));
    }


}
