package com.minhaz.myapp.serviceImp;

import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.service.NewsPaperService;
import com.minhaz.myapp.service.PostService;
import com.minhaz.myapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Service
public class ExecutionPoint {
    @Autowired
    PostRepository postRepository;

    @Autowired
    @Qualifier("prothomAloTagServiceImp")
    TagService prothomAloTagService;

    @Autowired
    @Qualifier("prothomAloServiceImp")
    NewsPaperService prothomAloService;

    @Autowired
    @Qualifier("jugantorServiceImp")
    NewsPaperService jugantorServiceImp;

//    @Transactional
//    @Scheduled(fixedDelay = 300000)
//    @Override
//    public void savePAloPosts() {
//        long strtTime = System.currentTimeMillis();
//        for (; ; ) {
//            try {
//                for (Post post : prothomAloService.createPsot()) {
//                    try {
//                        System.out.println(post.getCat());
//                        System.out.println(post.getHeading());
//                        System.out.println(post.getFtrImg());
//                        System.out.println(post.getUrl());
//                        System.out.println(post.getDateTime());
//                        System.out.println(post.getPublisher());
//
////                        post.getPostBody().forEach(para -> {
////                            if (para.getImgUrl() == null) {
////                                System.out.println(para.getDescription());
////                            } else {
////                                System.out.println(para.getImgUrl());
////                                System.out.println(para.getImgCaption());
////                                System.out.println(para.getDescription());
////                            }
////                        });
//                        post.getTags().forEach(tag-> System.out.print(tag.getTagName()+","));
//                        postRepository.save(post);
//                    } catch (Exception dataIntegrityViolationException) {
//                        System.out.println("Post already exists!1");
//                        continue;
//                    }
//                }
//                break;
//            } catch (Exception e) {
//                e.printStackTrace();
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//                continue;
//            }
//        }
//
//        System.out.println("========="+(System.currentTimeMillis()-strtTime)+"===========");
//
//    }


    @Transactional
    @Scheduled(fixedDelay = 300000)
    public void saveJugantorPosts() {
        jugantorServiceImp.savePosts();

    }



}
