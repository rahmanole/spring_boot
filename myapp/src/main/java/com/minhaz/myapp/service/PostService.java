package com.minhaz.myapp.service;


import com.minhaz.myapp.entity.Post;

import java.util.Date;
import java.util.List;

public interface PostService {
    void savePosts();
    List<Post> findAllPosts();
    String getPulisherLogo(String publisher);
    Post getPost(long id);
}
