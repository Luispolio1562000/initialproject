package com.qrsof.initialproject.response;

import com.qrsof.initialproject.model.Categoria;

import java.util.List;

public class CategoriaResponse {
    private List<Categoria> categoria;

    public List<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }
}
