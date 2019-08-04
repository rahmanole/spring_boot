package com.minhaz.myapp.serviceImp.somokal;


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
public class SomoKalServiceImp implements NewsPaperService {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;


    public void savePosts() throws Exception {
        List<Post> postList = postService.createPsot("samakal",
                "https://samakal.com/",
                "h1",
                "description",
                "image-container",
                findPostIds());

        postList.forEach(post -> {
            try {
                assignCategory(post.getPublisherGivenId(), post);
                postRepository.save(post);
                System.out.println("naya_diganta");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    // method for finding artcile url
    @Override
    public HashSet<String> findPostIds() throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect("https://samakal.com/").userAgent("Opera").get();
        Element body = document.body();

        Elements posts = body.getElementById("latestnews").getElementsByTag("a");

        for (int i = 0; i < 10; i++) {
            String link = posts.get(i).
                    attr("href")
                    .replace("https://samakal.com/", "");
            postId.add(link.substring(0, link.lastIndexOf('/')));
        }
        return postId;
    }

    //This method for finding post url from specific category
    @Override
    public HashSet<String> findPostIds(String catWiseUrl) throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect(catWiseUrl).userAgent("Opera").get();
        Element body = document.body();

        Elements posts = body.getElementsByClass("subcategory")
                        .first().getElementsByTag("a");

        //Naya diganta uploads more than 20 posts at a time
        //That's why all posts are taken
        for (int i = 0; i < posts.size(); i++) {
            String link = posts.get(i).attr("href")
                    .replace("http://www.dailynayadiganta.com/", "");
            postId.add(link.substring(0, link.lastIndexOf('/')).split("/")[1]);
        }
        return postId;
    }

    @Override
    public List<HashSet<String>> getCatWistPosIdList() throws Exception {

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
    public void assignCategory(String id, Post post, List<HashSet<String>> list) {
        if (list.get(0).contains(id)) {
            post.setCat("politics");
            return;
        }
        if (list.get(1).contains(id) ||
                list.get(2).contains(id) || list.get(3).contains(id)) {
            post.setCat("bangladesh");
            return;
        }
        if (list.get(4).contains(id)) {
            post.setCat("international");
            return;
        }
        if (list.get(5).contains(id)) {
            post.setCat("economy");
            return;
        }
        if (list.get(6).contains(id)) {
            post.setCat("opinion");
            return;
        }
        if (list.get(7).contains(id)) {
            post.setCat("sports");
            return;
        }

        if (list.get(8).contains(id)) {
            post.setCat("entertainment");
            return;
        }
        if (list.get(9).contains(id)) {
            post.setCat("sciTech");
            return;
        }
        if (list.get(10).contains(id)) {
            post.setCat("aboard");
            return;
        }
        if (list.get(11).contains(id)) {
            post.setCat("editorial");
            return;
        }
        if (list.get(12).contains(id)) {
            post.setCat("campus");
            return;
        }
        if (list.get(13).contains(id)) {
            post.setCat("others");
            return;
        }
    }

    @Override
    public void assignCategory(String id, Post post) {
        String cat = id.split("/")[0];

        if (cat.equals("bangladesh") ||
                cat.equals("whole-country")
        ) {
            post.setCat("bangladesh");
            return;
        }
        if (cat.equals("politics")) {
            post.setCat("politics");
            return;
        }
        if (cat.equals("international")) {
            post.setCat("international");
            return;
        }
        if (cat.equals("economics")) {
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
        if (cat.equals("technology")) {
            post.setCat("sciTech");
            return;
        }
        if (cat.equals("probas")) {
            post.setCat("aboard");
            return;
        }
    }
}
