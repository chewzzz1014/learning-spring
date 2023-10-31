package com.chewzzz.contentcalender.controller;

import com.chewzzz.contentcalender.model.Content;
import com.chewzzz.contentcalender.respository.ContentCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository repository;

    // @Autowired is implicit annotated
    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatusCode.valueOf(404), "Content Not Found")
        );
    }

    @PostMapping("")
    public void create(@RequestBody Content content) {
        System.out.println(content.toString());
        repository.save(content);
    }
}
