package com.qrsof.initialproject.service;

import com.qrsof.initialproject.model.Categoria;
import com.qrsof.initialproject.model.dao.ICategoriaDao;

import com.qrsof.initialproject.response.categorias.CaterogiaResponseRest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class CategoriaServicesImplTest {
    @InjectMocks
    CategoriaServiceImpl categoriaService;
    @Mock
    ICategoriaDao categoriaDao;
    List<Categoria> categorias = new ArrayList<>();

    @BeforeEach
    public void init() {
        //Indica que se utilizaran las anotaciones de mockito
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void buscarCategoriaTest() {
        //Se evalua si el dao, llama al findAll, retorna un objeto, se evita ir a la BD.
        when(categoriaDao.findAll()).thenReturn(categorias);

        ResponseEntity<CaterogiaResponseRest> response = categoriaService.buscarCategorias();
        assertEquals(10, response.getBody().getCategoriaResponse().getCategoria().size());
        verify(categoriaDao, times(1)).findAll();
    }

    @BeforeEach
    public void cargarCategorias() {
        Categoria categoria1 = new Categoria(Long.valueOf(1), "Historia", "Libros de historia");
        Categoria categoria2 = new Categoria(Long.valueOf(2), "Ciencia", "Libros de ciencia");
        Categoria categoria3 = new Categoria(Long.valueOf(3), "Ficción", "Libros de ficción");
        Categoria categoria4 = new Categoria(Long.valueOf(4), "Biografía", "Libros de biografía");
        Categoria categoria5 = new Categoria(Long.valueOf(5), "Tecnología", "Libros de tecnología");
        Categoria categoria6 = new Categoria(Long.valueOf(6), "Arte", "Libros de arte");
        Categoria categoria7 = new Categoria(Long.valueOf(7), "Deportes", "Libros de deportes");
        Categoria categoria8 = new Categoria(Long.valueOf(8), "Música", "Libros de música");
        Categoria categoria9 = new Categoria(Long.valueOf(9), "Cocina", "Libros de cocina");
        Categoria categoria10 = new Categoria(Long.valueOf(10), "Viajes", "Libros de viajes");
        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);
        categorias.add(categoria4);
        categorias.add(categoria5);
        categorias.add(categoria6);
        categorias.add(categoria7);
        categorias.add(categoria8);
        categorias.add(categoria9);
        categorias.add(categoria10);


    }

}
