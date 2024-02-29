package org.backend.medium.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.backend.medium.dto.response.ArticleDTO;
import org.backend.medium.entity.Article;
import org.backend.medium.entity.Tag;
import org.backend.medium.entity.User;
import org.backend.medium.exeption.DatabaseException;
import org.backend.medium.exeption.NotAllowedException;
import org.backend.medium.mapper.ArticleMapper;
import org.backend.medium.repository.ArticleRepository;
import org.backend.medium.repository.UserRepository;
import org.backend.medium.security.SecurityServices;
import org.backend.medium.service.ArticleServices;
import org.backend.medium.service.TagServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleServicesImpl implements ArticleServices {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final UserRepository userRepository;
    private final TagServices tagService;
    private final SecurityServices securityServices;

    @Override
    @Transactional
    public ResponseEntity<ArticleDTO> addArticle(ArticleDTO articlesDto) {
        List<Tag> list = tagService.addTags(articlesDto.getTags().stream().map(Objects::toString).collect(Collectors.toList()));
        Article article = articleMapper.toEntity(articlesDto);
        User loggedUser = securityServices.getLoggedUser();
        article.setAuthor(loggedUser);
        article.setTags(list);

        Article savedArticle = articleRepository.save(article);
        savedArticle.setPublished_at(LocalDateTime.now());
        savedArticle.setUpdated_at(LocalDateTime.now());
        try {
            return ResponseEntity.ok()
                    .body(articleMapper.toDto(savedArticle));
        } catch (Exception e) {
            throw new DatabaseException("Error while saving article to database: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Void> deleteArticleById(Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Article> articleOptional = articleRepository.findById(id);

        if (articleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        articleRepository.deleteById(id);

        return ResponseEntity.status(200).build();
    }

    @Override
    public ResponseEntity<ArticleDTO> getArticleById(Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Article> articleOptional = articleRepository.findById(id);

        if (articleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(200).body(articleMapper.toDto(articleOptional.get()));
    }

//    @Override
//    public ResponseEntity<List<ArticlesDto>> getAllArticles() {
//        List<Article> articleOptional = articleRepository.findAll();
//        return ResponseEntity.status(200).body(articleOptional.stream().map(articleMapper::toDto).toList());
//    }

    @Override
    public ResponseEntity<Page<ArticleDTO>> getAllArticles(Integer page, Integer size) {
        size = Math.max(size, 1);
        page = Math.max(page, 0);

        Long count = articleRepository.count();

        PageRequest pageRequest = PageRequest.of((count / size) <= page ? (count % size == 0) ? (int) (count / size) - 1 : (int) (count / size) : page, size);

        pageRequest = pageRequest.withSort(Sort.by("publishDate").descending());

        Page<ArticleDTO> articles = articleRepository.findAll(pageRequest).map(articleMapper::toDto);

        return ResponseEntity.ok().body(articles);
    }

    @Override
    public ResponseEntity<ArticleDTO> editArticle(ArticleDTO articlesDto) throws NotAllowedException {
        Article article = isValidArticle(articlesDto).getBody();

        if (articlesDto.getTitle() != null) {
            article.setTitle(articlesDto.getTitle());
        }
        if (articlesDto.get() != null) {
            article.setBody(articlesDto.getBody());
        }
        if (articlesDto.getTags() != null) {
            List<Tag> tagList = tagService.addTags(articlesDto.getTags());
            article.setTags(tagList);
        }

        return ResponseEntity.ok()
                .body(articleMapper.toDto(articleRepository.save(article)));
    }

    private ResponseEntity<Article> isValidArticle(ArticleDTO articlesDto) throws NotAllowedException {
        if (articlesDto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Article> articleOptional = articleRepository.findById(articlesDto.getId());

        if (articleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Article article = articleOptional.get();

        if (!article.getAuthor().getUsername().equals(securityServices.getLoggedUser().getUsername())) {
            throw new NotAllowedException();
        }
        return ResponseEntity.ok().body(article);
    }

    @Override
    public ResponseEntity<ArticleDTO> addLike(Integer articleId) {
        User loggedUser = securityServices.getLoggedUser();
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            List<User> articleLikes = article.getLikes();
            articleLikes.add(loggedUser);
            article.setLikes(articleLikes);
            articleRepository.save(article);

            List<Article> userLikes = loggedUser.getLikes();
            userLikes.add(article);
            loggedUser.setLikes(userLikes);
            userRepository.save(loggedUser);

            return ResponseEntity.ok().body(articleMapper.toDto(article));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<ArticleDTO> deleteLike(Integer articleId) {
        User loggedUser = securityServices.getLoggedUser();
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            List<User> articleLikes = article.getLikes();
            articleLikes.remove(loggedUser);
            article.setLikes(articleLikes);
            articleRepository.save(article);

            List<Article> userLikes = loggedUser.getLikes();
            userLikes.remove(article);
            loggedUser.setLikes(userLikes);
            userRepository.save(loggedUser);

            return ResponseEntity.ok().body(articleMapper.toDto(article));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Page<ArticleDTO>> getArticlesOfUserFeed(Integer page, Integer size) {
        size = Math.max(size, 1);
//        page = Math.max(page, 0);

        User loggedUser = securityServices.getLoggedUser();

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("publish_date").descending());

        Page<ArticleDTO> articles = articleRepository.getArticlesOfUserFeed(loggedUser.getId(), pageRequest).map(articleMapper::toDto);

        return ResponseEntity.ok().body(articles);
    }
}