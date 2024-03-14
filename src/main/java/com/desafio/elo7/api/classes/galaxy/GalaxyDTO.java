package com.desafio.elo7.api.classes.galaxy;

import jakarta.validation.constraints.NotNull;

public record GalaxyDTO(@NotNull(message = "Galaxy Name cannot be Null") String name) {
}
