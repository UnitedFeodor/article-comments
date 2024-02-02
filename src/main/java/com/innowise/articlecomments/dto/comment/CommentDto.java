package com.innowise.articlecomments.dto.comment;

import java.util.Date;

public record CommentDto(
        Long id,
        Long articleId,
        String commenterName,
        String commentText,
        Date commentDate
) {
}
