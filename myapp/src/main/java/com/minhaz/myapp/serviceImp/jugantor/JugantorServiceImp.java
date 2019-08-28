package com.minhaz.myapp.serviceImp.jugantor;


import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.entity.Img;
import com.minhaz.myapp.entity.Para;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.entity.Vdo;
import com.minhaz.myapp.service.NewsPaperService;
import com.minhaz.myapp.service.PostService;
import com.minhaz.myapp.service.TagService;
import com.minhaz.myapp.util.UtilityClass;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class JugantorServiceImp implements NewsPaperService {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;


    public void savePosts() throws Exception {
        List<Post> postList = postService.createPsot("jugantor",
                "https://www.jugantor.com/",
                "h1",
                "dtl_section",
                "dtl_section",
                findPostIds());
//        List<HashSet<String>> catWisePostList = getCatWistPosIdList();

        saveAndAssignCategory(postList);
    }


    // method for finding artcile url
    @Override
    public HashSet<String> findPostIds() throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect("https://www.jugantor.com/").userAgent("Opera").get();
        Element body = document.body();

        Elements posts = body.getElementsByClass("editor_picks_list");
        for (int i = 0; i < 20; i++) {
            String link = posts.get(i).getElementsByTag("a").first()
                    .attr("href")
                    .replace("https://www.jugantor.com/", "");
            postId.add(link.substring(0, link.lastIndexOf('/')));
        }
        return postId;
    }

    public void saveAndAssignCategory(List<Post> posts) {
        List<String> notsavePostsList = new ArrayList<>();
        for (Post post : posts) {

            String cat = post.getPublisherGivenId().split("/")[0];

            if (cat.equals("national") ||
                    cat.equals("capital") ||
                    cat.equals("country-news")
            ) {
                post.setCat("bangladesh");

            } else if (cat.equals("politics")) {
                post.setCat("politics");

            } else if (cat.equals("international")) {
                post.setCat("international");

            } else if (cat.equals("economy")) {
                post.setCat("economy");

            } else if (cat.equals("opinion")) {
                post.setCat("opinion");

            } else if (cat.equals("sports")) {
                post.setCat("sports");

            } else if (cat.equals("editorial")) {
                post.setCat("editorial");

            } else if (cat.equals("entertainment")) {
                post.setCat("entertainment");

            } else if (cat.equals("lifestyle")) {
                post.setCat("lifestyle");

            } else if (cat.equals("tech")) {
                post.setCat("sciTech");

            } else if (cat.equals("exile")) {
                post.setCat("aboard");

            } else if (cat.equals("campus")) {
                post.setCat("campus");

            } else if (cat.equals("various")) {
                post.setCat("others");
            }

            if (post.getCat() != null) {
                postRepository.save(post);
                System.out.println("jugantor");
            } else {
                notsavePostsList.add(post.getPublisherGivenId());
            }
        }
        UtilityClass.showStatistics("Jugantor",posts,notsavePostsList);

    }
}