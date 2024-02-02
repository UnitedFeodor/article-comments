package com.innowise.articlecomments.service;

import com.innowise.articlecomments.dto.comment.CommentDto;
import com.innowise.articlecomments.entity.Comment;
import com.innowise.articlecomments.exception.ResourceNotFoundException;
import com.innowise.articlecomments.mapper.CommentMapper;
import com.innowise.articlecomments.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    @Transactional(propagation = Propagation.REQUIRED)
    public CommentDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Comment with id " + id + " not found")
                );
        log.info("Comment with id {}  received", id);

        return commentMapper.commentToCommentDto(comment);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCommentById(Long id) {
        if (id == null || !commentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comment with id " + id + " not found");
        }
        log.info("Comment with id {} deleted", id);

        commentRepository.deleteById(id);
    }
}
