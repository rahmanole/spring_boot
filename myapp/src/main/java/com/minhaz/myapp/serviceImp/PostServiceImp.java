package com.minhaz.myapp.serviceImp;

import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.dao.TagRepository;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.service.PostService;
import com.minhaz.myapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@Service
public class PostServiceImp implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    TagService tagService;

    @Transactional
    @Scheduled(fixedDelay = 300000)
    @Override
    public void savePosts() {
        for (; ; ) {
            try {
                for (Post post : new ProthomAloServiceImp().createPsot()) {
                    tagService.assignTag(post,post.getPublisherGivenId());
                    try {
                        System.out.println(post.getCat());
                        System.out.println(post.getHeading());
                        System.out.println(post.getFtrImg());
                        System.out.println(post.getUrl());
                        System.out.println(post.getDateTime());
                        System.out.println(post.getPublisher());

                        post.getPostBody().forEach(para -> {
                            if (para.getImgUrl() == null) {
                                System.out.println(para.getDescription());
                            } else {
                                System.out.println(para.getImgUrl());
                                System.out.println(para.getImgCaption());
                                System.out.println(para.getDescription());
                            }
                        });
                        post.getTags().forEach(tag-> System.out.print(tag.getTagName()+","));
                        postRepository.save(post);
                    } catch (Exception dataIntegrityViolationException) {
                        System.out.println("Post already exists!1");
                        continue;
                    }
                }
                break;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                continue;
            }
        }

    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAllByOrderByDateTimeDesc();
    }

//    @Autowired
//    TagRepository tagRepository;
//    @Scheduled(fixedDelay = 3000)
//    public void show() {
//       postRepository.getPublisherGivenId().forEach(id->{
//           System.out.println(id);
//       });
//    }

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

}
