package com.minhaz.myapp.serviceImp.inqilab;


import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.service.NewsPaperService;
import com.minhaz.myapp.service.PostService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class InqilabServiceImp implements NewsPaperService{



    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    public void savePosts() throws Exception{
        List<Post> postList = postService.createPsot("inqilab",
                "https://www.dailyinqilab.com/article/",
                "h1",
                "article",
                "image_block",
                findPostIds());
        List<HashSet<String>> catWisePostList = getCatWistPosIdList();

        postList.forEach(post -> {
            try {
                assignCategory(post.getPublisherGivenId(),post,catWisePostList);
                postRepository.save(post);
                System.out.println("prothom_alo");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    // method for finding artcile url
    @Override
    public HashSet<String> findPostIds() throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect("https://www.dailyinqilab.com/allnews/latest/").userAgent("Opera").get();
        Element body = document.body();

        Elements posts = body.getElementsByClass("news_list").first().getElementsByClass("col-xs-12");

        for (Element post : posts) {
            String link = post.getElementsByTag("a").attr("href");
                postId.add(link.substring(0,link.indexOf("%")-1).replace("https://www.dailyinqilab.com/article/",""));
        }
        return postId;
    }

    //This method for finding post url from specific category
    @Override
    public HashSet<String> findPostIds(String catWiseUrl) throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect(catWiseUrl).userAgent("Opera").get();
        Element body = document.body();

        Elements posts = body.getElementsByClass("news_list").first().getElementsByClass("col-xs-12");

        for (Element post : posts) {
            String link = post.getElementsByTag("a").attr("href");
            postId.add(link.substring(0,link.indexOf("%")-1).replace("https://www.dailyinqilab.com/article/",""));
        }
        return postId;
    }

    public List<HashSet<String>> getCatWistPosIdList() throws Exception{

        List<HashSet<String>> list = new ArrayList<>();
        list.add(findPostIds("https://www.dailyinqilab.com/newscategory/national-news/"));
        list.add(findPostIds("https://www.dailyinqilab.com/newscategory/all-bangladesh/"));
        list.add(findPostIds("https://www.dailyinqilab.com/newscategory/inter-country/"));
        list.add(findPostIds("https://www.dailyinqilab.com/newscategory/city/"));
        list.add(findPostIds("https://www.dailyinqilab.com/newscategory/international/"));
        list.add(findPostIds("https://www.dailyinqilab.com/newscategory/business/"));
        list.add(findPostIds("https://www.dailyinqilab.com/newscategory/sports/"));
        list.add(findPostIds("https://www.dailyinqilab.com/newscategory/daily-entertainment/"));
        list.add(findPostIds("https://www.dailyinqilab.com/newscategory/foreign-life/"));
        list.add(findPostIds("https://www.dailyinqilab.com/newscategory/editors/"));
        return list;
    }

    public void assignCategory(String id,Post post,List<HashSet<String>>  list){

        if(
                list.get(0).contains(id) ||
                list.get(1).contains(id) ||
                list.get(2).contains(id) ||
                list.get(3).contains(id)

        ){
            post.setCat("bangladesh");
            return;
        }
        if(list.get(4).contains(id)){
            post.setCat("international");
            return;
        }
        if(list.get(5).contains(id)){
            post.setCat("economy");
            return;
        }
        if(list.get(6).contains(id)){
            post.setCat("sports");
            return;
        }
        if(list.get(7).contains(id)){
            post.setCat("entertainment");
            return;
        }
        if(list.get(8).contains(id)){
            post.setCat("aboard");
            return;
        }
        if(list.get(9).contains(id)){
            post.setCat("editorial");
            return;
        }
    }

    @Override
    public void assignCategory(String id, Post post) {

    }
}
