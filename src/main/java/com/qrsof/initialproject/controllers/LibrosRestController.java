package com.qrsof.initialproject.controllers;

import com.qrsof.initialproject.model.Libro;
import com.qrsof.initialproject.response.libros.LibroResponseRest;
import com.qrsof.initialproject.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libros")
public class LibrosRestController {
   @Autowired
   private ILibroService libroService;

    @GetMapping("/")
    public ResponseEntity<LibroResponseRest> obtenerLibros() {
        ResponseEntity<LibroResponseRest> response = libroService.obtenerLibros();
        return response;
    }


    @GetMapping("/{id}")
    public ResponseEntity<LibroResponseRest> obtenerLibroId(@PathVariable Long id) {
        ResponseEntity<LibroResponseRest> response = libroService.obtenerLibro(id);
        return response;
    }
    @PostMapping("/crear")
    public ResponseEntity<LibroResponseRest> crearLibro(@RequestBody Libro request) {
        ResponseEntity<LibroResponseRest> response = libroService.crearLibro(request);
        return response;
    }
}
