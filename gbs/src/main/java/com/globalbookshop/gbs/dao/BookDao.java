package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book,Long> {

}
