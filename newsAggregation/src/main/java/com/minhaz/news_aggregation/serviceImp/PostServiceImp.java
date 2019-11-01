package com.globalBookShop.gsb.serviceImp;

import com.globalBookShop.gsb.dao.PostRepository;
import com.globalBookShop.gsb.entity.Post;
import com.globalBookShop.gsb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
@Service
public class PostServiceImp implements PostService {
    @Autowired
    PostRepository postRepository;

    @Scheduled(fixedDelay = 60000)
    @Override
    public void savePosts() {
        try {
            for (Post post: new ProthomAloServiceImp().createPsot()) {
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
                    postRepository.save(post);
                } catch (Exception dataIntegrityViolationException) {
                    System.out.println("this post exits");
                    continue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Scheduled(fixedDelay = 1000)
    public void show(){
        for (Post post:findAllPosts() ) {
            System.out.println(post.getHeading());
            break;
        }
    }
}
