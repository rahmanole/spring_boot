package com.minhaz.myapp.service;


import com.minhaz.myapp.entity.Post;

import java.util.Date;
import java.util.List;

public interface PostService {
    void savePosts();
    List<Post> findAllAndOrderByDateTimeDesc();
    long show(Date date);

}
