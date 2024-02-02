package com.innowise.articlecomments.controller;

import com.innowise.articlecomments.dto.article.ArticleDto;
import com.innowise.articlecomments.dto.article.GetArticleDto;
import com.innowise.articlecomments.dto.article.PostArticleDto;
import com.innowise.articlecomments.dto.comment.CommentDto;
import com.innowise.articlecomments.dto.comment.PostCommentDto;
import com.innowise.articlecomments.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {
    //TODO add validation to dtos

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleDto> postArticle(
            @RequestBody PostArticleDto postArticleDto
    ) {
        ArticleDto articleDto = articleService.postArticle(postArticleDto);

        URI uri = URI.create(
                String.format("/api/v1/articles/%d", articleDto.id())
        );
        return ResponseEntity.created(uri).body(articleDto);
    }

    // TODO pagination for comments i guess?
    @GetMapping("/{id}")
    public ResponseEntity<GetArticleDto> getArticleById(
            @PathVariable Long id
    ) { //TODO handle null params
        GetArticleDto articleDto = articleService.getArticleById(id);
        return ResponseEntity.ok().body(articleDto); // TODO handle not found
    }

    @GetMapping
    public ResponseEntity<List<ArticleDto>> getArticlesPage(
            @RequestParam(required = false) int page,  //TODO handle null params
            @RequestParam(required = false) int limit
    ) {
        List<ArticleDto> articleDtoList = articleService.getArticlesPage(page, limit);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticleById(
            @PathVariable Long id
    ) {
        articleService.deleteArticleById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{articleId}/comments")
    public ResponseEntity<CommentDto> postCommentForArticle(
            @PathVariable Long articleId,
            @RequestBody PostCommentDto postCommentDto
    ) {
        CommentDto commentDto = articleService.postCommentForArticle(articleId, postCommentDto);

        URI uri = URI.create(
                String.format("/api/v1/articles/%d/comments/%d", articleId, commentDto.id())
        );
        return ResponseEntity.created(uri).body(commentDto);
    }

}
//
//    добавить статью добавить коммент к ней редактировать статью просмотреть список всех доступных статей
//
//        форма для создания статьи страница для просмотра статьи и комментов страница для всего списка статей с пагинацией без секьюрити
//
//        фронт на реакте
//
//        2 недели
//
//        *мгновенное отображение комментариев для остальных юзеров