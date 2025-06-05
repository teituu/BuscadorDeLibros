package com.aluralibros.literatura.dto;

import java.util.List;

public record LibroDTO(
        String title,
        List<AutorDTO> authors,
        List<String> languages,
        Long download_count
) {
    public String titulo() { return title; }
    public List<AutorDTO> autores() { return authors; }
    public List<String> idiomas() { return languages; }
    public Long descargas() { return download_count; }
}