package com.w2m.spaceShips.service;
import com.w2m.spaceShips.application.service.SpaceshipServiceImpl;
import com.w2m.spaceShips.domain.model.Spaceship;
import com.w2m.spaceShips.application.ports.output.SpaceshipRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpaceshipServiceImplTest {

    @Mock
    private SpaceshipRepository spaceshipRepository;

    @InjectMocks
    private SpaceshipServiceImpl spaceshipService;

    @Test
    void testSaveSpaceship() {
        Spaceship spaceship = Spaceship.builder()
                .name("ship name")
                .model("a123")
                .manufacturer("embraer")
                .build();

        when(spaceshipRepository.save(any(Spaceship.class))).thenReturn(spaceship);

        Spaceship savedSpaceship = spaceshipService.save(spaceship);
        assertNotNull(savedSpaceship);
        assertEquals("ship name", savedSpaceship.getName());
    }
}
