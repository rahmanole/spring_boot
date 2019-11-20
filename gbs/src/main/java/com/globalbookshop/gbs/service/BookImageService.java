package com.globalbookshop.gbs.service;

import com.globalbookshop.gbs.entity.BookImage;
import org.springframework.stereotype.Service;

@Service
public interface BookImageService {

    void save(BookImage bookImage);
}
