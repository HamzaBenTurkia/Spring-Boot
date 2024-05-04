package com.epi.pfa.controller;

import com.epi.pfa.model.Post;
import com.epi.pfa.model.Tag;
import com.epi.pfa.repository.PostRepository;
import com.epi.pfa.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/addPost")
    public String showAddPostForm(Model model) {
        model.addAttribute("post", new Tag());
        return "add-post";
    }

    @PostMapping("/save")
    public String createPost(@RequestParam("content") String content) {
        Post post = new Post();
        post.setContent(content);
        postRepository.save(post);
        return "redirect:/posts/showAllPosts";
    }

    @GetMapping("/showAllPosts")
    public String showAllPosts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "show-all-posts";
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }
}
