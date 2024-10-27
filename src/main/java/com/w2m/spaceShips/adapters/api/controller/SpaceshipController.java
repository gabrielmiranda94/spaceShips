package com.w2m.spaceShips.controller;


import com.w2m.spaceShips.domain.Spaceship;
import com.w2m.spaceShips.service.SpaceshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceshipController {
    @Autowired
    private SpaceshipService spaceshipService;

    @GetMapping
    public List<Spaceship> getAllSpaceships() {
        return spaceshipService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spaceship> getSpaceshipById(@PathVariable Long id) {
        return spaceshipService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Spaceship> getSpaceshipsByName(@RequestParam String name) {
        return spaceshipService.findByName(name);
    }

    @PostMapping
    public Spaceship createSpaceship(@RequestBody Spaceship spaceship) {
        return spaceshipService.save(spaceship);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpaceship(@PathVariable Long id) {
        spaceshipService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}