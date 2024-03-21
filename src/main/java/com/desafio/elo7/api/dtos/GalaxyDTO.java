package com.desafio.elo7.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GalaxyDTO(@NotNull(message = "Galaxy Name cannot be Null") @NotBlank(message = "Galaxy Name cannot be Blank")
                        String name) {
}
