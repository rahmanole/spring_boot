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

    @Autowired
    @Qualifier("bdNews24")
    NewsPaperService bdNews24;

    @Autowired
    @Qualifier("kalerKontho")
    NewsPaperService kalerKontho;

    @Autowired
    @Qualifier("nayaDiganta")
    NewsPaperService nayaDiganta;

    @Autowired
    @Qualifier("amaderSomoy")
    NewsPaperService amaderSomoy;

    @Autowired
    @Qualifier("banglaTribune")
    NewsPaperService banglaTribune;

    @Autowired
    @Qualifier("somoKalServiceImp")
    NewsPaperService somoKalService;

    @Autowired
    @Qualifier("inqilabServiceImp")
    NewsPaperService inqilabServiceImp;


    @Transactional
    @Scheduled(fixedDelay = 300000)
    public void savePosts() {
        for(;;){
            try{
                savePosts(inqilabServiceImp);
                savePosts(banglaTribune);
                savePosts(kalerKontho);
                savePosts(somoKalService);
                savePosts(ittefaqService);
                savePosts(jugantorService);
                savePosts(nayaDiganta);
                savePosts(bdProtidinService);
                savePosts(bdNews24);
                savePosts(prothomAloService);
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

//            inqilabServiceImp.findPostIds("https://www.dailyinqilab.com/newscategory/national-news/").forEach(id->{
//                System.out.println(id);
//            });

//            postService.createPsot("inqilab",
//                    "https://www.dailyinqilab.com/article/",
//                    "h1",
//                    "article",
//                    "image_block",
//                    inqilabServiceImp.findPostIds()).forEach(post -> {
//                System.out.println(post.getPublisherGivenId());
//                System.out.println(post.getCat());
//                System.out.println(post.getFtrImg());
//                System.out.println(post.getHeading());
//                post.getPostBody().forEach(para -> {
//                    System.out.println(para.getDescription());
//                    System.out.println(para.getImgList());
//                });
//            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Transactional
    @Async("threadPoolTaskExecutor")
    public void savePosts(NewsPaperService newsPaperService) {
        System.out.println(Thread.currentThread().getName());
        try {
            newsPaperService.savePosts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
