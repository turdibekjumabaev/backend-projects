package org.backend.medium.controller;

import org.backend.medium.dto.response.CommentDTO;
import org.backend.medium.service.CommentsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articles")
public class CommentsResources {

    @Autowired
    private CommentsServices commentsServices;

    @PostMapping("/{article_id}/comment")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Integer article_id, @RequestBody CommentDTO CommentDTO) {
        return commentsServices.addComment(article_id, CommentDTO);
    }

    @GetMapping("/{article_id}/comment")
    public ResponseEntity<List<CommentDTO>> getCommentByArticleId(@PathVariable Integer article_id) {
        return commentsServices.getCommentsByArticleId(article_id);
    }
}