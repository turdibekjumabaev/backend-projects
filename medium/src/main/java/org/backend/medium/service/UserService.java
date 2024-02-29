package org.backend.medium.service;

import org.backend.medium.dto.response.FollowDTO;
import org.backend.medium.dto.response.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<String> addUser(UserDTO userDto);

    ResponseEntity<String> signIn(String username, String password);

    ResponseEntity<UserDTO> getUser(Integer id);

    ResponseEntity<UserDTO> updateUser(UserDTO userDto);

    ResponseEntity<FollowDTO> followUser(Integer following);

    ResponseEntity<String> unfollowUser(Integer following);

}