package com.globalBookShop.gsb.service;

import com.globalBookShop.gsb.entity.Post;

import java.util.List;

public interface PostService {
    void savePosts();
    List<Post> findAllPosts();
}
