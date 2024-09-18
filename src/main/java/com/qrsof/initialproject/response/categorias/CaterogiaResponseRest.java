package com.qrsof.initialproject.response.categorias;

import com.qrsof.initialproject.response.ResponseRest;

public class CaterogiaResponseRest extends ResponseRest {
    private CategoriaResponse categoriaResponse = new CategoriaResponse();

    public CategoriaResponse getCategoriaResponse() {
        return categoriaResponse;
    }

    public void setCategoriaResponse(CategoriaResponse categoriaResponse) {
        this.categoriaResponse = categoriaResponse;
    }
}
