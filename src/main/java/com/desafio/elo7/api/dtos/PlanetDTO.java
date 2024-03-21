package com.desafio.elo7.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanetDTO(@NotNull(message = "Planet Name cannot be Null") @NotBlank(message = "Planet Name cannot be Blank")
                        String name) {
}
