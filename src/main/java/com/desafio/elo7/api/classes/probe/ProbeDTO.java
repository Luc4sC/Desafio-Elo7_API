package com.desafio.elo7.api.classes.probe;

import jakarta.validation.constraints.NotNull;

public record ProbeDTO(@NotNull(message = "Probe Name cannot be Null") String name, int positionInX, int positionInY) {
}
