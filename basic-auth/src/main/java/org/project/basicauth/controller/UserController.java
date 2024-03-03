package org.project.basicauth.controller;

import org.project.basicauth.dto.request.UserRequestDTO;
import org.project.basicauth.dto.response.UserDTO;
import org.project.basicauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        return service.getAllUsers(page, size);
    }

    @GetMapping(value = "/@{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username){
        return service.getUserByUsername(username);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id){
        return service.getUserById(id);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<UserDTO> updateUserById(@PathVariable UUID id, @RequestBody UserRequestDTO dto){
        return service.updateUserById(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id){
        return service.deleteById(id);
    }
}
