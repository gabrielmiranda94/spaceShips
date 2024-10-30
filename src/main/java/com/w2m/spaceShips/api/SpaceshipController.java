package com.w2m.spaceShips.api;

import com.w2m.spaceShips.application.dto.SpaceshipDTO;
import com.w2m.spaceShips.application.dto.UpdateSpaceshipDTO;
import com.w2m.spaceShips.application.mapper.SpaceShipMapper;
import com.w2m.spaceShips.application.ports.input.SpaceshipService;
import com.w2m.spaceShips.application.ports.output.EventProducer;
import com.w2m.spaceShips.domain.model.Spaceship;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/spaceships")
@Tag(name = "Spaceships", description = "Operations related to spaceships")
public class SpaceshipController {

    @Autowired
    private SpaceshipService spaceshipService;
    @Autowired
    private SpaceShipMapper spaceshipMapper;
    @Autowired
    private EventProducer eventProducer;

    @Operation(summary = "Get all spaceships", description = "Retrieves a list of all spaceships")
    @ApiResponse(responseCode = "200", description = "List of spaceships retrieved",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SpaceshipDTO.class)))
    @GetMapping("/get-spaceships")
    public ResponseEntity<List<SpaceshipDTO>> getAllSpaceships() {
        eventProducer.sendEvent();
        return ResponseEntity.ok(spaceshipService.findAll().stream()
                .map(spaceship -> spaceshipMapper.spaceshipToSpaceshipDTO(spaceship))
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Get spaceship by ID", description = "Retrieves a specific spaceship by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spaceship retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SpaceshipDTO.class))),
            @ApiResponse(responseCode = "404", description = "Spaceship not found")
    })
    @GetMapping("/get-spaceship-by-id/{id}")
    public ResponseEntity<SpaceshipDTO> getSpaceshipById(
            @Parameter(description = "ID of the spaceship") @PathVariable Long id) {
        eventProducer.sendEvent();
        Spaceship spaceship = spaceshipService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spaceship not found with id: " + id));
        return ResponseEntity.ok(spaceshipMapper.spaceshipToSpaceshipDTO(spaceship));
    }

    @Operation(summary = "Create a new spaceship", description = "Creates a new spaceship in the system")
    @ApiResponse(responseCode = "200", description = "Spaceship created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SpaceshipDTO.class)))
    @PostMapping("/create-spaceship")
    public ResponseEntity<SpaceshipDTO> createSpaceship(@RequestBody SpaceshipDTO spaceshipDTO) {
        eventProducer.sendEvent();
        Spaceship spaceship = spaceshipService.save(
                Spaceship.builder()
                        .name(spaceshipDTO.getName())
                        .model(spaceshipDTO.getModel())
                        .manufacturer(spaceshipDTO.getManufacturer())
                        .crewCapacity(spaceshipDTO.getCrewCapacity())
                        .build()
        );
        return ResponseEntity.ok(spaceshipMapper.spaceshipToSpaceshipDTO(spaceship));
    }

    @Operation(summary = "Update spaceship by ID", description = "Updates a specific spaceship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spaceship updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SpaceshipDTO.class))),
            @ApiResponse(responseCode = "404", description = "Spaceship not found")
    })
    @PutMapping("/update-spaceship/{id}")
    public ResponseEntity<SpaceshipDTO> updateSpaceship(
            @Parameter(description = "ID of the spaceship to update") @PathVariable Long id,
            @RequestBody SpaceshipDTO spaceshipDTO) {
        eventProducer.sendEvent();
        if (spaceshipService.findById(id).isPresent()) {
            return ResponseEntity.ok(spaceshipMapper.spaceshipToSpaceshipDTO(spaceshipService.save(
                    Spaceship.builder()
                            .id(id)
                            .name(spaceshipDTO.getName())
                            .model(spaceshipDTO.getModel())
                            .manufacturer(spaceshipDTO.getManufacturer())
                            .crewCapacity(spaceshipDTO.getCrewCapacity())
                            .build())));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Partially update spaceship", description = "Partially updates a specific spaceship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spaceship partially updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SpaceshipDTO.class))),
            @ApiResponse(responseCode = "404", description = "Spaceship not found")
    })
    @PatchMapping("/update-spaceship-partial/{id}")
    public ResponseEntity<SpaceshipDTO> updateSpaceshipPartial(
            @Parameter(description = "ID of the spaceship to partially update") @PathVariable Long id,
            @RequestBody UpdateSpaceshipDTO updateSpaceshipDTO) {
        eventProducer.sendEvent();
        try {
            Spaceship updatedSpaceship = spaceshipService.updatePartial(id, updateSpaceshipDTO);
            return ResponseEntity.ok(spaceshipMapper.spaceshipToSpaceshipDTO(updatedSpaceship));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete spaceship by ID", description = "Deletes a specific spaceship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Spaceship deleted"),
            @ApiResponse(responseCode = "404", description = "Spaceship not found")
    })
    @DeleteMapping("/delete-spaceship/{id}")
    public ResponseEntity<Void> deleteSpaceship(@Parameter(description = "ID of the spaceship to delete") @PathVariable Long id) {
        eventProducer.sendEvent();
        try {
            spaceshipService.deleteSpaceshipById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Search spaceships by name", description = "Searches for spaceships that match a specific name")
    @ApiResponse(responseCode = "200", description = "List of spaceships matching the name retrieved",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SpaceshipDTO.class)))
    @GetMapping("/search-by-param-name")
    public ResponseEntity<List<SpaceshipDTO>> findByNameParam(
            @Parameter(description = "Name parameter to search for") @RequestParam String nameParam) {
        eventProducer.sendEvent();
        return ResponseEntity.ok(spaceshipService.findByNameParam(nameParam).stream()
                .map(spaceship -> spaceshipMapper.spaceshipToSpaceshipDTO(spaceship))
                .collect(Collectors.toList()));
    }
}
