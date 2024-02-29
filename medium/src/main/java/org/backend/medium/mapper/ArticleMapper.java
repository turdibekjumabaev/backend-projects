package org.backend.medium.mapper;

import org.backend.medium.dto.response.ArticleDTO;
import org.backend.medium.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ArticleMapper implements CommonMapper<ArticleDTO, Article> {
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "tags", ignore = true)
    public abstract Article toEntity(ArticleDTO articlesDto);

    @Mapping(target = "tags", expression = "java(mapTags(article.getTags()))")
    @Mapping(target = "likes", expression = "java(mapLikes(article.getLikes()))")
    public abstract ArticleDTO toDto(Article article);

}