package com.epi.pfa.repository;

import com.epi.pfa.model.Post;
import com.epi.pfa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}
