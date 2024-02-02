package com.innowise.articlecomments.dto.comment;

// todo make sure dates are added as default value

public record PostCommentDto(
        Long articleId,
        String commenterName,
        String commentText
) {
}
