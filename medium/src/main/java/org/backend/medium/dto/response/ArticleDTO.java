package org.backend.medium.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private UUID id;
    private String title;
    private String content;
    private UserDTO author;
    private List<TagDTO> tags;
    private List<UserDTO> likes;
    private LocalDateTime published_at;
    private LocalDateTime updated_at;
}
