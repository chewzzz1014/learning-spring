package com.chewzzz.contentcalender.respository;

import com.chewzzz.contentcalender.model.Content;
import com.chewzzz.contentcalender.model.Status;
import com.chewzzz.contentcalender.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {

    }

    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    @PostConstruct
    private void init() {
        // executed after dependency injection is done to perform any initialization (after DI in ContentController constructor)
        Content c = new Content(
                        1,
                        "My First Blog Post",
                        "My first blog post",
                        Status.IDEA,
                        Type.ARTICLE,
                        LocalDateTime.now(),
                        null,
                        ""
                    );
        contentList.add(c);
    }

    public void save(Content c) {
        contentList.add(c);
    }
}
