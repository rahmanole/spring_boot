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

        saveAndAssignCategory(postList);
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

    public void saveAndAssignCategory(List<Post> posts) {
        List<String> notsavePostsList = new ArrayList<>();
        int i = 0;

        for (Post post : posts) {
            System.out.println("into loop");
            String cat = post.getPublisherGivenId().split("/")[0];

            if (cat.equals("bangladesh") ||
                    cat.equals("whole-country") ||
                    cat.equals("capital")
            ) {
                post.setCat("bangladesh");

            } else if (cat.equals("politics")) {
                post.setCat("politics");

            } else if (cat.equals("international")) {
                post.setCat("international");

            } else if (cat.equals("economics")) {
                post.setCat("economy");

            } else if (cat.equals("sports")) {
                post.setCat("sports");

            } else if (cat.equals("entertainment")) {
                post.setCat("entertainment");

            } else if (cat.equals("analysis")) {
                post.setCat("opinion");

            }else if (cat.equals("technology")) {
                post.setCat("sciTech");

            } else if (cat.equals("probas")) {
                post.setCat("aboard");
            }

            if (post.getCat() != null) {
                postRepository.save(post);
                System.out.println("naya diganta");
                i++;
            }else{
                notsavePostsList.add(post.getPublisherGivenId());
            }
        }

        System.out.println("Post retrieved:" + posts.size());
        System.out.println("Num of Posts was not saved from naya diganta:" + (posts.size() - i));
        System.out.println("Not saved posts:"+notsavePostsList);
    }
}