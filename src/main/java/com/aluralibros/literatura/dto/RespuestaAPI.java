package com.aluralibros.literatura.dto;

import java.util.List;

public record RespuestaAPI(
        List<LibroDTO> results
) {
    public List<LibroDTO> resultados() { return results; }
}