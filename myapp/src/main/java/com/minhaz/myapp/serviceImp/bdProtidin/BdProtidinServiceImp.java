package com.minhaz.myapp.serviceImp.bdProtidin;


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
public class BdProtidinServiceImp implements NewsPaperService {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;


    public void savePosts() throws Exception {
        List<Post> postList = postService.createPsot("bd_pratidin",
                "https://www.bd-pratidin.com/",
                "h1",
                "container-left-area",
                "main-image",
                findPostIds());
        saveAndAssignCategory(postList);
    }


    // method for finding artcile url
    @Override
    public HashSet<String> findPostIds() throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect("https://www.bd-pratidin.com").userAgent("Opera").get();
        Element body = document.body();

        Elements posts = body.getElementsByClass("home-latest-news").first().getElementsByTag("a");
        for (int i = 0; i < 20; i++) {
            String link = posts.get(i).attr("href");
            postId.add(link);
        }
        return postId;
    }

    public void saveAndAssignCategory(List<Post> posts) {
        List<String> notsavePostsList = new ArrayList<>();
        for (Post post : posts) {
            String cat = post.getPublisherGivenId().split("/")[0];

            if (cat.equals("national") ||
                    cat.equals("country") ||
                    cat.equals("dengue-update") ||
                    cat.equals("city-news") ||
                    cat.equals("chittagong-pratidin") ||
                    cat.equals("city-roundup") ||
                    cat.equals("chayer-desh")) {
                post.setCat("bangladesh");

            } else if (cat.equals("international-news") || cat.equals("kolkata")) {
                post.setCat("international");

            } else if (cat.equals("corporate-corner")) {
                post.setCat("economy");

            } else if (cat.equals("facebook") || post.getPublisherGivenId().equals("readers-column")) {
                post.setCat("opinion");

            } else if (cat.equals("sports")) {
                post.setCat("sports");

            } else if (cat.equals("entertainment")) {
                post.setCat("entertainment");

            } else if (cat.equals("tech-world")) {
                post.setCat("sciTech");

            } else if (cat.equals("probash-potro")) {
                post.setCat("aboard");

            } else if (cat.equals("campus-online")) {
                post.setCat("campus");

            } else if (cat.equals("job-market")) {
                post.setCat("jobs");

            } else if (cat.equals("mixter")) {
                post.setCat("others");
            }

            if (post.getCat() != null) {
                postRepository.save(post);
            } else {
                notsavePostsList.add(post.getPublisherGivenId());
            }
        }

        UtilityClass.showStatistics("BDPratidin",posts,notsavePostsList);

    }
}
