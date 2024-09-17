package com.qrsof.initialproject.model;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.io.Serial;
import java.io.Serializable;

//Anotacion: Agrega metadatos que eriquecen los elementos de nuestro codigo.

//Establecemos una representacion en la  BD
@Entity
//Nombre de la tabla en la BD
@Table(name = "categorias")
public class Categoria implements Serializable {


    @Serial
    private static final long serialVersionUID = -7479912260814714380L;

    //Se establece la Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
