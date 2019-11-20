package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookImageDao extends JpaRepository<BookImage,Long> {
    String QUERY_FOR_AUTHOR = "Select max(id) from book_images";

    @Query(nativeQuery = true,value = QUERY_FOR_AUTHOR)
    long getMaxId();
}
