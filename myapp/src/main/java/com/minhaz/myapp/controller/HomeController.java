package com.minhaz.myapp.controller;

import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.service.PostService;
import com.minhaz.myapp.util.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UtilityClass utilityClass;

    @GetMapping("/")
    public String home(Model model,@RequestParam(defaultValue = "0") int page) {
        model.addAttribute("allPosts", postService.getAllPosts(page));
        model.addAttribute("utilityClass", utilityClass);
        model.addAttribute("postService", postService);
        page++;
        model.addAttribute("nextPage", page);
        return "index";
    }

    @GetMapping("/categorised")
    public String categorised(Model model) {
        model.addAttribute("politicsPosts", postService.getPostsByCat(null, "politics"));
        model.addAttribute("bdPosts", postService.getPostsByCat(null, "bangladesh"));
        model.addAttribute("intPosts", postService.getPostsByCat(null, "international"));
        model.addAttribute("ecoPosts", postService.getPostsByCat(null, "economy"));
        model.addAttribute("sportsPosts", postService.getPostsByCat(null, "sports"));
        model.addAttribute("entPosts", postService.getPostsByCat(null, "entertainment"));
        model.addAttribute("sciTechPosts", postService.getPostsByCat(null, "sciTech"));
        model.addAttribute("ediPosts", postService.getPostsByCat(null, "editorial"));
        model.addAttribute("opiPosts", postService.getPostsByCat(null, "opinion"));
        model.addAttribute("aboardPosts", postService.getPostsByCat(null, "aboard"));
        model.addAttribute("campPosts", postService.getPostsByCat(null, "campus"));
        model.addAttribute("timeConverter", utilityClass);
        model.addAttribute("postService", postService);
        return "catWise";
    }

    @GetMapping("/news/{cat}")
    public String getAllNewsByCat(Model model, @RequestParam(defaultValue = "0") int page,@PathVariable String cat) {
        model.addAttribute("catWiseAllNews", postService.getPostsByCat(null, cat, page));
        model.addAttribute("utilityClass", utilityClass);
        model.addAttribute("postService", postService);
        model.addAttribute("catName", UtilityClass.getCatNameInBangla(cat));
        model.addAttribute("catNameInEng", cat);
        page++;
        model.addAttribute("nextPage", page);
        return "newsOfSingleCat";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
//    @GetMapping("/")
//    public String home(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam String cat ){
//        model.addAttribute("postList",postService.getPostsByCat(null,cat));
//        model.addAttribute("timeConverter",dateTimeConverter);
//        model.addAttribute("postService",postService);
//        page++;
//        model.addAttribute("nextPage",page);
//        return "index";
//    }

    @GetMapping("/post/{id}")
    public String postDetails(Model model, @PathVariable long id) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "post";
    }
}
