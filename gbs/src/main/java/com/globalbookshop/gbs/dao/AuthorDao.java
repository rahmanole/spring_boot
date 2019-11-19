package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.Author;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorDao extends JpaRepository<Author,Long>, JpaSpecificationExecutor<Author> {
    Author findAuthorByAuthorName(String authorName);
    String QUERY_FOR_AUTHOR = "Select author_name from authors";

    @Query(nativeQuery = true,value = QUERY_FOR_AUTHOR)
    List<String> authorNames();


}
