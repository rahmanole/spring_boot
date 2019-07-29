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
