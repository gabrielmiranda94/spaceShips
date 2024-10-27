package com.w2m.spaceShips.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Spaceship {

    private Long id;
    private String name;
    private String model;
    private String manufacturer;
    private int crewCapacity;
}
