package com.minhaz.myapp.service;

import com.minhaz.myapp.entity.Post;

public interface TagService {
    void assignTag(Post post, String id) throws Exception;
}
