package com.desafio.elo7.api.classes.planet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanetDTO(@NotNull(message = "Planet Name cannot be Null") @NotBlank(message = "Planet Name cannot be Blank")
                        String name) {
}
