package org.project.basicauth.mapper;

import java.util.List;

public interface EntityMapper<DTO, Entity> {
    DTO toDTO(Entity entity);
    Entity toEntity(DTO dto);
    List<DTO> toDTOs(List<Entity> entities);
    List<Entity> toEntities(List<DTO> dtos);
}
