package org.backend.medium.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowDTO {
    private UUID id;
    private UserDTO follower;
    private UserDTO following;
}