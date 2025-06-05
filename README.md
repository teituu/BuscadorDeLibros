# 📚 LiterAlura - Catálogo de Libros

Aplicación de consola desarrollada en Spring Boot para gestionar un catálogo de libros utilizando la API de Gutendex.

## 🚀 Características

- ✅ Buscar libros por título
- ✅ Listar libros registrados
- ✅ Listar autores registrados  
- ✅ Buscar autores vivos en un año específico
- ✅ Filtrar libros por idioma
- ✅ Persistencia en base de datos PostgreSQL

## 🛠️ Tecnologías

- **Java 17+**
- **Spring Boot 3.5.0**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

## ⚙️ Configuración

### 1. Requisitos previos
- Java 17 o superior
- PostgreSQL instalado y ejecutándose
- Maven

### 2. Configuración de Base de Datos

Crea un archivo `application-dev.properties` en `src/main/resources/` con tu configuración local:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

**⚠️ IMPORTANTE: Este archivo NO debe subirse a GitHub**

### 3. Instalación

```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/literalura.git
cd literalura

# Crear la base de datos
createdb literalura

# Ejecutar la aplicación
mvn spring-boot:run --spring.profiles.active=dev
```

## 🎯 Uso

Al ejecutar la aplicación, verás un menú interactivo:

```
=== LITERALURA ===
1. Buscar libro por título
2. Listar libros registrados
3. Listar autores registrados
4. Buscar autores vivos en año
5. Buscar libros por idioma
0. Salir
```

## 🌐 API Utilizada

Este proyecto consume la [API de Gutendex](https://gutendex.com/) para obtener información de libros del Proyecto Gutenberg.

## 📝 Notas de Desarrollo

- La aplicación maneja entradas inválidas sin crashes
- Implementa validación de datos
- Usa JPA para persistencia
- Búsquedas case-insensitive para idiomas

⭐ ¡No olvides darle una estrella al proyecto si te fue útil!
