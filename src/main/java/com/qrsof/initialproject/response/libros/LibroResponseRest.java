package com.qrsof.initialproject.response.libros;

import com.qrsof.initialproject.response.ResponseRest;

public class LibroResponseRest extends ResponseRest {
    private LibroResponse libroResponse = new LibroResponse();

    public LibroResponse getLibroResponse() {
        return libroResponse;
    }

    public void setLibroResponse(LibroResponse libroResponse) {
        this.libroResponse = libroResponse;
    }
}
