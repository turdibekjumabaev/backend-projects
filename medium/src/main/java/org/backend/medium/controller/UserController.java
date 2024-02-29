package org.backend.medium.controller;

import jakarta.validation.Valid;
import org.backend.medium.dto.response.FollowDTO;
import org.backend.medium.dto.response.UserDTO;
import org.backend.medium.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/sign-up")
    private ResponseEntity<String> addUser(@Valid @RequestBody UserDTO UserDTO) {
        return userService.addUser(UserDTO);
    }

    @PostMapping("/sign-in")
    private ResponseEntity<String> signIn(@RequestParam String username,@RequestParam String password){
        return userService.signIn(username,password);
    }

    @GetMapping({"/{id}"})
    ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PatchMapping
    ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO UserDTO) {
        return userService.updateUser(UserDTO);
    }




    @PostMapping("follow/{following}")
    public ResponseEntity<FollowDTO> followUser(@PathVariable Integer following) {
        return userService.followUser(following);
    }
    @DeleteMapping("follow/{following}")
    public ResponseEntity<String> unfollowUser(@PathVariable Integer following) {
        return userService.unfollowUser(following);
    }


}