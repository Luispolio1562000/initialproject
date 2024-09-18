package com.qrsof.initialproject.service;

import com.qrsof.initialproject.model.Categoria;
import com.qrsof.initialproject.response.categorias.CaterogiaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
    public ResponseEntity<CaterogiaResponseRest> buscarCategorias();
    public ResponseEntity<CaterogiaResponseRest> buscarPorId(Long id);
    public ResponseEntity<CaterogiaResponseRest> crearCategoria(Categoria categoria);
    public ResponseEntity<CaterogiaResponseRest> actualizarCategoria(Categoria categoria, Long id);
    public ResponseEntity<CaterogiaResponseRest> eliminarCategoria(Long id);
}
