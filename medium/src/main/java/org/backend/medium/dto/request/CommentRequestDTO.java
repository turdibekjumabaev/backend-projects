package org.backend.medium.dto.request;

import lombok.*;
import org.backend.medium.dto.response.ArticleDTO;
import org.backend.medium.dto.response.UserDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDTO {
    private String comment;
    private UserDTO author;
    private ArticleDTO article;
}