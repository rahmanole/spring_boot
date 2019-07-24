package com.minhaz.myapp.dao;


import com.minhaz.myapp.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    PageRequest pageable = PageRequest.of(0, 15);

    Page<Post> findByCatOrderByDateTimeDesc(String cat);
    List<Post> findAllByOrderByDateTimeDesc();
//    List<String> findPub();
    @Query(value = "select p.publisherGivenId from Post p where p.publisher = :publisher")
    HashSet<String> getPublisherGivenId(@Param("publisher") String publisher);

}
