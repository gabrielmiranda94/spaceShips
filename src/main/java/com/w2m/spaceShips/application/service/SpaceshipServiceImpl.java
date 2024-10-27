package com.w2m.spaceShips.application.service;

import com.w2m.spaceShips.adapters.api.dto.UpdateSpaceshipDTO;
import com.w2m.spaceShips.domain.model.Spaceship;
import com.w2m.spaceShips.application.ports.output.SpaceshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceshipService {
    @Autowired
    private  SpaceshipRepository spaceshipRepository;

    @Cacheable("spaceships")
    public List<Spaceship> findAll() {
        return spaceshipRepository.findAll();
    }

    public Optional<Spaceship> findById(Long id) {
        return spaceshipRepository.findById(id);
    }

    public List<Spaceship> findByName(String name) {
        return spaceshipRepository.findByNameContaining(name);
    }

    public Spaceship save(Spaceship spaceship) {
        return spaceshipRepository.save(spaceship);
    }

    public void deleteById(Long id) {
        spaceshipRepository.deleteById(id);
    }

    @Override
    public Spaceship updatePartial(Long id, UpdateSpaceshipDTO updateSpaceshipDTO) {
            Optional<Spaceship> existingSpaceshipOptional = findById(id);

            if (existingSpaceshipOptional.isPresent()) {
                Spaceship existingSpaceship = existingSpaceshipOptional.get();

                // Actualiza los campos que no son nulos
                if (updateSpaceshipDTO.getName() != null) {
                    existingSpaceship.setName(updateSpaceshipDTO.getName());
                }
                if (updateSpaceshipDTO.getModel() != null) {
                    existingSpaceship.setModel(updateSpaceshipDTO.getModel());
                }
                if (updateSpaceshipDTO.getManufacturer() != null) {
                    existingSpaceship.setManufacturer(updateSpaceshipDTO.getManufacturer());
                }
                if (updateSpaceshipDTO.getCrewCapacity() != null) {
                    existingSpaceship.setCrewCapacity(updateSpaceshipDTO.getCrewCapacity());
                }

                return save(existingSpaceship);
            } else {
                throw new EntityNotFoundException("Spaceship not found with id: " + id);
            }
    }

}
