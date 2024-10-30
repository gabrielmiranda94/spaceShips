package com.w2m.spaceShips.application.mapper;

import com.w2m.spaceShips.application.dto.SpaceshipDTO;
import com.w2m.spaceShips.domain.model.Spaceship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperShip {
    SpaceshipDTO spaceshipToSpaceshipDTO(Spaceship spaceship);

}
