package com.w2m.spaceShips.infrastructure.repository;

import com.w2m.spaceShips.domain.entity.SpaceshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JpaSpaceshipRepository extends JpaRepository<SpaceshipEntity, Long> {

    List<SpaceshipEntity> findByNameContaining(String name);
}