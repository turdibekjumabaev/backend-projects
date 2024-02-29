package org.backend.medium.mapper;

import org.backend.medium.dto.response.ImageDTO;
import org.backend.medium.entity.Image;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface ImageMapper extends CommonMapper<ImageDTO, Image> {

}