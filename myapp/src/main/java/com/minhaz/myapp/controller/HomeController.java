package com.minhaz.myapp.controller;

import com.minhaz.myapp.dao.PostRepository;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.service.PostService;
import com.minhaz.myapp.util.DateTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public String home(Model model, @RequestParam(defaultValue = "0") int page ){
        model.addAttribute("postList",postRepository.findAll(PageRequest.of(page, 6, Sort.by(Sort.Direction.DESC, "dateTime"))));
        model.addAttribute("timeConverter",dateTimeConverter);
        model.addAttribute("postService",postService);
        page++;
        model.addAttribute("nextPage",page);
        return "index";
    }

    @GetMapping("/post/{id}")
    public String postDetails(Model model,@PathVariable long id){
        Post post = postService.getPost(id);
        model.addAttribute("postDetails",post);
        return "post";
    }
}
