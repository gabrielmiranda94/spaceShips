package com.w2m.spaceShips.application.service;
import com.w2m.spaceShips.application.dto.UpdateSpaceshipDTO;
import com.w2m.spaceShips.application.ports.input.SpaceshipService;
import com.w2m.spaceShips.application.ports.output.EventProducer;
import com.w2m.spaceShips.application.ports.output.SpaceshipRepository;
import com.w2m.spaceShips.domain.model.Spaceship;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceshipServiceImpl implements SpaceshipService {
    @Autowired
    private  SpaceshipRepository spaceshipRepository;

    @Override
    public Optional<Spaceship> findById(Long id) {

        return spaceshipRepository.findById(id);
    }

    @Cacheable("spaceships")
    @Override
    public List<Spaceship> findAll() {
        return spaceshipRepository.findAll();
    }

    @Override
    public Spaceship save(Spaceship spaceship) {
        return spaceshipRepository.save(spaceship);
    }

    @Override
    public Spaceship updateSpaceship(Long id, Spaceship spaceship) {
        Optional<Spaceship> existingSpaceship = spaceshipRepository.findById(id);
        if (existingSpaceship.isPresent()) {
            spaceship.setId(id);
            return spaceshipRepository.save(spaceship);
        } else {
            throw new EntityNotFoundException("Spaceship not found with id: " + id);
        }
    }

    @Override
    public Spaceship updatePartial(Long id, UpdateSpaceshipDTO updateSpaceshipDTO) {
        Optional<Spaceship> existingSpaceshipOptional = findById(id);

        if (existingSpaceshipOptional.isPresent()) {
            Spaceship existingSpaceship = existingSpaceshipOptional.get();

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

    @Override
    public List<Spaceship> findByNameParam(String nameParam) {
        return spaceshipRepository.findByNameContaining(nameParam);
    }

    @Override
    public void deleteSpaceshipById(Long id) {

            if (spaceshipRepository.existsById(id)) {
                spaceshipRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("Spaceship not found with id: " + id);
            }


    }

}

