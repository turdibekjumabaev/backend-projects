package org.project.basicauth.mapper;

import org.mapstruct.Mapper;
import org.project.basicauth.dto.response.UserDTO;
import org.project.basicauth.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {
}
