package com.epi.pfa.services;

import com.epi.pfa.model.Post;
import com.epi.pfa.model.User;
import com.epi.pfa.repository.PostRepository;
import com.epi.pfa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService{

    @Autowired
    private PostRepository postRepository;
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
    @Override
    public Optional<Post> findById(long id) {
        return Optional.ofNullable(postRepository.findById(id).orElse(null));
    }
}
