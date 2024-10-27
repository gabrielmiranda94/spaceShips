package com.w2m.spaceShips.adapters.api.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceshipDTO {
    private Long id;
    private String name;
    private String model;
    private String manufacturer;
    private int crewCapacity;
}
