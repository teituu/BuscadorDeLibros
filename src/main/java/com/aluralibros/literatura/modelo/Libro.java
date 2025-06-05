package com.aluralibros.literatura.modelo;

import com.aluralibros.literatura.dto.LibroDTO;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    private String idioma;
    private Long descargas;

    // Constructor sin parámetros requerido por JPA
    public Libro() {
    }

    // Constructor para DTO
    public Libro(LibroDTO libroDTO) {
        this.titulo = libroDTO.titulo();
        this.idioma = libroDTO.idiomas().get(0);
        this.descargas = libroDTO.descargas();
        this.autores = libroDTO.autores().stream()
                .map(Autor::new)
                .toList();
    }

    // Getters
    public String getTitulo() { return titulo; }
    public List<Autor> getAutores() { return autores; }
    public String getIdioma() { return idioma; }
    public Long getDescargas() { return descargas; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setDescargas(Long descargas) {
        this.descargas = descargas;
    }
}