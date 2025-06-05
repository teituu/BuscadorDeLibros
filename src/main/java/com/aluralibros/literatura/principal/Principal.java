package com.aluralibros.literatura.principal;

import com.aluralibros.literatura.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {
    private final Scanner scanner = new Scanner(System.in);
    private final LibroServicio libroServicio;

    @Autowired
    public Principal(LibroServicio libroServicio) {
        this.libroServicio = libroServicio;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== LITERALURA ===");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Buscar autores vivos en año");
            System.out.println("5. Buscar libros por idioma");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerOpcionSegura();

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> buscarAutoresVivos();
                case 5 -> buscarLibrosPorIdioma();
                case 0 -> System.out.println("\n¡Gracias por usar LITERALURA! 👋");
                default -> System.out.println("❌ Opción no válida. Por favor, seleccione una opción del 0 al 5.");
            }
        } while (opcion != 0);
    }

    private int leerOpcionSegura() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada no válida. Por favor, ingrese un número.");
            return -1; // Valor que activará el caso default
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.print("\nIngrese título del libro: ");
        String titulo = scanner.nextLine();
        libroServicio.buscarYGuardarLibro(titulo);
    }

    private void listarLibrosRegistrados() {
        System.out.println("\n=== LIBROS REGISTRADOS ===");
        libroServicio.listarLibros();
    }

    private void listarAutoresRegistrados() {
        System.out.println("\n=== AUTORES REGISTRADOS ===");
        libroServicio.listarAutores();
    }

    private void buscarAutoresVivos() {
        System.out.print("\nIngrese año: ");
        int año = leerAñoSeguro();
        if (año != -1) {
            libroServicio.buscarAutoresVivos(año);
        }
    }

    private int leerAñoSeguro() {
        try {
            String input = scanner.nextLine().trim();
            int año = Integer.parseInt(input);
            if (año < 1 || año > 2025) {
                System.out.println("❌ Por favor, ingrese un año válido (1-2025).");
                return -1;
            }
            return año;
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada no válida. Por favor, ingrese un año numérico.");
            return -1;
        }
    }

    private void buscarLibrosPorIdioma() {
        System.out.print("\nIngrese idioma (ES, EN, FR, PT): ");
        String idioma = scanner.nextLine().trim().toUpperCase();

        // Validar que el idioma sea uno de los permitidos
        if (!idioma.matches("^(ES|EN|FR|PT)$")) {
            System.out.println("❌ Idioma no válido. Use: ES, EN, FR o PT");
            return;
        }

        libroServicio.buscarLibrosPorIdioma(idioma);
    }
}