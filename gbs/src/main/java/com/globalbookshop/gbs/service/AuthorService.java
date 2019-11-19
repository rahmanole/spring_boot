package com.globalbookshop.gbs.service;
import com.globalbookshop.gbs.entity.Author;
import com.globalbookshop.gbs.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    List<Book> getBookByAuthor(String authorName);
    List<String> authorNames();
    List<Author> getAuthorList(List<String> authorNames);
}
