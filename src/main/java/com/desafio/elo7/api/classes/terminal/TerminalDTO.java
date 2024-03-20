package com.desafio.elo7.api.classes.terminal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TerminalDTO(@NotNull(message = "Commands cannot be Null") @NotBlank(message = "Commands cannot be Blank")
                          String commands) {
}
