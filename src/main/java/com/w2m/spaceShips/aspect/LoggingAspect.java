package com.w2m.spaceShips.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.w2m.spaceShips.controller.SpaceshipController.getSpaceshipById(..)) && args(id)")
    public void logNegativeId(Long id) {
        if (id < 0) {
            System.out.println("Attempt to retrieve a spaceship with a negative ID: " + id);
        }
    }
}
