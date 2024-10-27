package com.w2m.spaceShips.adapters.persistence.repository;

import com.w2m.spaceShips.adapters.persistence.entity.SpaceshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JpaSpaceshipRepository extends JpaRepository<SpaceshipEntity, Long> {

    List<SpaceshipEntity> findByNameContaining(String name);
}