package com.aluralibros.literatura.dto;

public record AutorDTO(
        String name,
        Integer birth_year,
        Integer death_year
) {
    public String nombre() { return name; }
    public Integer nacimiento() { return birth_year; }
    public Integer fallecimiento() { return death_year; }
}