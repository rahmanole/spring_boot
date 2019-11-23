package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDao extends JpaRepository<Book,Long> {
    String QUERY_FOR_ISBN = "Select isbn from books";
    String QUERY_FOR_TITLES = "Select title from books";

    @Query(nativeQuery = true,value = QUERY_FOR_ISBN)
    List<String> isbns();

    @Query(nativeQuery = true,value = QUERY_FOR_TITLES)
    List<String> getTitles();

    Book findBookByIsbn(String isbn);
}
