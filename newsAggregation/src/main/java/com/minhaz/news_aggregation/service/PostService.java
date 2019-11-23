package com.minhaz.news_aggregation.service;

import com.minhaz.news_aggregation.entity.Post;

import java.util.List;

public interface PostService {
    void savePosts();
    List<Post> findAllPosts();
}
