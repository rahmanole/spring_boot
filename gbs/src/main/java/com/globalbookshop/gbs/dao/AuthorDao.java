package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<Author,Long>, JpaSpecificationExecutor<Author> {
    Author findAuthorByAuthorName(String authorName);
    public final String QUERY_FOR_AUTHOR = "Select * from authors";


}
