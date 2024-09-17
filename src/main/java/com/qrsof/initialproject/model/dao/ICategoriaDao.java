package com.qrsof.initialproject.model.dao;

import com.qrsof.initialproject.model.Categoria;
import org.springframework.data.repository.CrudRepository;

//CrudRepository nos permite ralizar operaciones en la BD,


public interface ICategoriaDao extends CrudRepository<Categoria, Long> {

}
