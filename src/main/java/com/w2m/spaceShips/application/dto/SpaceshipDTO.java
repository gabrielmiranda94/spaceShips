package com.w2m.spaceShips.application.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceshipDTO {

    private String name;
    private String model;
    private String manufacturer;
    private int crewCapacity;
}
