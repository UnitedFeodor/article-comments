package com.innowise.articlecomments.service;

import com.innowise.articlecomments.dto.article.ArticleDto;
import com.innowise.articlecomments.dto.article.GetArticleDto;
import com.innowise.articlecomments.dto.article.PostArticleDto;
import com.innowise.articlecomments.dto.comment.CommentDto;
import com.innowise.articlecomments.dto.comment.PostCommentDto;
import com.innowise.articlecomments.entity.Article;
import com.innowise.articlecomments.entity.Comment;
import com.innowise.articlecomments.exception.ResourceNotFoundException;
import com.innowise.articlecomments.mapper.ArticleMapper;
import com.innowise.articlecomments.mapper.CommentMapper;
import com.innowise.articlecomments.repository.ArticleRepository;
import com.innowise.articlecomments.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {

    // todo make sure hibernate issues like n+1 are handled
    private final ArticleMapper articleMapper;
    private final CommentMapper commentMapper;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public ArticleDto postArticle(PostArticleDto postArticleDto) {
        Article postArticle = articleMapper.postArticleDtoToArticle(postArticleDto);

        Article savedArticle = articleRepository.save(postArticle);// todo handle duplicates
        log.info("Article with id {} saved",savedArticle.getId());

        return articleMapper.articleToArticleDto(savedArticle);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public GetArticleDto getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Article with id " + id + " not found")
                );
        article.getComments();
        log.info("Article with id {} and {} comments received", article.getId(),article.getComments().size());

        // todo get all comments for it - make sure the mapper works correctly or maybe i need to write it myself
        return articleMapper.articleToGetArticleDto(article);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ArticleDto> getArticlesPage(int page, int limit) {
        List<Article> articleList = articleRepository
                .findAll(PageRequest.of(page, limit))
                .stream()
                .toList();
        log.info("Article page number {} with {} articles received", page, articleList.size());
        // todo figure out what will happen with wrong params like -1 or a billion etc.


        return articleMapper.listArticleToListArticleDto(articleList);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteArticleById(Long id) {
        if (id == null || !articleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Article with id " + id + " not found");
        }

        // The associated comments are delete with a cascade operation
        articleRepository.deleteById(id);
        log.info("Article with id {} deleted", id);
        // todo check that it's actually deleted with cascade
    }

    public CommentDto postCommentForArticle(Long articleId, PostCommentDto postCommentDto) {
        if (articleId == null || !articleRepository.existsById(articleId)) {
            throw new ResourceNotFoundException("Article with id " + articleId + " not found");
        }

        Comment comment = commentMapper.postCommentDtoToComment(postCommentDto);

        commentRepository.save(comment); //todo check that it is saved correctly for the article
        log.info("Comment with id {} posted for article with id {} saved", comment.getId(), articleId);

        return commentMapper.commentToCommentDto(comment);

    }
}
