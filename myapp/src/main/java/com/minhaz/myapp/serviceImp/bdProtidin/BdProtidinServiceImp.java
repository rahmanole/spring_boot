package com.minhaz.myapp.serviceImp.bdProtidin;


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
//        List<HashSet<String>> catWisePostList = getCatWistPosIdList();

        postList.forEach(post -> {
            try {
                assignCategory(post.getPublisherGivenId(), post);
                if(post.getCat() != null){
                    postRepository.save(post);
                }
                System.out.println("bd_pratidin");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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

    //This method for finding post url from specific category
    @Override
    public HashSet<String> findPostIds(String catWiseUrl) throws IOException {
        HashSet<String> postId = new HashSet<String>();
        Document document = Jsoup.connect(catWiseUrl).userAgent("Opera").get();
        Element body = document.body();

        Elements posts = body.getElementsByClass("lead-news").first().getElementsByTag("a");

        for (Element post : posts) {
            String link = post.attr("href");
            postId.add(link);
        }

        posts = body.getElementsByClass("lead-news-2nd").first().getElementsByTag("a");

        for (Element post : posts) {
            String link = post.attr("href");
            postId.add(link);
        }

        posts = body.getElementsByClass("lead-news-3nd").first().getElementsByTag("a");

        for (Element post : posts) {
            String link = post.attr("href");
            postId.add(link);

            if (postId.size() == 5)
                break;
        }

        return postId;
    }

    @Override
    public void assignCategory(String id,Post post){
        String cat = id.split("/")[0];

        if (cat.equals("national") ||
                cat.equals("country") ||
                cat.equals("dengue-update") ||
                cat.equals("city-news") ||
                cat.equals("chittagong-pratidin") ||
                cat.equals("city-roundup") ||
                cat.equals("chayer-desh")) {
            post.setCat("bangladesh");
            return;
        }
        if (cat.equals("international-news") || id.equals("kolkata")) {
            post.setCat("international");
            return;
        }
        if (cat.equals("corporate-corner")) {
            post.setCat("economy");
            return;
        }
        if (cat.equals("facebook") || id.equals("readers-column")) {
            post.setCat("opinion");
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
        if (cat.equals("tech-world")) {
            post.setCat("sciTech");
            return;
        }
        if (cat.equals("probash-potro")) {
            post.setCat("aboard");
            return;
        }
        if (cat.equals("campus-online")) {
            post.setCat("campus");
            return;
        }
        if (cat.equals("job-market")) {
            post.setCat("jobs");
            return;
        }
        if (cat.equals("mixter")) {
            post.setCat("others");
            return;
        }
    }

    @Override
    public List<HashSet<String>> getCatWistPosIdList() throws Exception {

        List<HashSet<String>> list = new ArrayList<>();
//        list.add(findPostIds("https://www.bd-pratidin.com/national"));
//        list.add(findPostIds("https://www.bd-pratidin.com/country"));
//        list.add(findPostIds("https://www.bd-pratidin.com/city-news"));
//        list.add(findPostIds("https://www.bd-pratidin.com/chittagong-pratidin"));
//        list.add(findPostIds("https://www.bd-pratidin.com/chayer-desh"));
//        list.add(findPostIds("https://www.bd-pratidin.com/city-roundup"));
//        list.add(findPostIds("https://www.bd-pratidin.com/international-news"));
//        list.add(findPostIds("https://www.bd-pratidin.com/kolkata"));
        list.add(findPostIds("https://www.bd-pratidin.com/corporate-corner"));
        list.add(findPostIds("https://www.bd-pratidin.com/facebook"));
        list.add(findPostIds("https://www.bd-pratidin.com/readers-column"));
        list.add(findPostIds("https://www.bd-pratidin.com/sports"));
        list.add(findPostIds("https://www.bd-pratidin.com/entertainment"));
        list.add(findPostIds("https://www.bd-pratidin.com/tech-world"));
        list.add(findPostIds("https://www.bd-pratidin.com/probash-potro"));
        list.add(findPostIds("https://www.bd-pratidin.com/campus-online"));
        list.add(findPostIds("https://www.bd-pratidin.com/job-market"));
        list.add(findPostIds("https://www.bd-pratidin.com/mixter"));
        return list;
    }

    @Override
    public void assignCategory(String id, Post post, List<HashSet<String>> list) {
        if (list.get(0).contains(id) ||
                list.get(1).contains(id) ||
                list.get(2).contains(id) ||
                list.get(3).contains(id) ||
                list.get(4).contains(id) ||
                list.get(5).contains(id)) {
            post.setCat("bangladesh");
            return;
        }
        if (list.get(6).contains(id) || list.get(7).contains(id)) {
            post.setCat("international");
            return;
        }
        if (list.get(8).contains(id)) {
            post.setCat("economy");
            return;
        }
        if (list.get(9).contains(id) || list.get(10).contains(id)) {
            post.setCat("opinion");
            return;
        }
        if (list.get(11).contains(id)) {
            post.setCat("sports");
            return;
        }

        if (list.get(12).contains(id)) {
            post.setCat("entertainment");
            return;
        }
        if (list.get(13).contains(id)) {
            post.setCat("sciTech");
            return;
        }
        if (list.get(14).contains(id)) {
            post.setCat("aboard");
            return;
        }
        if (list.get(15).contains(id)) {
            post.setCat("campus");
            return;
        }
        if (list.get(16).contains(id)) {
            post.setCat("jobs");
            return;
        }
        if (list.get(17).contains(id)) {
            post.setCat("others");
            return;
        }
    }
}
