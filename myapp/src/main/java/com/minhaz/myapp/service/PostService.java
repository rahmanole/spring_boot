package com.minhaz.myapp.service;


import com.minhaz.myapp.entity.Img;
import com.minhaz.myapp.entity.Para;
import com.minhaz.myapp.entity.Post;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public interface PostService {
    List<Post> createPsot(String publisher,String newsPaperUrl,String htmlTagForHeading,
                          String contentDetailsCssClass,
                          String cssClassForFeatuteImg,
                          HashSet<String> findPostIds) throws Exception;
    List<Para> psotBody(Elements articleParas,Element body,String cssClassForFeatuteImg,String publisher);
    void findImgOrVdo(Element articlePara, Para para,String publisher);
    String featureImgUrl(Element body,String cssClassForFearuteImg,String publisher);
    List<Post> findAllPosts();
    String getPulisherLogo(String publisher);
    Post getPost(long id);

    Page<Post> getPostsByCat(Pageable pageable, String catName);

    Page<Post> getPostsByCat(Pageable pageable, String catName,int page);

    Page<Post> getAllPosts(int page,String orderParameter);
}
