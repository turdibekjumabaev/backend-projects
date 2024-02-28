package org.backend.medium.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private UUID id;
    private String comment;
    private UserDTO author;
    private ArticleDTO article;
    private LocalDateTime created_at;
}