package org.backend.medium.service;

import org.backend.medium.dto.response.CommentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentsServices {
    ResponseEntity<CommentDTO> addComment(Integer article_id, CommentDTO commentsDto);
    ResponseEntity<List<CommentDTO>> getCommentsByArticleId(Integer article_id);
}