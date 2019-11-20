package com.globalbookshop.gbs.service;

import com.globalbookshop.gbs.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    void saveBook(Book book);
    Book getBook(long id);
    Page<Book> getAllBooks();

}
