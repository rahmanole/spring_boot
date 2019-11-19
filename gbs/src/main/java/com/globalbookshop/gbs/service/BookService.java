package com.globalbookshop.gbs.service;

import com.globalbookshop.gbs.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {

    void saveBook(Book book);

}
