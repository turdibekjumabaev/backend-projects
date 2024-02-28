package org.backend.medium.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String bio;
}
