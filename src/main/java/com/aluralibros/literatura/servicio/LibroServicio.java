package com.aluralibros.literatura.servicio;

import com.aluralibros.literatura.dto.LibroDTO;
import com.aluralibros.literatura.dto.RespuestaAPI;
import com.aluralibros.literatura.modelo.Autor;
import com.aluralibros.literatura.modelo.Libro;
import com.aluralibros.literatura.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroServicio {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://gutendex.com/books?search=";

    @Autowired
    private LibroRepositorio libroRepositorio;

    public void buscarYGuardarLibro(String titulo) {
        try {
            String url = API_URL + titulo.replace(" ", "%20");
            RespuestaAPI respuesta = restTemplate.getForObject(url, RespuestaAPI.class);

            if (respuesta != null && !respuesta.resultados().isEmpty()) {
                LibroDTO libroDTO = respuesta.resultados().get(0);

                if (libroRepositorio.existsByTitulo(libroDTO.titulo())) {
                    System.out.println("\n⚠️ El libro '" + libroDTO.titulo() + "' ya está registrado!");
                    return;
                }

                Libro libro = new Libro(libroDTO);
                libroRepositorio.save(libro);

                // Mostrar información amigable
                System.out.println("\n✅ Libro registrado exitosamente!");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor(es): " + libro.getAutores().stream()
                        .map(Autor::getNombre)
                        .collect(Collectors.joining(", ")));
                System.out.println("Idioma: " + libro.getIdioma());
                System.out.println("Descargas: " + libro.getDescargas());

            } else {
                System.out.println("\n❌ No se encontraron libros con el título: " + titulo);
            }
        } catch (Exception e) {
            System.out.println("\n❌ Error al buscar el libro: " + e.getMessage());
        }
    }

    public void listarLibros() {
        List<Libro> libros = libroRepositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("\nℹ️ No hay libros registrados en la base de datos");
            return;
        }

        System.out.println("\n=== 📚 LIBROS REGISTRADOS ===");
        libros.forEach(libro -> {
            System.out.println("\nTítulo: " + libro.getTitulo());
            System.out.println("Autor(es): " + libro.getAutores().stream()
                    .map(Autor::getNombre)
                    .collect(Collectors.joining(", ")));
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Descargas: " + libro.getDescargas());
        });
        System.out.println("============================");
    }

    public void listarAutores() {
        List<Autor> autores = libroRepositorio.findDistinctAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados");
            return;
        }

        autores.forEach(autor ->
                System.out.println(autor.getNombre()));
    }

    public void buscarAutoresVivos(int año) {
        List<Autor> autores = libroRepositorio.findAutoresVivos(año);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año");
            return;
        }

        autores.forEach(autor ->
                System.out.println(autor.getNombre() +
                        " (" + autor.getNacimiento() + "-" + autor.getFallecimiento() + ")"));
    }

    public void buscarLibrosPorIdioma(String idioma) {
        List<Libro> libros = libroRepositorio.findByIdiomaIgnoreCase(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en ese idioma");
            return;
        }

        libros.forEach(libro ->
                System.out.println(libro.getTitulo() + " - " + libro.getIdioma()));
    }
}