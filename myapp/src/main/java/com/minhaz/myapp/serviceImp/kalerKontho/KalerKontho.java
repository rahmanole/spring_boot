package com.minhaz.myapp.serviceImp.kalerKontho;


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
public class KalerKontho implements NewsPaperService {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;


    public void savePosts() throws Exception{
        List<Post> postList = postService.createPsot("kaler_kontho",
                "https://www.kalerkantho.com/",
                "h2",
                "some-class-name2",
                "img-popup",
                findPostIds());
//        List<HashSet<String>> catWisePostList = getCatWistPosIdList();

        postList.forEach(post -> {
            try {
                assignCategory(post.getPublisherGivenId(),post);
                postRepository.save(post);
                System.out.println("kaler_kontho");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }



    // method for finding artcile url
    @Override
    public HashSet<String> findPostIds() throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect("https://www.kalerkantho.com/online/entertainment").userAgent("Opera").get();
        Element body = document.body();
        Element latestNews = body.getElementById("myTabContent");
        Elements posts = latestNews.getElementsByTag("li");
        for (int i=0;i<20;i++) {
            String link = posts.get(i).getElementsByTag("a").first()
                    .attr("href");
            postId.add(link.substring(2));
        }
        return postId;
    }

    //This method for finding post url from specific category
    @Override
    public HashSet<String> findPostIds(String catWiseUrl) throws IOException {
        HashSet<String> postId = new HashSet<String>();
        Document document = Jsoup.connect(catWiseUrl).userAgent("Opera").get();
        Element body = document.body();
        Elements posts = body.getElementsByClass("leadmorehl2");

        for (Element post:posts) {
            String link = post.getElementsByTag("a").first()
                    .attr("href")
                    .replace("https://www.jugantor.com/","");

            postId.add(link.substring(0,link.lastIndexOf('/')));

            if(postId.size()>21)
                break;
        }

        return postId;
    }

    @Override
    public List<HashSet<String>> getCatWistPosIdList() throws Exception{

        List<HashSet<String>> list = new ArrayList<>();
        list.add(findPostIds("https://www.jugantor.com/politics"));
        list.add(findPostIds("https://www.jugantor.com/national"));
        list.add(findPostIds("https://www.jugantor.com/capital"));
        list.add(findPostIds("https://www.jugantor.com/country-news"));
        list.add(findPostIds("https://www.jugantor.com/international"));
        list.add(findPostIds("https://www.jugantor.com/economics"));
        list.add(findPostIds("https://www.jugantor.com/opinion"));
        list.add(findPostIds("https://www.jugantor.com/sports"));
        list.add(findPostIds("https://www.jugantor.com/entertainment"));
        list.add(findPostIds("https://www.jugantor.com/tech"));
        list.add(findPostIds("https://www.jugantor.com/exile"));
        list.add(findPostIds("https://www.jugantor.com/editorial"));
        list.add(findPostIds("https://www.jugantor.com/campus"));
        list.add(findPostIds("https://www.jugantor.com/various"));
        return list;
    }

    @Override
    public void assignCategory(String id,Post post,List<HashSet<String>>  list){
        if(list.get(0).contains(id) ){
            post.setCat("politics");
            return;
        }
        if(list.get(1).contains(id) ||
                list.get(2).contains(id) || list.get(3).contains(id)){
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
            post.setCat("opinion");
            return;
        }
        if(list.get(7).contains(id)){
            post.setCat("sports");
            return;
        }

        if(list.get(8).contains(id)){
            post.setCat("entertainment");
            return;
        }
        if(list.get(9).contains(id)){
            post.setCat("sciTech");
            return;
        }
        if(list.get(10).contains(id)){
            post.setCat("aboard");
            return;
        }
        if(list.get(11).contains(id)){
            post.setCat("editorial");
            return;
        }if(list.get(12).contains(id)){
            post.setCat("campus");
            return;
        }
        if(list.get(13).contains(id)){
            post.setCat("others");
            return;
        }
    }

    @Override
    public void assignCategory(String id, Post post) {
        String cat = id.split("/")[1];

        if (cat.equals("national") || cat.equals("country-news")) {
            post.setCat("bangladesh");
            return;
        }
        if (cat.equals("world")) {
            post.setCat("international");
            return;
        }
        if (cat.equals("business")) {
            post.setCat("economy");
            return;
        }

        if (cat.equals("sports")) {
            post.setCat("sports");
            return;
        }
        if (cat.equals("entertainment")) {
            post.setCat("entertainment");
            return;
        }
        if (cat.equals("info-tech")) {
            post.setCat("sciTech");
            return;
        }
        if (cat.equals("miscellaneous")) {
            post.setCat("others");
            return;
        }
    }
}
