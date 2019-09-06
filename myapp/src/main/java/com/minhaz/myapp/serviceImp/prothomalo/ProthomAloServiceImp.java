package com.minhaz.myapp.serviceImp.prothomalo;


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
public class ProthomAloServiceImp  implements NewsPaperService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    public void savePosts() throws Exception{
        if(findPostIds().isEmpty()){
            System.out.println("Archieve is empty");
            return;
        }
        List<Post> postList = postService.createPsot("prothom_alo",
                "https://www.prothomalo.com/",
                "h1",
                "right_part",
                "col_in",
                findPostIds());
        List<HashSet<String>> catWisePostList = getCatWistPosIdList();
        List<String> notsavePostsList = new ArrayList<>();
        postList.forEach(post -> {
            try {
                assignCategory(post.getPublisherGivenId(),post,catWisePostList);
                if(post.getCat() != null){
                    postRepository.save(post);
                }else{
                    notsavePostsList.add(post.getPublisherGivenId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        UtilityClass.showStatistics("Prothom_alo",postList,notsavePostsList);
    }

    // method for finding artcile url
    @Override
    public HashSet<String> findPostIds() throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect("https://www.prothomalo.com/archive/").userAgent("Opera").get();
        Element body = document.body();

        Element posts = body.getElementsByClass("listing").first();
        if(posts == null){
            return postId;
        }
        Elements postUrls = posts.getElementsByTag("a");
        for (Element post : postUrls) {
            String link = post.attr("href");
                postId.add(link.substring(1, link.indexOf('%')));
        }
        return postId;
    }

    @Override
    public void saveAndAssignCategory(List<Post> posts) {

    }

    //This method for finding post url from specific category

    public HashSet<String> findPostIds(String catWiseUrl) throws IOException {
        HashSet<String> postId = new HashSet<String>();
        Document document = Jsoup.connect(catWiseUrl).userAgent("Opera").get();
        Element body = document.body();
        Elements posts = body.getElementsByClass("each");
        for (int i = 0; i < 10; i++) {
            String link = posts.get(i).getElementsByTag("a").first().attr("href");
            if (link.length() > 100)
                postId.add(link.substring(1, link.indexOf('%')));
        }
        return postId;
    }

    public List<HashSet<String>> getCatWistPosIdList() throws Exception{

        List<HashSet<String>> list = new ArrayList<>();
        list.add(findPostIds("https://www.prothomalo.com/bangladesh-politics"));
        list.add(findPostIds("https://www.prothomalo.com/bangladesh/article/"));
        list.add(findPostIds("https://www.prothomalo.com/international/article/"));
        list.add(findPostIds("https://www.prothomalo.com/economy/article"));
        list.add(findPostIds("https://www.prothomalo.com/opinion/article/"));
        list.add(findPostIds("https://www.prothomalo.com/sports/article/"));
        list.add(findPostIds("https://www.prothomalo.com/entertainment/article/"));
        list.add(findPostIds("https://www.prothomalo.com/technology/article/"));
        list.add(findPostIds("https://www.prothomalo.com/durporobash"));
        list.add(findPostIds("https://www.prothomalo.com/opinion-editorial"));
        list.add(findPostIds("https://www.prothomalo.com/institution"));
        list.add(findPostIds("https://www.prothomalo.com/chakri-bakri"));
        list.add(findPostIds("https://www.prothomalo.com/pachmisheli"));
        return list;
    }

    public void assignCategory(String id,Post post,List<HashSet<String>>  list){
        System.out.println(id);
        System.out.println(list.get(0));

        if(list.get(0).contains(id)){
            post.setCat("politics");
            return;
        }
        if(list.get(1).contains(id)){
            post.setCat("bangladesh");
            return;
        }
        if(list.get(2).contains(id)){
            post.setCat("international");
            return;
        }
        if(list.get(3).contains(id)){
            post.setCat("economy");
            return;
        }
        if(list.get(4).contains(id)){
            post.setCat("opinion");
            return;
        }
        if(list.get(5).contains(id)){
            post.setCat("sports");
            return;
        }
        if(list.get(6).contains(id)){
            post.setCat("entertainment");
            return;
        }
        if(list.get(7).contains(id)){
            post.setCat("sciTech");
            return;
        }
        if(list.get(8).contains(id)){
            post.setCat("aboard");
            return;
        }
        if(list.get(9).contains(id)){
            post.setCat("editorial");
            return;
        }
        if(list.get(10).contains(id)){
            post.setCat("campus");
            return;
        }
        if(list.get(11).contains(id)){
            post.setCat("jobs");
            return;
        }
        if(list.get(12).contains(id)){
            post.setCat("others");
            return;
        }
    }


}
