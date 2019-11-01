package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<Author,Long> {
    Author findAuthorByAuthorName(String authorName);
}
