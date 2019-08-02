package com.minhaz.myapp.service;

import com.minhaz.myapp.entity.Post;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public interface NewsPaperService {
    void savePosts () throws Exception;
    HashSet<String> findPostIds() throws IOException;
    HashSet<String> findPostIds(String catWiseUrl) throws IOException;
    List<HashSet<String>> getCatWistPosIdList() throws Exception;
    void assignCategory(String id,Post post,List<HashSet<String>>  list);
    void assignCategory(String id,Post post);
}
