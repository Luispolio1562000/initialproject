package com.qrsof.initialproject.service;


import com.qrsof.initialproject.model.Categoria;
import com.qrsof.initialproject.model.dao.ICategoriaDao;
import com.qrsof.initialproject.response.categorias.CaterogiaResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);


    @Autowired
    private ICategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CaterogiaResponseRest> buscarCategorias() {
        log.info("Inicio Buscando categorias");
        CaterogiaResponseRest caterogiaResponseRest = new CaterogiaResponseRest();


        try {
            List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
            caterogiaResponseRest.getCategoriaResponse().setCategoria(categoria);
            caterogiaResponseRest.setMetadata("Respuesta Ok", "200", "Respuesta exitosa");
        } catch (Exception e) {
            caterogiaResponseRest.setMetadata("Respuesta Error", "500", "Error al buscar categorias");
            log.error("Error al consultar categorias");
            e.getStackTrace();
            return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            log.info("Fin Buscando categorias");
        }
        return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CaterogiaResponseRest> buscarPorId(Long id) {
        log.info("Inicio Buscando categoria por ID");
        CaterogiaResponseRest caterogiaResponseRest = new CaterogiaResponseRest();
        List<Categoria> categoria = new ArrayList<>();
        try {
            Optional<Categoria> categoriaOptional = categoriaDao.findById(id);
            if (categoriaOptional.isPresent()) {
                log.info("Categoria encontrada");
                categoria.add(categoriaOptional.get());
                caterogiaResponseRest.getCategoriaResponse().setCategoria(categoria);
                caterogiaResponseRest.setMetadata("Respuesta Ok", "200", "Respuesta exitosa");
            } else {
                log.info("Categoria no encontrada");
                caterogiaResponseRest.setMetadata("Respuesta Error", "404", "Categoria no encontrada");
                return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error al buscar categoria", e.getMessage());
            caterogiaResponseRest.setMetadata("Respuesta Error", "404", "Categoria no encontrada");
            return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CaterogiaResponseRest> crearCategoria(Categoria categoria) {
        log.info("Inicio creando categoria");
        CaterogiaResponseRest caterogiaResponseRest = new CaterogiaResponseRest();
        List<Categoria> categoriaList = new ArrayList<>();
        try {
            Categoria categoriaGuardada = categoriaDao.save(categoria);

            if (categoriaGuardada != null) {
                categoriaList.add(categoriaGuardada);
                caterogiaResponseRest.getCategoriaResponse().setCategoria(categoriaList);
            } else {
                log.error("Categoria no creada");
                caterogiaResponseRest.setMetadata("Respuesta Error", "404", "Categoria no creada");
                return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            log.error("Error al crear categoria", e);
            caterogiaResponseRest.setMetadata("Respuesta Error", "500", "Error al crear categoria");
            return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        caterogiaResponseRest.setMetadata("Respuesta Ok", "201", "Respuesta exitosa");
        return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<CaterogiaResponseRest> actualizarCategoria(Categoria categoria, Long id) {
        log.info("Inicio actualizar categoria");
        CaterogiaResponseRest caterogiaResponseRest = new CaterogiaResponseRest();
        List<Categoria> categoriaList = new ArrayList<>();
        try {
            Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
            if (categoriaBuscada.isPresent()) {
                categoriaBuscada.get().setNombre(categoria.getNombre());
                categoriaBuscada.get().setDescripcion(categoria.getDescripcion());

                Categoria categoriaUpdate = categoriaDao.save(categoriaBuscada.get()); //Actualizando
                    if (categoriaUpdate != null) {

                        categoriaList.add(categoriaUpdate);
                        caterogiaResponseRest.getCategoriaResponse().setCategoria(categoriaList);
                    }else {
                        log.error("Error al actualizar categoria");
                        caterogiaResponseRest.setMetadata("Respuesta Error", "404", "Categoria no actualizada");
                      return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.BAD_REQUEST);
                    }

            }else {
                log.error("Error al actualizar categoria");
                caterogiaResponseRest.setMetadata("Respuesta Error", "404", "Categoria no actualizada");
                return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error al actualizar categoria");
            caterogiaResponseRest.setMetadata("Respuesta Error", "404", "Categoria no actualizada");
            return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("Fin actualizar categoria");
        caterogiaResponseRest.setMetadata("Respuesta Ok", "202", "Respuesta exitosa");
        return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<CaterogiaResponseRest> eliminarCategoria(Long id) {
        log.info("Inicio eliminar categoria");
        CaterogiaResponseRest caterogiaResponseRest = new CaterogiaResponseRest();
        try {
         categoriaDao.deleteById(id);
         log.info("Categoria eliminada exitosamente");
         caterogiaResponseRest.setMetadata("Respuesta Ok", "202", "Categoria eliminada");
        } catch ( Exception e ) {
            log.error("Error al eliminar categoria");
            e.getStackTrace();
            caterogiaResponseRest.setMetadata("Respuesta Error", "404", "Categoria no eliminada");
            return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.NOT_FOUND);
        }
        log.info("Fin eliminar categoria");
        caterogiaResponseRest.setMetadata("Respuesta Ok", "202", "Respuesta exitosa");
        return new ResponseEntity<CaterogiaResponseRest>(caterogiaResponseRest, HttpStatus.ACCEPTED);
    }
}
