package com.minhaz.myapp.service;

import com.minhaz.myapp.entity.Post;

public interface TagService {
    void manageCatAndTags(String cat,Post post,String id) throws Exception;
    void assignTagForBDPosts(Post post, String id) throws Exception;
    void assignTagForIntPosts(Post post, String id) throws Exception;
    void assignTagForEconomy(Post post, String id) throws Exception;
    void assignTagForOpinion(Post post, String id) throws Exception;
}
