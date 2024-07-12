package com.example.jdbc_template_tut.bookmark;

import java.time.Instant;

public record Bookmark(
        Long id,
        String title,
        String url,
        Instant createdAt
) {
}
