package com.minhaz.myapp.serviceImp.nayaDiganta;


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

        saveAndAssignCategory(postList);
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


    public void saveAndAssignCategory(List<Post> posts) {
        List<String> notsavePostsList = new ArrayList<>();
        for (Post post : posts) {
            String cat = post.getPublisherGivenId().split("/")[1];

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

            } else if (cat.equals("politics")) {
                post.setCat("politics");

            } else if (cat.equals("international") ||
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

            } else if (cat.equals("economics")) {
                post.setCat("economy");

            } else if (cat.equals("opinion")) {
                post.setCat("opinion");

            } else if (cat.equals("athletics") ||
                    cat.equals("tennis") ||
                    cat.equals("hockey") ||
                    cat.equals("cricket") ||
                    cat.equals("football") ||
                    cat.equals("swimming") ||
                    cat.equals("sports")

            ) {
                post.setCat("sports");

            } else if (cat.equals("sub-editorial")) {
                post.setCat("editorial");

            } else if (cat.equals("cinema") ||
                    cat.equals("fashion") ||
                    cat.equals("television") ||
                    cat.equals("radio") ||
                    cat.equals("natok") ||
                    cat.equals("music")) {
                post.setCat("entertainment");

            } else if (cat.equals("health") ||
                    cat.equals("fashion") ||
                    cat.equals("parenting") ||
                    cat.equals("housekeeping") ||
                    cat.equals("Cooking") ||
                    cat.equals("travel")

            ) {
                post.setCat("lifestyle");

            } else if (cat.equals("tech")) {
                post.setCat("sciTech");

            } else if (cat.equals("exile")) {
                post.setCat("aboard");

            } else if (cat.equals("campus")) {
                post.setCat("campus");

            } else if (cat.equals("miscellaneous")) {
                post.setCat("others");
            }

            if (post.getCat() != null) {
                postRepository.save(post);
                System.out.println("naya_diganta");
            }else{
                notsavePostsList.add(post.getPublisherGivenId());
            }
        }
        UtilityClass.showStatistics("Naya_diganta",posts,notsavePostsList);

    }
}