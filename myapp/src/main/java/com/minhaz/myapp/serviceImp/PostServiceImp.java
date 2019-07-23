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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    PostRepository postRepository;


    @Override
    public List<Post> createPsot(String publisher,String newsPaperUrl,
                                 String htmlTagForHeading,
                                 String contentDetailsCssClass,
                                 String cssClassForFeatuteImg,
                                 HashSet<String> findPostIds) throws Exception {

        List<Post> postList = new ArrayList();

        findPostIds.removeAll(postRepository.getPublisherGivenId(publisher));

        if(findPostIds.size()<1){
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
            Elements heading = body.getElementsByTag(htmlTagForHeading);

            post.setHeading(heading.text());

            // ---------end-------

//            String postFeatureImgUrl = featureImgUrl(body);
//            post.setFtrImg(postFeatureImgUrl);

            post.setPublisher(publisher);
            //This portion for article paras
            Elements articleParas = body.getElementsByClass(contentDetailsCssClass).first().getElementsByTag("p");
            articleParas.outerHtml();

            post.setPostBody(psotBody(articleParas,body,cssClassForFeatuteImg,publisher));
            post.setFtrImg(featureImgUrl(body,cssClassForFeatuteImg,publisher));


            postList.add(post);

        }
        return postList;
    }

    @Override
    public List<Para> psotBody(Elements articleParas,
                               Element body,
                               String cssClassForFeatuteImg,
                               String publisher) {

        List<Para> allParas = new ArrayList();
        for (int i = 0; i < articleParas.size(); i++) {
            Para para = new Para();
            para.setDescription(articleParas.get(i).text());
            findImgOrVdo(articleParas.get(i),para,publisher);
            allParas.add(para);
        }

        if(allParas.get(0).getImgList().size() == 0){
            System.out.println("kkkk");
            Set<Img> imgSet = new HashSet<>();
            Img img = new Img();
            img.setImgUrl(featureImgUrl(body,cssClassForFeatuteImg,publisher));
            imgSet.add(img);
            allParas.get(0).setImgList(imgSet);

//            for (Img img:allParas.get(0).getImgList()) {
//                img.setImgUrl(featureImgUrl(body,cssClassForFeatuteImg,publisher));
//                System.out.println(featureImgUrl(body,cssClassForFeatuteImg,publisher));
//                break;
//            }
        }
        return allParas;
    }

    @Override
    public HashSet<Img> findImgOrVdo(Element articlePara,Para para,String publisher) {
        HashSet<Img> imgList = new HashSet<>();
        HashSet<Vdo> vdoList = new HashSet<>();

        Elements imgTags = articlePara.select("img");
        Elements vdoTags = articlePara.getElementsByTag("iframe");

        if(imgTags != null){
            for (Element imgTag:imgTags) {
                Img img = new Img();
                if(publisher.equals("jugantor")){
                    img.setImgUrl("https://www.jugantor.com" + imgTag.attr("src"));
                }
                img.setImgCaption(imgTag.attr("alt"));
                imgList.add(img);
            }
            para.setImgList(imgList);
        }

        if(vdoTags != null){
            for (Element vdoTag:vdoTags) {
                Vdo vdo = new Vdo();
                vdo.setVdoUrl(vdoTag.attr("src"));
                vdo.setVdoCaption(vdoTag.attr("src"));
                vdoList.add(vdo);
            }
        }

        return imgList;
    }

    @Override
    public String featureImgUrl(Element body,String cssClassForFeatuteImg,String publisher) {
        Elements elements = body.getElementsByClass(cssClassForFeatuteImg);
        String imgWithCaption = "";

        if(publisher.equals("jugantor")){
            imgWithCaption = "https://www.jugantor.com" + elements.select("img").first().attr("src");
        }
        return imgWithCaption;
    }


    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAllByOrderByDateTimeDesc();
    }

    @Override
    public String getPulisherLogo(String publisher) {
        switch (publisher) {
            case "prothom_alo":
                return "https://paloimages.prothom-alo.com/contents/themes/public/style/images/Prothom-Alo.png";
            case "bd_protidin":
                return "https://www.bd-pratidin.com/assets/importent_images/main_logo.gif";
            case "kaler_kontho":
                return "https://www.kalerkantho.com/assets/site/img/logo.png";
            case "jugantor":
                return "https://www.jugantor.com/templates/jugantor-v2/images/logo_main.png?v=1";
            case "ittefaq":
                return "https://www.ittefaq.com.bd/templates/desktop-v1/images/main-logo.png";
            case "somokal":
                return "https://samakal.com/assets/images/logo-bn.png";
            case "amader_somoy":
                return "http://www.dainikamadershomoy.com/files/assets/img/main-logo.png";
            case "inquilab":
                return "https://www.dailyinqilab.com/includes/themes/dailyinqilab/images/logo.png";
            case "bhorer_kagoj":
                return "https://www.bhorerkagoj.com/wp-content/uploads/2018/11/Bhorer-Kagoj-logo-1.png";
            case "bdnews24":
                return "https://d30fl32nd2baj9.cloudfront.net/media/2013/01/04/logo1.png1/BINARY/logo1.png";
            case "bbc_bangla":
                return "http://eqbal.info/wp-content/uploads/2017/10/Logo_BBC_Bangla-768x432.png";
            default:
                return "";

        }
    }

    @Override
    public Post getPost(long id) {
        return postRepository.getOne(id);
    }

}
