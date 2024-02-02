package com.innowise.articlecomments.mapper;

import com.innowise.articlecomments.dto.article.ArticleDto;
import com.innowise.articlecomments.dto.article.GetArticleDto;
import com.innowise.articlecomments.dto.article.PostArticleDto;
import com.innowise.articlecomments.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleDto articleToArticleDto(Article article);

    GetArticleDto articleToGetArticleDto(Article article);
    List<ArticleDto> listArticleToListArticleDto(List<Article> articleList);
    Article postArticleDtoToArticle(PostArticleDto postArticleDto);


}
