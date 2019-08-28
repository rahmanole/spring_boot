package com.minhaz.myapp.service;

import com.minhaz.myapp.entity.Post;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public interface NewsPaperService {
    void savePosts () throws Exception;
    HashSet<String> findPostIds() throws IOException;
    void saveAndAssignCategory(List<Post> posts);
}
