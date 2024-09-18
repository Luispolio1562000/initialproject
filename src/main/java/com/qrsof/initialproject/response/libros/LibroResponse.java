package com.qrsof.initialproject.response.libros;

import com.qrsof.initialproject.model.Libro;

import java.util.List;

public class LibroResponse {
    private List<Libro> libros;

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libro) {
        this.libros = libro;
    }
}
