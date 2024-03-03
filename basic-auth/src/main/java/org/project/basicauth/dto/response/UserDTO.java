package org.project.basicauth.dto.response;

import lombok.*;

import java.time.LocalDate;
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
    private LocalDate birth;
    private String bio;
    private RoleDTO role;
}
