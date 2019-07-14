package com.minhaz.myapp.dao;


import com.minhaz.myapp.entity.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    PageRequest pageable = PageRequest.of(0, 15);

    List<Post> findByCat(String cat);
    List<Post> findAllByOrderByDateTimeDesc();
//    List<String> findPub();
    @Query(value = "select publisher_given_id from post p")
    List<String> publisherGivenId();

}
