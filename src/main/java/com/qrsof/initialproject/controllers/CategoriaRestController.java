package com.qrsof.initialproject.controllers;


import com.qrsof.initialproject.model.Categoria;
import com.qrsof.initialproject.response.CaterogiaResponseRest;
import com.qrsof.initialproject.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CategoriaRestController {
    @Autowired
    private ICategoriaService categoriaService;


    @GetMapping("/categorias")
    public ResponseEntity<CaterogiaResponseRest> consultarCategoria() {
        ResponseEntity<CaterogiaResponseRest> caterogiaResponseRest = categoriaService.buscarCategorias();
        return caterogiaResponseRest;
    }


    @GetMapping("categorias/{id}")
    public ResponseEntity<CaterogiaResponseRest> consultarCategoriaId(@PathVariable Long id) {
        ResponseEntity<CaterogiaResponseRest> caterogiaResponseRest = categoriaService.buscarPorId(id);
        return caterogiaResponseRest;
    }

    @PostMapping("/categorias")
    public ResponseEntity<CaterogiaResponseRest> crearCategoria(@RequestBody Categoria request) {
        ResponseEntity<CaterogiaResponseRest> caterogiaResponseRest = categoriaService.crearCategoria(request);
        return caterogiaResponseRest;
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CaterogiaResponseRest> actualizarCategoria(@RequestBody Categoria request, @PathVariable Long id) {
        ResponseEntity<CaterogiaResponseRest> caterogiaResponseRest = categoriaService.actualizarCategoria(request, id);
        return caterogiaResponseRest;
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<CaterogiaResponseRest> eliminarCategoria(@PathVariable Long id) {
      ResponseEntity<CaterogiaResponseRest> categoriaResponseRest =  categoriaService.eliminarCategoria(id);
        return categoriaResponseRest;
    }
}
