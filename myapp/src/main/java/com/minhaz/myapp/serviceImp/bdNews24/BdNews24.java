package com.minhaz.myapp.serviceImp.bdNews24;


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
public class BdNews24 implements NewsPaperService {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;


    public void savePosts() throws Exception {
        List<Post> postList = postService.createPsot("bdnews24",
                "https://bangla.bdnews24.com/",
                "h1",
                "custombody",
                "gallery-image-box",
                findPostIds());
        saveAndAssignCategory(postList);
    }


    // method for finding artcile url
    @Override
    public HashSet<String> findPostIds() throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect("https://bangla.bdnews24.com/news/").userAgent("Opera").get();
        Element body = document.body();

        Elements posts = body.getElementsByClass("paginationSimple576272").first()
                .getElementsByTag("li");
        for (int i = 0; i < 20; i++) {

            /*
             * business logic used here is slightly different from service implementations.
             * Because all latest news of bdNews24 contains two <a> tag.while using the logic,
             * used in other services implementations, it shows the link of second <a> tag
             * which contains category link of that particular link.
             */

            String link = posts.get(i).getElementsByTag("a").outerHtml();
            int firstIndex = link.indexOf("\"") + 1;
            int lastIndex = link.indexOf("\"", 20);
            link = link.substring(firstIndex, lastIndex).replace("https://bangla.bdnews24.com/", "");

            postId.add(link);
        }
        return postId;
    }


    public void saveAndAssignCategory(List<Post> posts) {
        List<String> notsavePostsList = new ArrayList<>();
        for (Post post : posts) {

            String cat = post.getPublisherGivenId().split("/")[0];

            if (cat.equals("bangladesh") ||
                    cat.equals("environment") ||
                    cat.equals("janadurbhog") ||
                    cat.equals("samagrabangladesh") ||
                    cat.equals("ctg") ||
                    cat.equals("health")
            ) {
                post.setCat("bangladesh");

            } else if (cat.equals("politics")) {
                post.setCat("politics");

            } else if (cat.equals("world")) {
                post.setCat("international");

            } else if (cat.equals("economy") ||
                    cat.equals("stocks") ||
                    cat.equals("business")) {
                post.setCat("economy");

            } else if (cat.equals("bangla")) {
                post.setCat("opinion");

            } else if (cat.equals("cricket") || cat.equals("sport")) {
                post.setCat("sports");
            } else if (cat.equals("editorial")) {
                post.setCat("editorial");

            } else if (cat.equals("glitz")) {
                post.setCat("entertainment");

            } else if (cat.equals("science") ||
                    cat.equals("tech")) {
                post.setCat("sciTech");

            } else if (cat.equals("probash")) {
                post.setCat("aboard");
            } else if (cat.equals("lifestyle")) {

                post.setCat("lifestyle");

            } else if (cat.equals("campus")) {

                post.setCat("campus");

            } else if (cat.equals("various")) {

                post.setCat("others");
            }

            if (post.getCat() != null) {
                postRepository.save(post);
            } else {
                notsavePostsList.add(post.getPublisherGivenId());
            }
        }

        UtilityClass.showStatistics("BDNews24",posts,notsavePostsList);

    }
}
