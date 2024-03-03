package org.project.basicauth.service;

import org.project.basicauth.dto.request.UserRequestDTO;
import org.project.basicauth.dto.response.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserService {
    ResponseEntity<UserDTO> getUserById(UUID id);
    ResponseEntity<UserDTO> getUserByUsername(String username);
    ResponseEntity<Page<UserDTO>> getAllUsers(Integer page, Integer size);
    ResponseEntity<UserDTO> createNewUser(UserRequestDTO dto);
    ResponseEntity<UserDTO> updateUserById(UUID id, UserRequestDTO dto);
    ResponseEntity<Void> deleteById(UUID id);

}
