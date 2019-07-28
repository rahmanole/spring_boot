package com.minhaz.myapp.serviceImp.ittefaq;


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
public class IttefaqServiceImp implements NewsPaperService {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    public void savePosts() throws Exception{
        List<Post> postList = postService.createPsot("ittefaq",
                "https://www.ittefaq.com.bd",
                "h1",
                "dtl_content_block",
                "dtl_img_block",
                findPostIds());
        List<HashSet<String>> catWisePostList = getCatWistPosIdList();

        postList.forEach(post -> {
            try {
                assignCategory(post.getPublisherGivenId(),post,catWisePostList);
                postRepository.save(post);
                System.out.println("ittefaq");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }



    // method for finding artcile url
    @Override
    public HashSet<String> findPostIds() throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect("https://www.ittefaq.com.bd/all-news").userAgent("Opera").get();
        Element body = document.body();

        Elements posts = body.getElementsByClass("allnews");
        for (int i=0;i<20;i++) {
            String link = posts.get(i).getElementsByTag("a").first()
                    .attr("href")
                    .replace("https://www.ittefaq.com.bd","");
            postId.add(link.substring(0,link.lastIndexOf('/')));
        }
        return postId;
    }

    //This method for finding post url from specific category
    @Override
    public HashSet<String> findPostIds(String catWiseUrl) throws IOException {
        HashSet<String> postId = new HashSet<String>();
        Document document = Jsoup.connect(catWiseUrl).userAgent("Opera").get();
        Element body = document.body();
        Elements posts = body.getElementsByClass("category-lead-top");

        for (Element post:posts) {
            String link = post.getElementsByTag("a").first()
                    .attr("href")
                    .replace("https://www.ittefaq.com.bd","");

            postId.add(link.substring(0,link.lastIndexOf('/')));

            if(postId.size()>21)
                break;
        }

        posts = body.getElementsByClass("category-mid-top");

        for (Element post:posts) {
            String link = post.getElementsByTag("a").first()
                    .attr("href")
                    .replace("https://www.ittefaq.com.bd","");

            postId.add(link.substring(0,link.lastIndexOf('/')));

            if(postId.size()>21)
                break;
        }

        return postId;
    }

    @Override
    public List<HashSet<String>> getCatWistPosIdList() throws Exception{

        List<HashSet<String>> list = new ArrayList<>();
        list.add(findPostIds("https://www.ittefaq.com.bd/politics"));
        list.add(findPostIds("https://www.ittefaq.com.bd/national"));
        list.add(findPostIds("https://www.ittefaq.com.bd/budget2019"));
        list.add(findPostIds("https://www.ittefaq.com.bd/wholecountry"));
        list.add(findPostIds("https://www.ittefaq.com.bd/capital"));
        list.add(findPostIds("https://www.ittefaq.com.bd/court"));
        list.add(findPostIds("https://www.ittefaq.com.bd/worldnews"));
        list.add(findPostIds("https://www.ittefaq.com.bd/economy"));
        list.add(findPostIds("https://www.ittefaq.com.bd/print-edition/opinion"));
        list.add(findPostIds("https://www.ittefaq.com.bd/sports"));
        list.add(findPostIds("https://www.ittefaq.com.bd/entertainment"));
        list.add(findPostIds("https://www.ittefaq.com.bd/scienceandtechnology"));
        list.add(findPostIds("https://www.ittefaq.com.bd/aboard"));
        list.add(findPostIds("https://www.ittefaq.com.bd/print-edition/editorial"));
        list.add(findPostIds("https://www.ittefaq.com.bd/education"));
        return list;
    }

    @Override
    public void assignCategory(String id,Post post,List<HashSet<String>>  list){
        if(list.get(0).contains(id) ){
            post.setCat("politics");
            return;
        }
        if(list.get(1).contains(id) ||
                list.get(2).contains(id) || list.get(3).contains(id)
                || list.get(4).contains(id)
                || list.get(5).contains(id)){
            post.setCat("bangladesh");
            return;
        }
        if(list.get(6).contains(id)){
            post.setCat("international");
            return;
        }
        if(list.get(7).contains(id)){
            post.setCat("economy");
            return;
        }
        if(list.get(8).contains(id)){
            post.setCat("opinion");
            return;
        }
        if(list.get(9).contains(id)){
            post.setCat("sports");
            return;
        }

        if(list.get(10).contains(id)){
            post.setCat("entertainment");
            return;
        }
        if(list.get(11).contains(id)){
            post.setCat("sciTech");
            return;
        }
        if(list.get(12).contains(id)){
            post.setCat("aboard");
            return;
        }
        if(list.get(13).contains(id)){
            post.setCat("editorial");
            return;
        }
        if(list.get(14).contains(id)){
            post.setCat("campus");
            return;
        }
    }

    private String findPostCat(String id) {

        return id.split("/")[1];
    }



}
