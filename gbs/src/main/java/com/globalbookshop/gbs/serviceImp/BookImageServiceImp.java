package com.globalbookshop.gbs.serviceImp;

import com.globalbookshop.gbs.dao.BookImageDao;
import com.globalbookshop.gbs.entity.Book;
import com.globalbookshop.gbs.entity.BookImage;
import com.globalbookshop.gbs.service.BookImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class BookImageServiceImp implements BookImageService {
    @Autowired
    BookImageDao bookImageDao;

    @Override
    public void save(BookImage bookImage) {
      bookImageDao.save(bookImage);
    }


}
