package com.qrsof.initialproject.model.dao;

import com.qrsof.initialproject.model.Libro;
import org.springframework.data.repository.CrudRepository;

public interface ILibroDao extends CrudRepository<Libro, Long> {

}
