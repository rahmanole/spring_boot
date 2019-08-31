package com.minhaz.myapp.serviceImp.amadersomoy;


import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.service.NewsPaperService;
import com.minhaz.myapp.service.PostService;
import com.minhaz.myapp.util.UtilityClass;
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
public class AmaderSomoy implements NewsPaperService{



    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    public void savePosts() throws Exception{
        List<Post> postList = postService.createPsot("prothom_alo",
                "http://www.dainikamadershomoy.com/",
                "h1",
                "w3-rest",
                "w3-rest",
                findPostIds());
        List<HashSet<String>> catWisePostList = getCatWistPosIdList();
        List<String> notsavePostsList = new ArrayList<>();
        postList.forEach(post -> {
            try {
                assignCategory(post.getPublisherGivenId(),post,catWisePostList);
                if(post.getCat() != null){
                    postRepository.save(post);
                }else{
                    notsavePostsList.add(post.getPublisherGivenId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        UtilityClass.showStatistics("Amader_somoy",postList,notsavePostsList);

    }

    // method for finding artcile url
    @Override
    public HashSet<String> findPostIds() throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect("http://www.dainikamadershomoy.com/archive/all").userAgent("Opera").get();
        Element body = document.body();

        Elements posts = body.getElementById("latest").getElementsByTag("a");
        for (Element post : posts) {
            String link = post.attr("href");
                postId.add(link.replace("http://www.dainikamadershomoy.com/",""));
        }
        return postId;
    }

    //This method for finding post url from specific category

    public HashSet<String> findPostIds(String catWiseUrl) throws IOException {
        HashSet<String> postId = new HashSet<String>();
        Document document = Jsoup.connect(catWiseUrl).userAgent("Opera").get();
        Element body = document.body();
        Elements posts = body.getElementsByClass("each");
        for (int i = 0; i < 5; i++) {
            String link = posts.get(i).getElementsByTag("a").first().attr("href");
            if (link.length() > 100)
                postId.add(link.substring(0, link.indexOf('%')));
        }
        return postId;
    }

    public List<HashSet<String>> getCatWistPosIdList() throws Exception{

        List<HashSet<String>> list = new ArrayList<>();
        list.add(findPostIds("http://www.dainikamadershomoy.com/bangladesh"));
        list.add(findPostIds("http://www.dainikamadershomoy.com/international"));
        list.add(findPostIds("http://www.dainikamadershomoy.com/entertainment"));
        list.add(findPostIds("http://www.dainikamadershomoy.com/sports"));
        list.add(findPostIds("http://www.dainikamadershomoy.com/opinion"));
        list.add(findPostIds("http://www.dainikamadershomoy.com/economy"));
        list.add(findPostIds("http://www.dainikamadershomoy.com/probash"));
        list.add(findPostIds("http://www.dainikamadershomoy.com/lifestyle/"));
        return list;

    }

    public void assignCategory(String id,Post post,List<HashSet<String>>  list){

        if(list.get(0).contains(id)){
            post.setCat("bangladesh");
            return;
        }
        if(list.get(1).contains(id)){
            post.setCat("international");
            return;
        }
        if(list.get(2).contains(id)){
            post.setCat("entertainment");
            return;
        }
        if(list.get(3).contains(id)){
            post.setCat("sports");
            return;
        }
        if(list.get(4).contains(id)){
            post.setCat("opinion");
            return;
        }
        if(list.get(5).contains(id)){
            post.setCat("economy");
            return;
        }
        if(list.get(6).contains(id)){
            post.setCat("aboard");
            return;
        }
        if(list.get(7).contains(id)){
            post.setCat("lifestyle");
            return;
        }
    }


    @Override
    public void saveAndAssignCategory(List<Post> posts) {

    }
}
