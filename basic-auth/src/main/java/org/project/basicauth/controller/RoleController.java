package org.project.basicauth.controller;

import org.project.basicauth.dto.request.RoleRequestDTO;
import org.project.basicauth.dto.response.RoleDTO;
import org.project.basicauth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/role")
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<Page<RoleDTO>> getAllRoles(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        return service.getAllRoles(page, size);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable UUID id){
        return service.getRoleById(id);
    }

    @PostMapping()
    public ResponseEntity<RoleDTO> createNewRole(@RequestBody RoleRequestDTO dto){
        return service.createNewRole(dto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteRoleById(@PathVariable UUID id){
        return service.deleteRoleById(id);
    }
}
