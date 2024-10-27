package com.w2m.spaceShips.adapters.api.controller;


import com.w2m.spaceShips.adapters.api.dto.SpaceshipDTO;
import com.w2m.spaceShips.adapters.api.dto.UpdateSpaceshipDTO;
import com.w2m.spaceShips.domain.model.Spaceship;
import com.w2m.spaceShips.application.service.SpaceshipServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceshipController {
    @Autowired
    private SpaceshipServiceImpl spaceshipService;

    @GetMapping
    public List<SpaceshipDTO> getAllSpaceships() {
        return spaceshipService.findAll().stream()
                .map(spaceship -> new SpaceshipDTO(
                        spaceship.getId(),
                        spaceship.getName(),
                        spaceship.getModel(),
                        spaceship.getManufacturer(),
                        spaceship.getCrewCapacity()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SpaceshipDTO getSpaceshipById(@PathVariable Long id) {
        Spaceship spaceship = spaceshipService.findById(id).get();
        return new SpaceshipDTO(spaceship.getId(), spaceship.getName(), spaceship.getModel(), spaceship.getManufacturer(), spaceship.getCrewCapacity());
    }

    @PostMapping
    public SpaceshipDTO createSpaceship(@RequestBody SpaceshipDTO spaceshipDTO) {
        Spaceship spaceship = spaceshipService.save(
                Spaceship.builder()
                        .name(spaceshipDTO.getName())
                        .model(spaceshipDTO.getModel())
                        .manufacturer(spaceshipDTO.getManufacturer())
                        .crewCapacity(spaceshipDTO.getCrewCapacity())
                        .build()
        );
        return new SpaceshipDTO(spaceship.getId(), spaceship.getName(), spaceship.getModel(), spaceship.getManufacturer(), spaceship.getCrewCapacity());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Spaceship> updateSpaceship(@PathVariable Long id, @RequestBody Spaceship spaceship) {
        if (spaceshipService.findById(id).isPresent()) {
            spaceship.setId(id);
            return ResponseEntity.ok(spaceshipService.save(spaceship));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Spaceship> updateSpaceshipPartial(
            @PathVariable Long id,
            @RequestBody UpdateSpaceshipDTO updateSpaceshipDTO) {

        try {
            Spaceship updatedSpaceship = spaceshipService.updatePartial(id, updateSpaceshipDTO);
            return ResponseEntity.ok(updatedSpaceship);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpaceship(@PathVariable Long id) {
        try {
            spaceshipService.deleteSpaceshipById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}