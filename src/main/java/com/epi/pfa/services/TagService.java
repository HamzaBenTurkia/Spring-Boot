package com.epi.pfa.services;

import com.epi.pfa.model.Tag;
import com.epi.pfa.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService implements ITagService{

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
    @Override
    public Tag saveTag(Tag tag) {
        return null;
    }

    @Override
    public Tag getTagById(Long id) {
        return null;
    }

    @Override
    public Optional<Tag> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Tag updateTag(Long tagId, Tag updatedTag) {
        Tag existingTag = tagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("Tag not found with ID: " + tagId));
        existingTag.setName(updatedTag.getName());

        return tagRepository.save(existingTag);
    }
}
