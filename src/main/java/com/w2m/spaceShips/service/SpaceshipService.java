package com.w2m.spaceShips.service;

import com.w2m.spaceShips.domain.Spaceship;
import com.w2m.spaceShips.repositiry.SpaceshipRepository;
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
}
