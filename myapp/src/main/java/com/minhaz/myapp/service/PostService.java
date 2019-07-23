package com.minhaz.myapp.service;


import com.minhaz.myapp.entity.Img;
import com.minhaz.myapp.entity.Para;
import com.minhaz.myapp.entity.Post;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public interface PostService {
    List<Post> createPsot(String publisher,String newsPaperUrl,String htmlTagForHeading,
                          String contentDetailsCssClass,
                          String cssClassForFeatuteImg,
                          HashSet<String> findPostIds) throws Exception;
    List<Para> psotBody(Elements articleParas,Element body,String cssClassForFeatuteImg,String publisher);
    HashSet<Img> findImgOrVdo(Element articlePara, Para para,String publisher);
    String featureImgUrl(Element body,String cssClassForFearuteImg,String publisher);
    List<Post> findAllPosts();
    String getPulisherLogo(String publisher);
    Post getPost(long id);
}
