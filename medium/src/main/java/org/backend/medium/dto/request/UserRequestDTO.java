package org.backend.medium.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String bio;
}
