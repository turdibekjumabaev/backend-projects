package org.project.basicauth.dto.request;

import lombok.*;
import org.project.basicauth.dto.response.RoleDTO;

import java.time.LocalDate;

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
    private LocalDate birth;
    private String bio;
    private RoleDTO role;
}
