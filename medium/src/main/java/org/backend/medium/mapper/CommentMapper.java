package org.backend.medium.mapper;

import org.backend.medium.dto.response.CommentDTO;
import org.backend.medium.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CommentMapper implements CommonMapper<CommentDTO, Comment> {

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected ArticleMapper articleMapper;

    @Mapping(target = "author", expression = "java(userMapper.toEntity(commentsDto.getAuthor()))")
//    @Mapping(target = "article", expression = "java(articleMapper.toEntity(commentsDto.getArticle()))")
    public abstract Comment toEntity(CommentDTO commentsDto);
}