package com.epi.pfa.controller;

import com.epi.pfa.model.Tag;
import com.epi.pfa.model.User;
import com.epi.pfa.repository.TagRepository;
import com.epi.pfa.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/addTag")
    public String showAddTagForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "add-tag";
    }

    @PostMapping("/save")
    public String createTag(@RequestParam("name") String tagName) {
        Tag tag = new Tag();
        tag.setName(tagName);
        tagRepository.save(tag);
        return "redirect:/tags/showAllTags";
    }

    @GetMapping("/showAllTags")
    public String showAllTags(Model model) {
        List<Tag> tags = tagRepository.findAll();
        model.addAttribute("tags", tags);
        return "show-all-tags";
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    @PutMapping("/tags/{id}")
    public Tag updateTag(@PathVariable Long id, @RequestBody Tag updatedTag) {
        return tagService.updateTag(id, updatedTag);
    }
}
