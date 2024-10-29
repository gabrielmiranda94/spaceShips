package com.w2m.spaceShips.output;

import com.w2m.spaceShips.domain.model.Spaceship;
import java.util.List;
import java.util.Optional;

public interface SpaceshipRepository {

    List<Spaceship> findByName(String name);
    Optional<Spaceship> findById(Long id);
    Spaceship save(Spaceship spaceship);
    void deleteById(Long id);
    List<Spaceship> findAll();

    List<Spaceship> findByNameContaining(String name);

    boolean existsById(Long id);

}
