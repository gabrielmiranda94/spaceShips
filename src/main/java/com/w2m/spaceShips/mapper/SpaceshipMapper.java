package com.w2m.spaceShips.mapper;

import com.w2m.spaceShips.dto.SpaceshipDTO;
import com.w2m.spaceShips.domain.model.Spaceship;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpaceshipMapper {
    SpaceshipMapper INSTANCE = Mappers.getMapper(SpaceshipMapper.class);
    SpaceshipDTO spaceshipToSpaceshipDTO(Spaceship spaceship);
//    Spaceship spaceshipDTOToSpaceship(SpaceshipDTO spaceshipDTO);
}
