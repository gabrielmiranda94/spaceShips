package com.w2m.spaceShips.application.dto;


import lombok.Data;

@Data
public class UpdateSpaceshipDTO {
    private String name;
    private String model;
    private String manufacturer;
    private Integer crewCapacity;
}