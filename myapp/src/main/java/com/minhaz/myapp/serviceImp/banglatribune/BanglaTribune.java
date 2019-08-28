package com.minhaz.myapp.serviceImp.banglatribune;


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
public class BanglaTribune implements NewsPaperService {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;


    public void savePosts() throws Exception{
        List<Post> postList = postService.createPsot("bangla_tribune",
                "http://www.banglatribune.com/",
                "h2",
                "detail_article",
                "jw_detail_content_holder",
                findPostIds());
        saveAndAssignCategory(postList);
    }



    // method for finding artcile url
    @Override
    public HashSet<String> findPostIds() throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect("http://www.banglatribune.com/archive").userAgent("Opera").get();
        Element body = document.body();

        Elements posts = body.getElementsByClass("each");
        for (int i=0;i<posts.size();i++) {
            String link = posts.get(i).getElementsByTag("a").first()
                    .attr("href");
            postId.add(link.substring(1, link.indexOf('%')));
        }
        return postId;
    }


    @Override
    public void saveAndAssignCategory(List<Post> posts) {
        List<String> notsavePostsList = new ArrayList<>();
        for (Post post:posts) {
            String cat = post.getPublisherGivenId().split("/")[0];

            if (cat.equals("national") ||
                    cat.equals("country") ||
                    cat.equals("others")
            ) {
                post.setCat("bangladesh");

            }else if (cat.equals("politics")) {
                post.setCat("politics");

            }else if (cat.equals("foreign")) {
                post.setCat("international");

            }else if (cat.equals("business")) {
                post.setCat("economy");

            }else if (cat.equals("columns")) {
                post.setCat("opinion");

            }else if (cat.equals("sport")) {
                post.setCat("sports");

            }else if (cat.equals("editorial")) {
                post.setCat("editorial");

            }else if (cat.equals("entertainment")) {
                post.setCat("entertainment");

            }else if (cat.equals("tech-and-gadget")) {
                post.setCat("sciTech");

            }else if (cat.equals("exile")) {
                post.setCat("aboard");

            }else if (cat.equals("campus")) {
                post.setCat("campus");

            }else if (cat.equals("lifestyle")) {
                post.setCat("lifestyle");

            }else if (cat.equals("various")) {
                post.setCat("others");
            }
            if(post.getCat() != null){
                postRepository.save(post);
                System.out.println("bangla_trubne");
            }else{
                notsavePostsList.add(post.getPublisherGivenId());
            }
        }
        UtilityClass.showStatistics("Bangla_tribune",posts,notsavePostsList);

    }

}
