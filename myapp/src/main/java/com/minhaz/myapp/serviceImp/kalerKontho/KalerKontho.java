package com.minhaz.myapp.serviceImp.kalerKontho;


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
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class KalerKontho implements NewsPaperService {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;


    public void savePosts() throws Exception{
        List<Post> postList = postService.createPsot("kaler_kontho",
                "https://www.kalerkantho.com/",
                "h2",
                "some-class-name2",
                "img-popup",
                findPostIds());
        saveAndAssignCategory(postList);
    }



    // method for finding artcile url
    @Override
    public HashSet<String> findPostIds() throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect("https://www.kalerkantho.com/online/entertainment").userAgent("Opera").get();
        Element body = document.body();
        Element latestNews = body.getElementById("myTabContent");
        Elements posts = latestNews.getElementsByTag("li");
        for (int i=0;i<20;i++) {
            String link = posts.get(i).getElementsByTag("a").first()
                    .attr("href");
            postId.add(link.substring(2));
        }
        return postId;
    }


    public void saveAndAssignCategory(List<Post> posts) {
        List<String> notsavePostsList = new ArrayList<>();
        for (Post post:posts) {
            String cat = post.getPublisherGivenId().split("/")[1];

            if (cat.equals("national") || cat.equals("country-news")) {
                post.setCat("bangladesh");
            }else if (cat.equals("world")) {

                post.setCat("international");

            }else if (cat.equals("business")) {

                post.setCat("economy");

            }else if (cat.equals("sport")) {

                post.setCat("sports");

            }else if (cat.equals("entertainment")) {

                post.setCat("entertainment");

            }else if (cat.equals("info-tech")) {

                post.setCat("sciTech");

            }else if (cat.equals("miscellaneous")) {

                post.setCat("others");

            }

            if(post.getCat() != null){
                postRepository.save(post);
                System.out.println("Kalerkontho");
            }else{
                notsavePostsList.add(post.getPublisherGivenId());
            }
        }
        UtilityClass.showStatistics("Kalerkontho",posts,notsavePostsList);
    }
}
