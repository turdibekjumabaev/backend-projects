package org.backend.medium.mapper;

import org.backend.medium.dto.response.FollowDTO;
import org.backend.medium.entity.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
@Mapper(componentModel = "spring")
public abstract class FollowMapper implements CommonMapper<FollowDTO, Follow>{
    @Autowired
    protected UserMapper userMapper;


    @Mapping(target = "follower", expression = "java(userMapper.toEntity(followDto.getFollower()))")
    @Mapping(target = "following", expression = "java(userMapper.toEntity(followDto.getFollowing()))")
    public abstract Follow toEntity(FollowDTO followDto);

    @Mapping(target = "follower", expression = "java(userMapper.toDto(follows.getFollower()))")
    @Mapping(target = "following", expression = "java(userMapper.toDto(follows.getFollowing()))")
    public abstract FollowDTO toDto(Follow follows);
}