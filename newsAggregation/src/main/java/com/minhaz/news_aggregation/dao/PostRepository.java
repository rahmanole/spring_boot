package com.minhaz.news_aggregation.dao;

import com.minhaz.news_aggregation.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByCat(String cat);
}
