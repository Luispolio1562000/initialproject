package com.qrsof.initialproject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "libros")
//Se implementa serializable porque se hacen peticiones http, esto lo convierte en bytes para su transmision por la red
public class Libro implements Serializable {
    @Serial
    private static final long serialVersionUID = -890324621643048199L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;


    //Relacion muchos a uno, se establece como se carga la operacion.
    //La info se carga hasta que es solicitada.

    @ManyToOne(fetch = FetchType.EAGER)
    //Especifica propiedades que deben ser ignoradas durante la serializacion del JSON
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Categoria categoria;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
