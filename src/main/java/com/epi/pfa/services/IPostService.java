package com.epi.pfa.services;

import com.epi.pfa.model.Post;
import com.epi.pfa.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface IPostService {
    Post savePost(Post post);
    Post getPostById(Long id) ;
    Optional<Post> findById(long id);
}
