package org.project.basicauth.service.impl;

import org.project.basicauth.dto.request.UserRequestDTO;
import org.project.basicauth.dto.response.UserDTO;
import org.project.basicauth.entity.User;
import org.project.basicauth.mapper.RoleMapper;
import org.project.basicauth.mapper.UserMapper;
import org.project.basicauth.repository.UserRepository;
import org.project.basicauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<UserDTO> getUserById(UUID id) {
        try {
            if (id == null){
                return ResponseEntity.badRequest().build();
            }

            if (repository.existsById(id)){
                User user = repository.getReferenceById(id);
                return ResponseEntity.ok(mapper.toDTO(user));
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<UserDTO> getUserByUsername(String username) {
        try {
            if (username == null){
                return ResponseEntity.badRequest().build();
            }

            if (repository.existsByUsername(username)){
                User user = repository.findByUsername(username);
                return ResponseEntity.ok(mapper.toDTO(user));
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Page<UserDTO>> getAllUsers(Integer page, Integer size) {
        try {
            if (page == null || page < 0) {
                page = 0;
            }

            if (size == null || size <= 0) {
                size = 10;
            }

            Page<User> usersPage = repository.findAll(PageRequest.of(page, size));

            Page<UserDTO> userDTOPage = usersPage.map(mapper::toDTO);

            return ResponseEntity.ok(userDTOPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<UserDTO> createNewUser(UserRequestDTO dto) {
        try {
            User newUser = User.builder()
                    .id(UUID.randomUUID())
                    .firstname(dto.getFirstname())
                    .lastname(dto.getLastname())
                    .username(dto.getUsername())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .birth(dto.getBirth())
                    .bio(dto.getBio())
                    .role(roleMapper.toEntity(dto.getRole()))
                    .build();

            User savedUser = repository.save(newUser);

            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(savedUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<UserDTO> updateUserById(UUID id, UserRequestDTO dto) {
        try {
            if (repository.existsById(id)) {
                User existingUser = repository.getReferenceById(id);

                existingUser.setFirstname(dto.getFirstname());
                existingUser.setLastname(dto.getLastname());
                existingUser.setUsername(dto.getUsername());
                existingUser.setPassword(dto.getPassword());
                existingUser.setBirth(dto.getBirth());
                existingUser.setBio(dto.getBio());
                existingUser.setRole(roleMapper.toEntity(dto.getRole()));

                User updatedUser = repository.save(existingUser);

                return ResponseEntity.ok(mapper.toDTO(updatedUser));
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);

                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
