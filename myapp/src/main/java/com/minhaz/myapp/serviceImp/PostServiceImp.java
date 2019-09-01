package com.minhaz.myapp.serviceImp;

import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.entity.Img;
import com.minhaz.myapp.entity.Para;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.entity.Vdo;
import com.minhaz.myapp.service.PostService;
import com.minhaz.myapp.service.NewsPaperService;
import com.minhaz.myapp.service.TagService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class PostServiceImp implements PostService {

    @Autowired
    PostRepository postRepository;


    @Override
    public List<Post> createPsot(String publisher, String newsPaperUrl,
                                 String htmlTagForHeading,
                                 String contentDetailsCssClass,
                                 String cssClassForFeatuteImg,
                                 HashSet<String> findPostIds) throws Exception {

        List<Post> postList = new ArrayList();

        findPostIds.removeAll(postRepository.getPublisherGivenId(publisher));

        if (findPostIds.size() < 1) {
            return postList;
        }

        for (String id : findPostIds) {
            Post post = new Post();

            String completeArticleUrl = newsPaperUrl + id;

            post.setPublisherGivenId(id);

            post.setDateTime(new Date());
            post.setUrl(completeArticleUrl);

            Document document = Jsoup.connect(completeArticleUrl).userAgent("Opera").get();
            Element body = document.body();


            // Finding headline of the post
            if (publisher.equals("kaler_kontho")) {
                Element heading = body.getElementsByClass("details").first().
                        getElementsByTag(htmlTagForHeading).first();
                post.setHeading(heading.text());
            }else if (publisher.equals("bangla_tribune")) {
                Element heading = body.getElementsByClass("detail_article").first().
                        getElementsByTag(htmlTagForHeading).first();
                post.setHeading(heading.text());
            }else if (publisher.equals("samakal")) {
                Element heading = body.getElementsByClass("detail-top").first().
                        getElementsByTag(htmlTagForHeading).first();
                post.setHeading(heading.text());
            }else if (publisher.equals("inqilab")) {
                Element heading = body.getElementsByTag("article").first().
                        getElementsByTag(htmlTagForHeading).first();
                post.setHeading(heading.text());
            } else {
                Element heading = body.getElementsByTag(htmlTagForHeading).first();
                post.setHeading(heading.text());
            }


            // ---------end-------

//            String postFeatureImgUrl = featureImgUrl(body);
//            post.setFtrImg(postFeatureImgUrl);

            post.setPublisher(publisher);
            //This portion for article paras
            Elements articleParas;
            if(publisher.equals("inqilab")){
                articleParas = body.getElementsByTag(contentDetailsCssClass);
            }else {
                articleParas = body.getElementsByClass(contentDetailsCssClass).first().getElementsByTag("p");

            }

            articleParas.outerHtml();
            Img ftrImg = featureImgUrl(body, cssClassForFeatuteImg, publisher);

            List<Para> postBody = postBody(articleParas, publisher);

            if (postBody.get(0).getImgList().size() == 0) {
                Set<Img> imgList = new HashSet<>();
                imgList.add(ftrImg);
                postBody.get(0).setImgList(imgList);
            }
            post.setPostBody(postBody);

            post.setFtrImg(featureImgUrl(body, cssClassForFeatuteImg, publisher).getImgUrl());


            postList.add(post);

        }
        return postList;
    }

    @Override
    public List<Para> postBody(Elements articleParas,
                               String publisher) {

        List<Para> allParas = new ArrayList();

        for (int i = 0; i < articleParas.size(); i++) {
            Para para = new Para();
            if (articleParas.get(i).text().contains("আরো পড়ুন")) {
                continue;
            }

            para.setDescription(articleParas.get(i).text());
            findImgOrVdo(articleParas.get(i), para, publisher);
            allParas.add(para);
        }
        //Sometimes articles dont have any description they just use images
        //in this case I need to do thw following otherwise will get exception
        //at line 89
        if (articleParas.size() == 0) {
            Para para = new Para();
            Set<Img> imgList = new HashSet<>();
            para.setImgList(imgList);
            allParas.add(para);
        }
        return allParas;
    }

    @Override
    public void postSave(List<Post> posts) {

    }

    @Override
    public void findImgOrVdo(Element articlePara, Para para, String publisher) {
        Set<Img> imgList = new HashSet<>();
        List<Vdo> vdoList = new ArrayList<>();

        Elements imgTags = articlePara.select("img");
        Elements vdoTags = articlePara.getElementsByTag("iframe");

        if (imgTags != null) {
            for (Element imgTag : imgTags) {
                Img img = new Img();

                img.setImgCaption(imgTag.attr("alt"));

                if (publisher.equals("jugantor")) {
                    img.setImgUrl("https://www.jugantor.com" + imgTag.attr("src"));

                } else if (publisher.equals("bd_pratidin")) {
                    img.setImgUrl("https://www.bd-pratidin.com" + imgTag.attr("src").substring(1));

                } else if (publisher.equals("kaler_kontho")) {
                    img.setImgUrl("https://www.kalerkantho.com/" + imgTag.attr("src").substring(1));
                } else {
                    img.setImgUrl(imgTag.attr("src"));
                }
                imgList.add(img);
            }
            para.setImgList(imgList);
        } else {
            para.setImgList(imgList);
        }

        if (vdoTags != null) {
            for (Element vdoTag : vdoTags) {
                Vdo vdo = new Vdo();
                vdo.setVdoUrl(vdoTag.attr("src"));
                vdo.setVdoCaption(vdoTag.attr("src"));
                vdoList.add(vdo);
            }
            para.setVdoList(vdoList);
        }

    }

    @Override
    public Img featureImgUrl(Element body, String cssClassForFeatuteImg, String publisher) {
        Elements elements = body.getElementsByClass(cssClassForFeatuteImg);
        Img img = new Img();
        String imgUrl = "";
        String caption = "";

        if (elements.select("img").first() != null) {

            if (publisher.equals("jugantor")) {

                imgUrl = "https://www.jugantor.com" + elements.select("img").first().attr("src");
                caption = elements.select("img").first().attr("alt");

                img.setImgUrl(imgUrl);
                img.setImgCaption(caption);
            } else if (publisher.equals("ittefaq")) {

                imgUrl = "https://www.ittefaq.com.bd" + elements.select("img").first().attr("src");
                caption = elements.select("img").first().attr("alt");

                img.setImgUrl(imgUrl);
                img.setImgCaption(caption);

            } else if (publisher.equals("bd_pratidin")) {
                //As this vendors img url start with dot(.) thats why i use subString() method to remove the dot(.)
                imgUrl = "https://www.bd-pratidin.com" + elements.select("img").first().attr("src").substring(1);
                caption = elements.select("img").first().attr("alt");

                img.setImgUrl(imgUrl);
                img.setImgCaption(caption);

            } else if (publisher.equals("kaler_kontho")) {
                if (elements.select("img").first() != null) {
                    imgUrl = elements.select("img").first().attr("src");
                    caption = elements.select("img").first().attr("alt");
                } else {
                    System.out.println("This post dont have any feature image");
                    //Here we can set our logo as default feature img
                    //if any post dont have any feature imgage
                }
                img.setImgUrl(imgUrl);
                img.setImgCaption(caption);
            } else {
                imgUrl = elements.select("img").first().attr("src");
                caption = elements.select("img").first().attr("alt");

                img.setImgUrl(imgUrl);
                img.setImgCaption(caption);
            }
        } else {
            System.out.println("This post dont have any feature image");
            //Here we can set our logo as default feature img
            //if any post dont have any feature imgage
        }
        return img;
    }

    @Override
    public Page<Post> getPostsByCatForPostPage(String catName, long postId) {
        List<Post> postsForPostPage = postRepository
                .findAllByCat(PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, "dateTime")), catName)
                .getContent()
                .stream().collect(Collectors.toList());
        Post post = getPost(postId);
        int i = postsForPostPage.indexOf(post);
        System.out.println(postsForPostPage.size());
        System.out.println(i);
        postsForPostPage.remove(post);
        Page<Post> posts = new PageImpl<>(postsForPostPage);
        return posts;
    }


    @Override
    public String getPulisherLogo(String publisher) {
        switch (publisher) {
            case "prothom_alo":
                return "/img/logo_prothom_alo.png";
            case "bd_pratidin":
                return "/img/logo_bd_pratidin.gif";
            case "kaler_kontho":
                return "/img/logo_kaler_kontho.png";
            case "jugantor":
                return "/img/logo_jugantor.png";
            case "ittefaq":
                return "/img/logo_ittefaq.png";
            case "samakal":
                return "/img/logo_Samakal.png";
            case "amader_somoy":
                return "/img/logo_amader_shomoy.png";
            case "inquilab":
                return "/img/logo_inquilab.png";
            case "bdnews24":
                return "/img/logo_bdnews24.png";
            case "naya_diganta":
                return "/img/logo_naya_diganta.png";
            case "bangla_tribune":
                return "/img/logo_bangla_tribune.png";
            case "inqilab":
                return "/img/logo_inquilab.png";
            default:
                return "";
        }
    }

    @Override
    public Post getPost(long id) {
        return postRepository.getOne(id);
    }

    @Override
    public Page<Post> getPostsByCat(Pageable pageable, String catName) {
        return postRepository.findAllByCat(PageRequest.of(0, 6, Sort.by(Sort.Direction.DESC, "dateTime")), catName);
    }



    @Override
    public Page<Post> getPostsByCat(Pageable pageable, String catName, int page) {
        return postRepository.findAllByCat(PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "dateTime")), catName);
    }

    @Override
    public Page<Post> getAllPosts(int page) {
        return postRepository.findAll(PageRequest.of(page,20, Sort.Direction.DESC,"dateTime"));
    }
}
