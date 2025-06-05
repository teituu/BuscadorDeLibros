package com.aluralibros.literatura.repositorio;

import com.aluralibros.literatura.modelo.Autor;
import com.aluralibros.literatura.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    boolean existsByTitulo(String titulo);

    @Query("SELECT DISTINCT a FROM Libro l JOIN l.autores a")
    List<Autor> findDistinctAutores();

    @Query("SELECT DISTINCT a FROM Libro l JOIN l.autores a WHERE a.nacimiento <= :año AND (a.fallecimiento >= :año OR a.fallecimiento IS NULL)")
    List<Autor> findAutoresVivos(@Param("año") int año);

    List<Libro> findByIdiomaIgnoreCase(String idioma);

    @Query("SELECT l FROM Libro l WHERE UPPER(l.idioma) = UPPER(:idioma)")
    List<Libro> findByIdiomaCustom(@Param("idioma") String idioma);
}