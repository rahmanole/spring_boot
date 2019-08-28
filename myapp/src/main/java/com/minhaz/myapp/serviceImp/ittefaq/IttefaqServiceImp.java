package com.minhaz.myapp.serviceImp.ittefaq;


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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public void savePosts() throws Exception {
        List<Post> postList = postService.createPsot("ittefaq",
                "https://www.ittefaq.com.bd/",
                "h1",
                "dtl_content_block",
                "dtl_img_block",
                findPostIds());
//        List<HashSet<String>> catWisePostList = getCatWistPosIdList();

        saveAndAssignCategory(postList);
    }


    // method for finding artcile url
    @Override
    public HashSet<String> findPostIds() throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect("https://www.ittefaq.com.bd/all-news").userAgent("Opera").get();
        Element body = document.body();

        Elements posts = body.getElementsByClass("allnews");
        for (int i = 0; i < 20; i++) {
            String link = posts.get(i).getElementsByTag("a").first()
                    .attr("href")
                    .replace("https://www.ittefaq.com.bd", "");
            postId.add(link.substring(1, link.lastIndexOf('/')));
        }
        return postId;
    }

    public void saveAndAssignCategory(List<Post> posts) {
        List<String> notsavePostsList = new ArrayList<>();
        for (Post post : posts) {

            String cat = post.getPublisherGivenId().split("/")[0];

            if (cat.equals("national") ||
                    cat.equals("budget2019") ||
                    cat.equals("wholecountry") ||
                    cat.equals("capital") ||
                    cat.equals("court")
            ) {
                post.setCat("bangladesh");

            } else if (cat.equals("politics")) {
                post.setCat("politics");

            } else if (cat.equals("worldnews")) {
                post.setCat("international");

            } else if (cat.equals("economy")) {
                post.setCat("economy");

            } else if (cat.equals("print-edition")) {

                if (post.getPublisherGivenId().split("/")[2].equals("opinion")) {
                    post.setCat("opinion");
                } else {
                    post.setCat("editorial");
                }

            } else if (cat.equals("sports")) {
                post.setCat("sports");

            } else if (cat.equals("entertainment")) {
                post.setCat("entertainment");

            } else if (cat.equals("scienceandtechnology")) {
                post.setCat("sciTech");

            } else if (cat.equals("aboard")) {
                post.setCat("aboard");

            } else if (cat.equals("education")) {
                post.setCat("campus");
            }

            if (post.getCat() != null) {
                postRepository.save(post);
                System.out.println("ittefaq");
            } else {
                notsavePostsList.add(post.getPublisherGivenId());

            }
        }
        UtilityClass.showStatistics("Ittefaq",posts,notsavePostsList);
    }
}