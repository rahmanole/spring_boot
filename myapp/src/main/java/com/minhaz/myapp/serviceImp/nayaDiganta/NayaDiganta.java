package com.minhaz.myapp.serviceImp.nayaDiganta;


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
public class NayaDiganta implements NewsPaperService {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;


    public void savePosts() throws Exception {
        List<Post> postList = postService.createPsot("naya_diganta",
                "http://www.dailynayadiganta.com/",
                "h1",
                "news-content",
                "image-holder",
                findPostIds());
//        List<HashSet<String>> catWisePostList = getCatWistPosIdList();

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
        Document document = Jsoup.connect("http://www.dailynayadiganta.com/archive").userAgent("Opera").get();
        Element body = document.body();

        Elements posts = body.getElementsByClass("archive").first().getElementsByTag("li");

        //Naya diganta uploads more than 20 posts at a time
        //That's why all posts are taken
        for (int i = 0; i < 20; i++) {
            String link = posts.get(i).getElementsByTag("a").first()
                    .attr("href")
                    .replace("http://www.dailynayadiganta.com/", "");
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

        if (cat.equals("law-and-justice") ||
                cat.equals("crime") ||
                cat.equals("diplomacy") ||
                cat.equals("administration") ||
                cat.equals("Incident-accident") ||
                cat.equals("organization") ||
                cat.equals("election") ||
                cat.equals("khulna") ||
                cat.equals("barishal") ||
                cat.equals("sylhet") ||
                cat.equals("rangpur") ||
                cat.equals("mymensingh") ||
                cat.equals("rajshahi") ||
                cat.equals("chattagram") ||
                cat.equals("dhaka") ||
                cat.equals("last-page") ||
                cat.equals("parliament")
        ) {
            post.setCat("bangladesh");
            return;
        }
        if (cat.equals("politics")) {
            post.setCat("politics");
            return;
        }
        if (cat.equals("international") ||
                cat.equals("onnodiganta") ||
                cat.equals("subcontinent") ||
                cat.equals("middle-east") ||
                cat.equals("turkey") ||
                cat.equals("usa-canad") ||
                cat.equals("america") ||
                cat.equals("europe") ||
                cat.equals("africa") ||
                cat.equals("australia") ||
                cat.equals("international-organizations") ||
                cat.equals("asia")

        ) {
            post.setCat("international");
            return;
        }
        if (cat.equals("economics")) {
            post.setCat("economy");
            return;
        }
        if (cat.equals("opinion")) {
            post.setCat("opinion");
        }

        if (cat.equals("athletics") ||
                cat.equals("tennis") ||
                cat.equals("hockey") ||
                cat.equals("cricket") ||
                cat.equals("football") ||
                cat.equals("swimming") ||
                cat.equals("sports")

        ) {
            post.setCat("sports");
            return;
        }
        if (cat.equals("sub-editorial")) {
            post.setCat("editorial");
            return;
        }

        if (cat.equals("cinema") ||
                cat.equals("fashion") ||
                cat.equals("television") ||
                cat.equals("radio") ||
                cat.equals("natok") ||
                cat.equals("music")) {
            post.setCat("entertainment");
            return;
        }
        if (cat.equals("health") ||
                cat.equals("fashion") ||
                cat.equals("parenting") ||
                cat.equals("housekeeping") ||
                cat.equals("Cooking") ||
                cat.equals("travel")

        ) {
            post.setCat("sports");
            return;
        }
        if (cat.equals("tech")) {
            post.setCat("sciTech");
            return;
        }
        if (cat.equals("exile")) {
            post.setCat("aboard");
            return;
        }
        if (cat.equals("campus")) {
            post.setCat("campus");
            return;
        }
        if (cat.equals("miscellaneous")) {
            post.setCat("others");
            return;
        }
    }
}
