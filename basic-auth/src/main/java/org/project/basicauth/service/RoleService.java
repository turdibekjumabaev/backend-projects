package org.project.basicauth.service;

import org.project.basicauth.dto.request.RoleRequestDTO;
import org.project.basicauth.dto.response.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface RoleService {
    ResponseEntity<RoleDTO> getRoleById(UUID id);
    ResponseEntity<Page<RoleDTO>> getAllRoles(Integer page, Integer size);
    ResponseEntity<RoleDTO> createNewRole(RoleRequestDTO dto);
    ResponseEntity<Void> deleteRoleById(UUID id);
}
