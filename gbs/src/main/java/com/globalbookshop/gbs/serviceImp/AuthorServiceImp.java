package com.globalbookshop.gbs.serviceImp;

import com.globalbookshop.gbs.dao.AuthorDao;
import com.globalbookshop.gbs.entity.Author;
import com.globalbookshop.gbs.entity.Book;
import com.globalbookshop.gbs.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImp implements AuthorService {
    @Autowired
    AuthorDao authorDao;

    @Override
    public List<Book> getBookByAuthor(String authorName) {
        return authorDao.findAuthorByAuthorName(authorName).getBooks();
    }
}
