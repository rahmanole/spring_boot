package com.globalbookshop.gbs.serviceImp;

import com.globalbookshop.gbs.dao.PublisherDao;
import com.globalbookshop.gbs.entity.Book;
import com.globalbookshop.gbs.entity.Publisher;
import com.globalbookshop.gbs.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImp implements PublisherService {
    @Autowired
    PublisherDao publisherDao;

    @Override
    public List<Book> getBooksByTitle(String title){
        return publisherDao.findPublisherByPublisher(title).getBooks();
    }
}
