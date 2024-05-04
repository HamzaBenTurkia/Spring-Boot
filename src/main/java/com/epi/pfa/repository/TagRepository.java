package com.epi.pfa.repository;

import com.epi.pfa.model.Post;
import com.epi.pfa.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByPostsIn(List<Post> posts);
}
