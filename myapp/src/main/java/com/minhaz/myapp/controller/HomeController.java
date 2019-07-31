package com.minhaz.myapp.controller;

import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.service.PostService;
import com.minhaz.myapp.util.DateTimeConverter;
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
    DateTimeConverter dateTimeConverter;

    @GetMapping("/")
    public String home(Model model) {
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
        model.addAttribute("timeConverter", dateTimeConverter);
        model.addAttribute("postService", postService);
        return "index";
    }

    @GetMapping("/politics")
    public String politics(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("politicsPosts", postService.getPostsByCat(null, "politics", page));
        model.addAttribute("timeConverter", dateTimeConverter);
        model.addAttribute("postService", postService);
        page++;
        model.addAttribute("nextPage", page);
        return "politics";
    }

    @GetMapping("/bangladesh")
    public String bangladesh(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("bdPosts", postService.getPostsByCat(null, "bangladesh", page));
        model.addAttribute("timeConverter", dateTimeConverter);
        model.addAttribute("postService", postService);
        page++;
        model.addAttribute("nextPage", page);
        return "bangladesh";
    }

    @GetMapping("/international")
    public String international(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("intPosts", postService.getPostsByCat(null, "international", page));
        model.addAttribute("timeConverter", dateTimeConverter);
        model.addAttribute("postService", postService);
        page++;
        model.addAttribute("nextPage", page);
        return "international";
    }

    @GetMapping("/economy")
    public String economy(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("ecoPosts", postService.getPostsByCat(null, "economy", page));
        model.addAttribute("timeConverter", dateTimeConverter);
        model.addAttribute("postService", postService);
        page++;
        model.addAttribute("nextPage", page);
        return "economy";
    }

    @GetMapping("/sports")
    public String sports(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("sportsPosts", postService.getPostsByCat(null, "sports", page));
        model.addAttribute("timeConverter", dateTimeConverter);
        model.addAttribute("postService", postService);
        page++;
        model.addAttribute("nextPage", page);
        return "sports";
    }

    @GetMapping("/entertainment")
    public String entertainment(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("entPosts", postService.getPostsByCat(null, "entertainment", page));
        model.addAttribute("timeConverter", dateTimeConverter);
        model.addAttribute("postService", postService);
        page++;
        model.addAttribute("nextPage", page);
        return "entertainment";
    }

    @GetMapping("/sciTech")
    public String sciTech(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("sciTechPosts", postService.getPostsByCat(null, "sciTech", page));
        model.addAttribute("timeConverter", dateTimeConverter);
        model.addAttribute("postService", postService);
        page++;
        model.addAttribute("nextPage", page);
        return "sciTech";
    }

    @GetMapping("/editorial")
    public String editorial(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("ediPosts", postService.getPostsByCat(null, "editorial", page));
        model.addAttribute("timeConverter", dateTimeConverter);
        model.addAttribute("postService", postService);
        page++;
        model.addAttribute("nextPage", page);
        return "editorial";
    }

    @GetMapping("/opinion")
    public String opinion(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("opiPosts", postService.getPostsByCat(null, "opinion", page));
        model.addAttribute("timeConverter", dateTimeConverter);
        model.addAttribute("postService", postService);
        page++;
        model.addAttribute("nextPage", page);
        return "opinion";
    }

    @GetMapping("/aboard")
    public String aboard(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("aboardPosts", postService.getPostsByCat(null, "aboard", page));
        model.addAttribute("timeConverter", dateTimeConverter);
        model.addAttribute("postService", postService);
        page++;
        model.addAttribute("nextPage", page);
        return "aboard";
    }

    @GetMapping("/campus")
    public String campus(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("campPosts", postService.getPostsByCat(null, "campus", page));
        model.addAttribute("timeConverter", dateTimeConverter);
        model.addAttribute("postService", postService);
        page++;
        model.addAttribute("nextPage", page);
        return "campus";
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
