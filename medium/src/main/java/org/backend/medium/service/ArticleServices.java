package org.backend.medium.service;

import org.backend.medium.dto.response.ArticleDTO;
import org.backend.medium.exeption.NotAllowedException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ArticleServices {

    ResponseEntity<ArticleDTO> addArticle(ArticleDTO articlesDto);
    ResponseEntity<Void> deleteArticleById(Integer id);
    ResponseEntity<ArticleDTO> getArticleById(Integer id);
    ResponseEntity<Page<ArticleDTO>> getAllArticles(Integer page, Integer size);
    ResponseEntity<ArticleDTO> editArticle(ArticleDTO articlesDto) throws NotAllowedException;
    ResponseEntity<ArticleDTO> addLike(Integer articleId);
    ResponseEntity<ArticleDTO> deleteLike(Integer articleId);

    ResponseEntity<Page<ArticleDTO>> getArticlesOfUserFeed(Integer page, Integer size);
}