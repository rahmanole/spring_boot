package com.minhaz.myapp.serviceImp;

import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Component
@Service
public class PostServiceImp implements PostService {
    @Autowired
    PostRepository postRepository;

    @Scheduled(fixedDelay = 300000)
    @Override
    public void savePosts() {
        for(;;){
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
                        dataIntegrityViolationException.printStackTrace();
                        continue;
                    }


                }
                break;
            }catch (Exception e){
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

//    @Scheduled(fixedDelay = 3000)
    @Override
    public long show(Date date){
        return (new Date().getTime()-date.getTime())/(1000*60);
    }

}
