package com.w2m.spaceShips.application.ports.input;

import com.w2m.spaceShips.adapters.api.dto.UpdateSpaceshipDTO;
import com.w2m.spaceShips.domain.model.Spaceship;

import java.util.List;
import java.util.Optional;

public interface SpaceshipService {

    List<Spaceship> findAll();
    Optional<Spaceship> findById(Long id);
    Spaceship save(Spaceship spaceship);

    Spaceship updateSpaceship(Long id, Spaceship spaceship);
    void deleteSpaceshipById(Long id);
    Spaceship updatePartial(Long id, UpdateSpaceshipDTO updateSpaceshipDTO);

}
