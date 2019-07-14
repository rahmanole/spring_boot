package com.minhaz.myapp.service;

import com.minhaz.myapp.entity.Post;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public interface ProthomAloService {
    List<Post> createPsot() throws Exception;
    HashSet<String> findPostIds() throws IOException;
    HashSet<String> findPostIds(String catWiseUrl) throws IOException;
}
