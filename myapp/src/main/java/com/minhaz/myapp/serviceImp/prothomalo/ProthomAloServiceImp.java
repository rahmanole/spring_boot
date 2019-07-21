package com.minhaz.myapp.serviceImp.prothomalo;


import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.entity.Para;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.service.NewsPaperService;
import com.minhaz.myapp.service.TagService;
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
public class ProthomAloServiceImp implements NewsPaperService {

    @Autowired
    @Qualifier("prothomAloTagServiceImp")
    TagService tagService;

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> createPsot() throws Exception {
        List<Post> postList = new ArrayList();
        String publisher = "prothom_alo";
        String newPaperUrl = "https://www.prothomalo.com";
        HashSet<String> postId = findPostIds();
        postId.removeAll(postRepository.getPublisherGivenId());
        if(postId.size()<1){
            return postList;
        }
        List<HashSet<String>> catWistIdSets = getCatWistPosIdList();

        for (String id : postId) {
            Post post = new Post();

            String completeArticleUrl = newPaperUrl + id;

            post.setPublisherGivenId(id);

            assignCategory(id,post,catWistIdSets);

            post.setDateTime(new Date());
            post.setUrl(completeArticleUrl);

            Document document = Jsoup.connect(completeArticleUrl).userAgent("Opera").get();
            Element body = document.body();


            // Finding headline of the post
            Elements heading = body.getElementsByTag("h1");

            post.setHeading(heading.text());

            // ---------end-------

//            String postFeatureImgUrl = featureImgUrl(body);
//            post.setFtrImg(postFeatureImgUrl);

            post.setPublisher(publisher);
            //This portion for article paras
            Elements articleParas = body.getElementsByTag("p");
            articleParas.outerHtml();

            post.setPostBody(psotBody(articleParas,body));
            post.setFtrImg(post.getPostBody().get(0).getImgUrl());


            postList.add(post);

        }
        return postList;
    }

    //This method for finding category name from post details page
    private String getCat(Element body) throws Exception{
        String cat = body.getElementsByClass("category_name").first().attr("href").replace("/","");
        return cat;
    }

    //This for finding article body.It would be Map type
    private List<Para> psotBody(Elements articleParas,Element body) {
        List<Para> allParas = new ArrayList();
        for (int i = 0; i < articleParas.size() - 2; i++) {
            Para para = new Para();
            if (findImgUrl(articleParas.get(i)).equals("")) {
                para.setDescription(articleParas.get(i).text());
            } else {
                para.setImgUrl(findImgUrl(articleParas.get(i)));
                para.setImgCaption(findImgCaption(articleParas.get(i)));
                para.setDescription(articleParas.get(i).text());
            }


            allParas.add(para);

        }

        if(allParas.get(0).getImgUrl() == null){
            allParas.get(0).setImgUrl(featureImgUrl(body));
        }
        return allParas;
    }
    //----------end-----------


    private String featureImgUrl(Element body) {
        Elements elements = body.getElementsByClass("col_in");
        String imgWithCaption = elements.select("img").first().attr("src");
        return imgWithCaption;
    }


    //---------------Finding image URL--------
    private String findImgUrl(Element articleParas) {
        HashMap<String, String> imgUrlAndCaptin = new HashMap<String, String>();
        Element imgTag = articleParas.select("img").first();
        if (imgTag != null) {
            return imgTag.attr("src");
        } else {
            return "";
        }
    }
    //----------------end--------------

    //---------------Finding image caption--------
    private String findImgCaption(Element articleParas) {
        HashMap<String, String> imgUrlAndCaptin = new HashMap<String, String>();
        Element imgTag = articleParas.select("img").first();
        if (imgTag != null) {
            return imgTag.attr("alt");
        } else {
            return "";
        }
    }
    //----------------end--------------

    // method for finding artcile url
    @Override
    public HashSet<String> findPostIds() throws IOException {
        HashSet<String> postId = new HashSet();
        Document document = Jsoup.connect("https://www.prothomalo.com/archive").userAgent("Opera").get();
        Element body = document.body();

        Element posts = body.getElementsByClass("listing").first();
        Elements postUrls = posts.getElementsByTag("a");
        for (Element post : postUrls) {
            String link = post.attr("href");
            if (link.length() > 100)
                postId.add(link.substring(0, link.indexOf('%')));
        }
        return postId;
    }

    //This method for finding post url from specific category
    @Override
    public HashSet<String> findPostIds(String catWiseUrl) throws IOException {
        HashSet<String> postId = new HashSet<String>();
        Document document = Jsoup.connect(catWiseUrl).userAgent("Opera").get();
        Element body = document.body();
        Elements posts = body.getElementsByClass("each");
        for (int i = 0; i < 5; i++) {
            String link = posts.get(i).getElementsByTag("a").first().attr("href");
            if (link.length() > 100)
                postId.add(link.substring(0, link.indexOf('%')));
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
        return list;

    }

    public void assignCategory(String id,Post post,List<HashSet<String>>  list){
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
    }

    private String findPostCat(String id) {

        return id.split("/")[1];
    }



}
