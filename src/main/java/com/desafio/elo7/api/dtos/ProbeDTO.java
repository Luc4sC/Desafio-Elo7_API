package com.desafio.elo7.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProbeDTO(@NotNull(message = "Probe Name cannot be Null") @NotBlank(message = "Probe Name cannot be Blank")
                       String name) {
}
