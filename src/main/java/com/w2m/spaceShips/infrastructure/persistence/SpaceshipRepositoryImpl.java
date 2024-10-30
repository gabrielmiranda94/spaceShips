package com.w2m.spaceShips.infrastructure.persistence;

import com.w2m.spaceShips.domain.entity.SpaceshipEntity;
import com.w2m.spaceShips.infrastructure.repository.JpaSpaceshipRepository;
import com.w2m.spaceShips.application.ports.output.SpaceshipRepository;
import com.w2m.spaceShips.domain.model.Spaceship;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SpaceshipRepositoryImpl implements SpaceshipRepository {

    private final JpaSpaceshipRepository jpaSpaceshipRepository;

    public SpaceshipRepositoryImpl(JpaSpaceshipRepository jpaSpaceshipRepository) {
        this.jpaSpaceshipRepository = jpaSpaceshipRepository;
    }

    @Override
    public List<Spaceship> findByName(String name) {
        return jpaSpaceshipRepository.findByNameContaining(name)
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findById(Long id) {
        return jpaSpaceshipRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public Spaceship save(Spaceship spaceship) {
        SpaceshipEntity entity = mapToEntity(spaceship);
        SpaceshipEntity savedEntity = jpaSpaceshipRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        jpaSpaceshipRepository.deleteById(id);
    }

    @Override
    public List<Spaceship> findAll() {
        return jpaSpaceshipRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Spaceship> findByNameContaining(String name) {
        return jpaSpaceshipRepository.findByNameContaining(name)
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return jpaSpaceshipRepository.existsById(id);
    }

    private Spaceship mapToDomain(SpaceshipEntity entity) {
        return Spaceship.builder()
                .id(entity.getId())
                .name(entity.getName())
                .model(entity.getModel())
                .manufacturer(entity.getManufacturer())
                .crewCapacity(entity.getCrewCapacity())
                .build();
    }

    private SpaceshipEntity mapToEntity(Spaceship spaceship) {
        return SpaceshipEntity.builder()
                .id(spaceship.getId())
                .name(spaceship.getName())
                .model(spaceship.getModel())
                .manufacturer(spaceship.getManufacturer())
                .crewCapacity(spaceship.getCrewCapacity())
                .build();
    }
}
