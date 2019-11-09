package com.globalbookshop.gbs.service;


import com.globalbookshop.gbs.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublisherService {
    List<Book> getBooksByTitle(String title);
}
