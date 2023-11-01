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
        Content content = new Content(
                        1,
                        "My First Blog Post",
                        "My first blog post",
                        Status.IDEA,
                        Type.ARTICLE,
                        LocalDateTime.now(),
                        null,
                        ""
                    );
        contentList.add(content);
    }

    public void save(Content content) {
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    public boolean existsById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    public void delete(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }
}
