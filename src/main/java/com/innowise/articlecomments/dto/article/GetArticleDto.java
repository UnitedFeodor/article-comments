package com.innowise.articlecomments.dto.article;

import com.innowise.articlecomments.dto.comment.CommentDto;

import java.util.Date;
import java.util.List;

public record GetArticleDto(
        Long id,
        String title,
        String content,
        String author,
        Date publishDate,
        List<CommentDto> comments
) {
}
