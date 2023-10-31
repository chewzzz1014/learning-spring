package com.chewzzz.contentcalender.controller;

import com.chewzzz.contentcalender.model.Content;
import com.chewzzz.contentcalender.respository.ContentCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository repository;

    // @Autowired is implicit annotated
    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Content> findAll() {
        return repository.findAll();
    }

}
