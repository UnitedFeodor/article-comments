package com.innowise.articlecomments.repository;

import com.innowise.articlecomments.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
