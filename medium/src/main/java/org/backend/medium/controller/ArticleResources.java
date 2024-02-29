package org.backend.medium.controller;

import org.backend.medium.dto.response.ArticleDTO;
import org.backend.medium.service.ArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("articles")
public class ArticleResources {

    @Autowired
    private ArticleServices articleServices;


    @PostMapping
    public ResponseEntity<ArticleDTO> addArticle(@RequestBody ArticleDTO ArticleDTO) {
        return articleServices.addArticle(ArticleDTO);
    }

    @PatchMapping
    public ResponseEntity<ArticleDTO> editArticle(@RequestBody ArticleDTO ArticleDTO) throws NotAllowedException {
        return articleServices.editArticle(ArticleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticleById(@PathVariable Integer id) {
        return articleServices.deleteArticleById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Integer id) {
        return articleServices.getArticleById(id);
    }

    @GetMapping
    public ResponseEntity<Page<ArticleDTO>> getAllArticles(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return articleServices.getAllArticles(page, size);
    }

    @GetMapping("/feed")
    public ResponseEntity<Page<ArticleDTO>> getArticlesOfUserFeed(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        return articleServices.getArticlesOfUserFeed(page, size);
    }

    @PostMapping("/{articleId}/like")
    public ResponseEntity<ArticleDTO> addLike(@PathVariable Integer articleId) {
        return articleServices.addLike(articleId);
    }

    @DeleteMapping("/{articleId}/like")
    public ResponseEntity<ArticleDTO> deleteLike(@PathVariable Integer articleId) {
        return articleServices.deleteLike(articleId);
    }
}