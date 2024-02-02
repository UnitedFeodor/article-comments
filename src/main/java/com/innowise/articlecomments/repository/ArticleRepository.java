package com.innowise.articlecomments.repository;

import com.innowise.articlecomments.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
