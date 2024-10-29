package com.w2m.spaceShips.controller;


import com.w2m.spaceShips.dto.SpaceshipDTO;
import com.w2m.spaceShips.dto.UpdateSpaceshipDTO;
import com.w2m.spaceShips.mapper.SpaceshipMapper;
import com.w2m.spaceShips.domain.model.Spaceship;
import com.w2m.spaceShips.service.SpaceshipServiceImpl;
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
    public ResponseEntity<List<SpaceshipDTO>> getAllSpaceships() {
        return ResponseEntity.ok(spaceshipService.findAll().stream()
                .map(spaceship -> new SpaceshipDTO(
                        spaceship.getId(),
                        spaceship.getName(),
                        spaceship.getModel(),
                        spaceship.getManufacturer(),
                        spaceship.getCrewCapacity()))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceshipDTO> getSpaceshipById(@PathVariable Long id) {
        Spaceship spaceship = spaceshipService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spaceship not found with id: " + id));

/*
        kafkaService.produce("/spaceships/" + id, "user");
*/

        return ResponseEntity.ok(SpaceshipMapper.INSTANCE.spaceshipToSpaceshipDTO(spaceship));
    }

    @PostMapping
    public ResponseEntity<SpaceshipDTO> createSpaceship(@RequestBody SpaceshipDTO spaceshipDTO) {
        Spaceship spaceship = spaceshipService.save(
                Spaceship.builder()
                        .name(spaceshipDTO.getName())
                        .model(spaceshipDTO.getModel())
                        .manufacturer(spaceshipDTO.getManufacturer())
                        .crewCapacity(spaceshipDTO.getCrewCapacity())
                        .build()
        );
        return ResponseEntity.ok(SpaceshipMapper.INSTANCE.spaceshipToSpaceshipDTO(spaceship));

    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceshipDTO> updateSpaceship(@PathVariable Long id, @RequestBody Spaceship spaceship) {
        if (spaceshipService.findById(id).isPresent()) {
            spaceship.setId(id);
            return ResponseEntity.ok(SpaceshipMapper.INSTANCE.spaceshipToSpaceshipDTO(spaceshipService.save(spaceship)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<SpaceshipDTO> updateSpaceshipPartial(
            @PathVariable Long id,
            @RequestBody UpdateSpaceshipDTO updateSpaceshipDTO) {

        try {
            Spaceship updatedSpaceship = spaceshipService.updatePartial(id, updateSpaceshipDTO);
            return ResponseEntity.ok(SpaceshipMapper.INSTANCE.spaceshipToSpaceshipDTO(updatedSpaceship));
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