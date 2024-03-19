package com.desafio.elo7.api.classes.terminal;

import jakarta.validation.constraints.NotNull;

public record TerminalDTO(@NotNull(message = "Commands cannot be Null") String commands) {
}
