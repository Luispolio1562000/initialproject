package com.qrsof.initialproject.controllers;

import com.qrsof.initialproject.model.Categoria;
import com.qrsof.initialproject.response.categorias.CaterogiaResponseRest;
import com.qrsof.initialproject.service.ICategoriaService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class CategoriaRestControllerTest {

    @InjectMocks
    CategoriaRestController categoriaRestController;

    @Mock
    private ICategoriaService categoriaService;


    @BeforeEach
    public void  init(){
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public  void createCategoriaTest(){
        //Contexto de la solicitud.
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Categoria categoria = new Categoria( Long.valueOf(1), "Clasicos", "Libros de literatura moderna.");
        when(categoriaService.crearCategoria(any(Categoria.class))).thenReturn(new ResponseEntity<CaterogiaResponseRest>(HttpStatus.CREATED));
        ResponseEntity<CaterogiaResponseRest> respuesta = categoriaRestController.crearCategoria(categoria);
        assertThat(respuesta.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());
    }
}
