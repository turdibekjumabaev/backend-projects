package org.backend.medium.service.impl;

import org.backend.medium.dto.response.FollowDTO;
import org.backend.medium.dto.response.UserDTO;
import org.backend.medium.entity.Follow;
import org.backend.medium.entity.User;
import org.backend.medium.exeption.UserNotFoundException;
import org.backend.medium.exeption.UserNotSavedException;
import org.backend.medium.mapper.FollowMapper;
import org.backend.medium.mapper.UserMapper;
import org.backend.medium.repository.FollowRepository;
import org.backend.medium.repository.UserRepository;
import org.backend.medium.security.JwtService;
import org.backend.medium.security.SecurityServices;
import org.backend.medium.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private FollowRepository followsRepository;

    @Autowired
    private SecurityServices securityServices;

    @Autowired
    private AuthenticationManager authenticationManager;


    public ResponseEntity<String> addUser(UserDTO userDto) {
        try {
            var jwt = jwtService.generateToken(userRepository.save(userMapper.toEntity(userDto)));
            return new ResponseEntity<>(jwt, HttpStatus.CREATED);
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new UserNotSavedException("database connection failed. user is not saved");
        } catch (DataIntegrityViolationException e) {
            throw new UserNotFoundException("username or email already exists");
        }
    }


    public ResponseEntity<String> signIn(String username,String password){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            var user = userRepository.findFirstByUsername(username).orElseThrow();
            var jwt = jwtService.generateToken(user);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new UserNotSavedException("database connection failed. user is not saved");
        } catch (DataIntegrityViolationException e) {
            throw new UserNotFoundException("username or email already exists");
        }
    }

    public ResponseEntity<UserDTO> getUser(Integer id) {
        try {
            return new ResponseEntity<>(userRepository.findFirstById(id).map((m) -> userMapper.toDto(m)).orElseThrow(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException("user is not found");
        }
    }

    public ResponseEntity<UserDTO> updateUser(UserDTO userDto) {
        if (userDto.getId() == null) {
            throw new UserNotFoundException("user id not found");
        } else {
            try {
                if (userRepository.existsById(userDto.getId())) {
                    User userEntity = new User();
                    userEntity.setId(userDto.getId());
                    if (userDto.getUsername() != null) userEntity.setUsername(userDto.getUsername());
                    if (userDto.getPassword() != null) userEntity.setPassword(userDto.getPassword());
                    if (userDto.getEmail() != null) userEntity.setEmail(userDto.getEmail());
                    if (userDto.getBio() != null) userEntity.setBio(userDto.getBio());
                    try {
                        return new ResponseEntity<>(userMapper.toDto(userRepository.save(userEntity)), HttpStatus.OK);
                    } catch (NoSuchElementException e) {
                        throw new UserNotFoundException("user is not found");

                    } catch (InvalidDataAccessResourceUsageException e) {
                        throw new UserNotSavedException("database connection failed. User is not updated");
                    }
                } else {
                    throw new UserNotFoundException("User is not found");
                }
            } catch (InvalidDataAccessResourceUsageException e) {
                throw new UserNotSavedException("User is not updated");
            }


        }

    }


    @Override
    public ResponseEntity<FollowDTO> followUser(Integer following) {

        User user = securityServices.getLoggedUser();
        User followingEntity = User.builder().id(following).build();

        try {
            return new ResponseEntity<>(followMapper.toDto(followsRepository.save(Follows.builder()
                    .follower(user)
                    .following(followingEntity)
                    .build())), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException("user is not found");
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new UserNotSavedException("user is not updated");
        }


    }

    @Override
    public ResponseEntity<String> unfollowUser(Integer following) {
        User user = securityServices.getLoggedUser();
        User followingEntity = User.builder().id(following).build();

        try {
            followsRepository.delete(Follow.builder()
                    .follower(user)
                    .following(followingEntity)
                    .build());
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException("user is not found");
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new UserNotSavedException("user is not unfollowed");
        }
    }


}