package com.chewzzz.contentcalender.respository;

import com.chewzzz.contentcalender.model.Content;
import java.util.List;

import com.chewzzz.contentcalender.model.Status;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContentRepository extends ListCrudRepository<Content,Integer> {

    List<Content> findAllByTitleContains(String keyword);

    @Query("SELECT * FROM Content WHERE status = :status;")
    List<Content> listByStatus(@Param("status") Status status);
}
