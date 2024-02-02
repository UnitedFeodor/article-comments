package com.innowise.articlecomments.controller;

import com.innowise.articlecomments.dto.comment.CommentDto;
import com.innowise.articlecomments.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;




    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
        CommentDto comment = commentService.getCommentById(id);

        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long id) {
        commentService.deleteCommentById(id);

        return ResponseEntity.noContent().build();
    }




}
