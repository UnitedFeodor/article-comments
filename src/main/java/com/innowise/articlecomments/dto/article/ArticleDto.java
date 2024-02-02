package com.innowise.articlecomments.dto.article;

import java.util.Date;

public record ArticleDto(
        Long id,
        String title,
        String author,
        Date publishDate
) {
}
