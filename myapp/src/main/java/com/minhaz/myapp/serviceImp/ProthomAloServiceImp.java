package com.minhaz.myapp.serviceImp;


import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.dao.TagRepository;
import com.minhaz.myapp.entity.Para;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.entity.Tag;
import com.minhaz.myapp.service.ProthomAloService;
import com.minhaz.myapp.service.TagService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ProthomAloServiceImp implements ProthomAloService {

    @Autowired
    TagService tagService;

    @Override
    public List<Post> createPsot() throws Exception {
        List<Post> postList = new ArrayList();
        String publisher = "prothom_alo";
        String newPaperUrl = "https://www.prothomalo.com";
        HashSet<String> postId = findPostIds("https://www.prothomalo.com/bangladesh");


        for (String id : postId) {
            Post post = new Post();

            String completeArticleUrl = newPaperUrl + id;

            post.setPublisherGivenId(id);
            post.setCat(findPostCat(id));
            post.setDateTime(new Date());
            post.setUrl(completeArticleUrl);

            Document document = Jsoup.connect(completeArticleUrl).get();
            Element body = document.body();


            // Finding headline of the post
            Elements heading = body.getElementsByTag("h1");

            post.setHeading(heading.text());

            // ---------end-------

            String postFeatureImgUrl = featureImgUrl(body);
            post.setFtrImg(postFeatureImgUrl);

            post.setPublisher(publisher);
            //This portion for article paras
            Elements articleParas = body.getElementsByTag("p");
            articleParas.outerHtml();

            post.setPostBody(psotBody(articleParas));

//			System.out.println("------------end------------------");
            // break;
//            tagService.assignTag(post, id);
            postList.add(post);

        }


        return postList;
    }

    //This for finding article body.It would be Map type
    private List<Para> psotBody(Elements articleParas) {
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

        Element posts = body.getElementById("widget_56292");
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

    private String findPostCat(String id) {

        return id.split("/")[1];
    }



}
