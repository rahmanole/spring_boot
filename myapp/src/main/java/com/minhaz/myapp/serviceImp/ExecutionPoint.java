package com.minhaz.myapp.serviceImp;

import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.service.NewsPaperService;
import com.minhaz.myapp.service.PostService;
import com.minhaz.myapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
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
    @Qualifier("jugantorServiceImp")
    NewsPaperService jugantorService;

    @Autowired
    @Qualifier("prothomAloServiceImp")
    NewsPaperService prothomAloService;


    @Transactional
    @Scheduled(fixedDelay = 300000)
    public void savePosts() {
        saveProthomAloPosts();
        saveJugantorPosts();
    }

    @Transactional
//    @Async("threadPoolTaskExecutor")
    public void saveProthomAloPosts() {
        System.out.println(Thread.currentThread().getName());
        try{
            prothomAloService.savePosts();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
 //   @Async("threadPoolTaskExecutor")
    public void saveJugantorPosts() {
        System.out.println(Thread.currentThread().getName());
        try{
            jugantorService.savePosts();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
