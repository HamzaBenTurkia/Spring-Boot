package com.epi.pfa.controller;

import com.epi.pfa.model.Tag;
import com.epi.pfa.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/tag")
public class TagRestController {

    @Autowired
    private TagService tagService;

    @GetMapping("/{id}")
    public Optional<Tag> findById(@PathVariable long id) {
        return tagService.findById(id);
    }

    @PostMapping
    public Tag createUser(@RequestBody Tag tag) {
        return tagService.saveTag(tag);
    }

    @GetMapping("/showAllUsers")
    public Collection<Tag> findUsers() {
        return tagService.getAllTags();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tag updateUser(
            @PathVariable("id") final String id, @RequestBody final Tag tag) {
        return tag;
    }
}
