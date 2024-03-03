package org.project.basicauth.service.impl;

import org.project.basicauth.dto.request.RoleRequestDTO;
import org.project.basicauth.dto.response.RoleDTO;
import org.project.basicauth.entity.Role;
import org.project.basicauth.mapper.RoleMapper;
import org.project.basicauth.repository.RoleRepository;
import org.project.basicauth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    @Autowired
    private RoleMapper mapper;

    @Override
    public ResponseEntity<RoleDTO> getRoleById(UUID id) {
        try {
            if (id == null){
                return ResponseEntity.badRequest().build();
            }

            if (repository.existsById(id)){
                Role role = repository.getReferenceById(id);
                return ResponseEntity.ok(mapper.toDTO(role));
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Page<RoleDTO>> getAllRoles(Integer page, Integer size) {
        try {
            if (page == null || page < 0) {
                page = 0;
            }

            if (size == null || size <= 0) {
                size = 10;
            }

            Page<Role> rolesPage = repository.findAll(PageRequest.of(page, size));
            Page<RoleDTO> roleDTOPage = rolesPage.map(mapper::toDTO);

            return ResponseEntity.ok(roleDTOPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<RoleDTO> createNewRole(RoleRequestDTO dto) {
        try {
            Role role = Role.builder()
                    .id(UUID.randomUUID())
                    .role(dto.getRole())
                    .build();

            Role savedRole = repository.save(role);

            return ResponseEntity.ok(mapper.toDTO(savedRole));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteRoleById(UUID id) {
        try {
            if (id == null){
                return ResponseEntity.badRequest().build();
            }

            if (repository.existsById(id)){
                repository.deleteById(id);

                return ResponseEntity.ok().build();
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
