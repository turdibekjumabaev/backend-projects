package org.backend.medium.dto.request;

import lombok.*;
import org.backend.medium.dto.response.TagDTO;
import org.backend.medium.dto.response.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequestDTO {
    private String title;
    private String content;
    private UserDTO author;
    private List<TagDTO> tags;
    private List<UserDTO> likes;
    private LocalDateTime published_at;
    private LocalDateTime updated_at;
}