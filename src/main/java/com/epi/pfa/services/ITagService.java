package com.epi.pfa.services;

import com.epi.pfa.model.Post;
import com.epi.pfa.model.Tag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ITagService {
    List<Tag> getAllTags();

    Tag saveTag(Tag tag);
    Tag getTagById(Long id);
    Optional<Tag> findById(long id);
    Tag updateTag(Long tagId, Tag updatedTag);
}
