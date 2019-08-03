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
    @Qualifier("kalerKontho")
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

    @Autowired
    @Qualifier("bdNews24")
    NewsPaperService bdNews24;

    @Autowired
    @Qualifier("kalerKontho")
    NewsPaperService kalerKontho;


    @Transactional
    @Scheduled(fixedDelay = 300000)
    public void savePosts() {
        for(;;){
            try{
                saveProthomAloPosts();
                saveJugantorPosts();
                saveIttefaqPosts();
                saveBdPratidinPosts();
//                saveBdNews24Posts();
                saveKalerKonthoPosts();
                break;
            }catch (Exception e){
                continue;
            }
        }

    }

    @Transactional
//    @Scheduled(fixedDelay = 30000)
    public void showPostIds() {

        try {

//            kalerKontho.findPostIds().forEach(id->{
//                System.out.println(id.split("/")[1]);
//            });

            postService.createPsot("kaler_kontho",
                    "https://www.kalerkantho.com/",
                    "h2",
                    "some-class-name2",
                    "img-popup",
                    kalerKontho.findPostIds()).forEach(post -> {
                System.out.println(post.getPublisherGivenId());
                System.out.println(post.getFtrImg());
                System.out.println(post.getHeading());
                post.getPostBody().forEach(para -> {
                    System.out.println(para.getDescription());
                    System.out.println(para.getImgList());
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
    @Transactional
    @Async("threadPoolTaskExecutor")
    public void saveBdPratidinPosts() {
        System.out.println(Thread.currentThread().getName());
        try{
            bdProtidinService.savePosts();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Transactional
    @Async("threadPoolTaskExecutor")
    public void saveBdNews24Posts() {
        System.out.println(Thread.currentThread().getName());
        try{
            bdProtidinService.savePosts();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    @Async("threadPoolTaskExecutor")
    public void saveKalerKonthoPosts() {
        System.out.println(Thread.currentThread().getName());
        try{
            kalerKontho.savePosts();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
