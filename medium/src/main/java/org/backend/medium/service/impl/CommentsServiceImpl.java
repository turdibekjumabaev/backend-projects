package org.backend.medium.service.impl;

import org.backend.medium.dto.response.CommentDTO;
import org.backend.medium.entity.Article;
import org.backend.medium.entity.Comment;
import org.backend.medium.entity.User;
import org.backend.medium.exeption.DatabaseException;
import org.backend.medium.mapper.CommentMapper;
import org.backend.medium.repository.CommentRepository;
import org.backend.medium.repository.UserRepository;
import org.backend.medium.security.SecurityServices;
import org.backend.medium.service.CommentsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsServices {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SecurityServices securityServices;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<CommentDTO> addComment(Integer article_id, CommentDTO commentsDto) {
        User loggedUser = securityServices.getLoggedUser();
        Comment comments = commentMapper.toEntity(commentsDto);
        comments.setAuthor(loggedUser);
        comments.setArticle(Article.builder().id(article_id).build());
        try {
            return ResponseEntity
                    .ok()
                    .body(commentMapper.toDto(
                            commentRepository.save(comments)));
        } catch (Exception e) {
            throw new DatabaseException("Error while add comment: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<CommentDTO>> getCommentsByArticleId(Integer article_id) {
        return ResponseEntity
                .ok()
                .body(commentRepository.findAllByArticle_Id(article_id)
                        .stream()
                        .map(commentMapper::toDto)
                        .toList());
    }
}