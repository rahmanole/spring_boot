package com.minhaz.MyProject;

import com.minhaz.dao.PostRepository;
import com.minhaz.entity.Post;
import com.minhaz.serviceImp.ProthomAloServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class Test {

    @Scheduled(fixedDelay = 10000)
    public void showPosts() throws Exception {

        long startTime = System.currentTimeMillis();
        ProthomAloServiceImp palo = new ProthomAloServiceImp();
        List<Post> posts = palo.createPsot();
        System.out.println(posts.size());
        posts.forEach(post -> {

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

        });

        System.out.println(System.currentTimeMillis() - startTime);
    }

}
