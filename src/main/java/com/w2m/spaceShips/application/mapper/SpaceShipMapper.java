package com.w2m.spaceShips.application.mapper;


import com.w2m.spaceShips.application.dto.SpaceshipDTO;
import com.w2m.spaceShips.domain.model.Spaceship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpaceShipMapper {

    SpaceshipDTO spaceshipToSpaceshipsDTO(Spaceship spaceship);
}