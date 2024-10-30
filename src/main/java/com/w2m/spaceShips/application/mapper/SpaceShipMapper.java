package com.w2m.spaceShips.application.mapper;


import com.w2m.spaceShips.application.dto.SpaceshipDTO;
import com.w2m.spaceShips.domain.model.Spaceship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpaceShipMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "manufacturer", target = "manufacturer")
    @Mapping(source = "crewCapacity", target = "crewCapacity")
    SpaceshipDTO spaceshipToSpaceshipDTO(Spaceship spaceship);
}