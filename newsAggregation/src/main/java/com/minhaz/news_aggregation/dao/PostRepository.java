package com.globalBookShop.gsb.dao;

import com.globalBookShop.gsb.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByCat(String cat);
}
