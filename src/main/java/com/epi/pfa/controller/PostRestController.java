package com.epi.pfa.controller;

import com.epi.pfa.model.Post;
import com.epi.pfa.model.User;
import com.epi.pfa.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostRestController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public Optional<Post> findById(@PathVariable long id) {
        return postService.findById(id);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @GetMapping("/showAllPosts")
    public Collection<Post> findPosts() {
        return postService.getAllPosts();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(
            @PathVariable("id") final String id, @RequestBody final Post post) {
        return post;
    }
}
