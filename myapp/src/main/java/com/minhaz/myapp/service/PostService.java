package com.minhaz.myapp.service;


import com.minhaz.myapp.entity.Img;
import com.minhaz.myapp.entity.Para;
import com.minhaz.myapp.entity.Post;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;

@Service
public interface PostService {
    List<Post> createPsot(String publisher,String newsPaperUrl,String htmlTagForHeading,
                          String contentDetailsCssClass,
                          String cssClassForFeatuteImg,
                          HashSet<String> findPostIds) throws Exception;
    List<Para> postBody(Elements articleParas,String publisher);
    void postSave(List<Post> posts);
    void findImgOrVdo(Element articlePara, Para para,String publisher);
    Img featureImgUrl(Element body,String cssClassForFearuteImg,String publisher);
    Page<Post> getPostsByCatForPostPage(String catName,long postId);
    String getPulisherLogo(String publisher);
    Post getPost(long id);

    Page<Post> getPostsByCat(Pageable pageable, String catName);

    Page<Post> getAllPosts(int page);

    Page<Post> getPostsByCat(Pageable pageable, String catName,int page);

}
