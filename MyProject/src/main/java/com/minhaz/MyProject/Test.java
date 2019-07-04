package com.minhaz.MyProject;

import com.minhaz.dao.PostRepository;
import com.minhaz.entity.Post;
import com.minhaz.serviceImp.ProthomAloServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;

@Component
public class Test {

    @Autowired
    PostRepository postRepository;

//    @Scheduled(fixedDelay = 60000)
//    public void showPosts() throws Exception {
//
//        long startTime = System.currentTimeMillis();
//        ProthomAloServiceImp palo = new ProthomAloServiceImp();
//        List<Post> posts = palo.createPsot();
//        System.out.println(posts.size());
//        for (Post post: posts) {
//            try {
//                postRepository.save(post);
//            }catch (DataIntegrityViolationException dataIntegrityViolationException){
//                System.out.println("this post exits");
//                continue;
//            }
//
//            System.out.println(post.getCat());
//            System.out.println(post.getHeading());
//            System.out.println(post.getFtrImg());
//            System.out.println(post.getUrl());
//            System.out.println(post.getDateTime());
//            System.out.println(post.getPublisher());
//
//            post.getPostBody().forEach(para -> {
//                if (para.getImgUrl() == null) {
//                    System.out.println(para.getDescription());
//                } else {
//                    System.out.println(para.getImgUrl());
//                    System.out.println(para.getImgCaption());
//                    System.out.println(para.getDescription());
//                }
//            });
//        }
//
//        System.out.println(System.currentTimeMillis() - startTime);
//    }

    @Scheduled(fixedDelay = 1000)
    public void showByCat(){
        System.out.println(postRepository.findByCat("sports").size());
    }


}
