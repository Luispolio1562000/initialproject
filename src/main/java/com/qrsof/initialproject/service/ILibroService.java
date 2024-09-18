package com.qrsof.initialproject.service;

import com.qrsof.initialproject.model.Libro;
import com.qrsof.initialproject.response.libros.LibroResponseRest;
import org.springframework.http.ResponseEntity;

public interface ILibroService {
    public ResponseEntity<LibroResponseRest> obtenerLibros();
    public ResponseEntity<LibroResponseRest> obtenerLibro(Long id);
    public ResponseEntity<LibroResponseRest> crearLibro(Libro libro);
    public ResponseEntity<LibroResponseRest> actualizarLibro(Libro libro, Long id);
    public ResponseEntity<LibroResponseRest> borrarLibro(Long id);
}
