package org.project.basicauth.mapper;

import org.mapstruct.Mapper;
import org.project.basicauth.dto.response.RoleDTO;
import org.project.basicauth.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
}
