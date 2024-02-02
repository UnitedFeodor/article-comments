package com.innowise.articlecomments.mapper;

import com.innowise.articlecomments.dto.comment.CommentDto;
import com.innowise.articlecomments.dto.comment.PostCommentDto;
import com.innowise.articlecomments.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto commentToCommentDto(Comment comment);
    Comment postCommentDtoToComment(PostCommentDto postCommentDto);
}
