package org.backend.medium.dto.request;

import lombok.*;
import org.backend.medium.dto.response.UserDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowRequestDTO {
    private UserDTO follower;
    private UserDTO following;
}