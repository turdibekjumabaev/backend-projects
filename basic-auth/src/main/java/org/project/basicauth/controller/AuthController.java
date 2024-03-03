package org.project.basicauth.controller;

import org.project.basicauth.dto.request.UserRequestDTO;
import org.project.basicauth.dto.response.UserDTO;
import org.project.basicauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> registerNewUser(@RequestBody UserRequestDTO dto){
        return userService.createNewUser(dto);
    }
}
