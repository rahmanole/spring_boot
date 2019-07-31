package com.minhaz.myapp.serviceImp;

import com.minhaz.myapp.service.NewsPaperService;
import com.minhaz.myapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
public class ExecutionPoint {
    @Autowired
    PostService postService;



    @Autowired
    @Qualifier("jugantorServiceImp")
    NewsPaperService jugantorService;

    @Autowired
    @Qualifier("prothomAloServiceImp")
    NewsPaperService prothomAloService;

    @Autowired
    @Qualifier("ittefaqServiceImp")
    NewsPaperService ittefaqService;

    @Autowired
    @Qualifier("bdProtidinServiceImp")
    NewsPaperService bdProtidinService;


//    @Transactional
//    @Scheduled(fixedDelay = 300000)
//    public void savePosts() {
//
//        for(;;){
//            try{
//                saveProthomAloPosts();
//                saveJugantorPosts();
//                saveIttefaqPosts();
//                break;
//            }catch (Exception e){
//                continue;
//            }
//        }
//
//    }

    @Transactional
    @Scheduled(fixedDelay = 60000)
    public void showPostIds() {

        try {

            postService.createPsot("bd_pratidin",
                    "https://www.bd-pratidin.com/",
                    "h1",
                    "container-left-area",
                    "main-image",
                    bdProtidinService.findPostIds()).forEach(post -> {
                        System.out.println(post.getHeading());
                        System.out.println(post.getFtrImg());
                        post.getPostBody().forEach(para -> {
                            System.out.println(para.getDescription());
                        });
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }





    @Transactional
    @Async("threadPoolTaskExecutor")
    public void saveProthomAloPosts() {
        System.out.println(Thread.currentThread().getName());
        try{
            prothomAloService.savePosts();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    @Async("threadPoolTaskExecutor")
    public void saveJugantorPosts() {
        System.out.println(Thread.currentThread().getName());
        try{
            jugantorService.savePosts();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    @Async("threadPoolTaskExecutor")
    public void saveIttefaqPosts() {
        System.out.println(Thread.currentThread().getName());
        try{
            ittefaqService.savePosts();
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
