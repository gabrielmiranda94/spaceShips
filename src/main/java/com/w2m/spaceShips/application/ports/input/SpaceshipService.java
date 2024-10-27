package com.w2m.spaceShips.application.ports.input;

import com.w2m.spaceShips.adapters.api.dto.UpdateSpaceshipDTO;
import com.w2m.spaceShips.domain.model.Spaceship;

import java.util.List;

public interface SpaceshipUseCase {

    List<Spaceship> findAllSpaceships();
    Spaceship findSpaceshipById(Long id);
    Spaceship updateSpaceship(Long id, Spaceship spaceship);
    void deleteSpaceship(Long id);
    Spaceship updatePartial(Long id, UpdateSpaceshipDTO updateSpaceshipDTO); // Nuevo método

}
