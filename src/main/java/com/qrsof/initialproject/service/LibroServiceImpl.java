package com.qrsof.initialproject.service;

import com.qrsof.initialproject.model.Libro;
import com.qrsof.initialproject.model.dao.ILibroDao;
import com.qrsof.initialproject.response.libros.LibroResponseRest;
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
public class LibroServiceImpl implements ILibroService {
    private static final Logger log = LoggerFactory.getLogger(LibroServiceImpl.class);

    @Autowired
    private ILibroDao libroDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> obtenerLibros() {
        log.info("Obteniendo libros");
        LibroResponseRest libroResponseRest = new LibroResponseRest();

        try {
            List<Libro> libros = (List<Libro>) libroDao.findAll();
            libroResponseRest.getLibroResponse().setLibros(libros);
            libroResponseRest.setMetadata("Respuesta Ok", "200", "Respuesta exitosa");
            log.info("Libros obtenidos");

        } catch (Exception e) {
            log.error("Error al obtener libros", e.getMessage());
            libroResponseRest.setMetadata("Respuesta Error", "500", "Error al buscar libros");
            e.getStackTrace();
            return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);

        } finally {
            log.info("Fin obteniendo libros");

        }
        return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibroResponseRest> obtenerLibro(Long id) {
        log.info("Obteniendo libro {}", id);
        LibroResponseRest libroResponseRest = new LibroResponseRest();
        List<Libro> libro = new ArrayList<>();
        try {
            Optional<Libro> libroOptional = libroDao.findById(id);
            if (libroOptional.isEmpty()) {
                log.warn("Libro no encontrado");
                libroResponseRest.setMetadata("Respuesta Error", "500", "Error al obtener libro");
                return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.NOT_FOUND);
            } else {
                log.info("Libro encontrado");
                libro.add(libroOptional.get());
                libroResponseRest.getLibroResponse().setLibros(libro);
                libroResponseRest.setMetadata("Respuesta Ok", "200", "Respuesta exitosa");
            }

        } catch (Exception e) {
            log.error("Error al obtener libros", e.getMessage());
            libroResponseRest.setMetadata("Respuesta Error", "500", "Libro no encontrado");
            return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.NOT_FOUND);
        } finally {
            log.info("Fin obteniendo libro");
        }
        return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> crearLibro(Libro libro) {
        log.info("Creando libro {}", libro);
        LibroResponseRest libroResponseRest = new LibroResponseRest();
        List<Libro> crearLibro = new ArrayList<>();


        try {
            Libro libroGuardado = libroDao.save(libro);
            if (libroGuardado != null) {
                crearLibro.add(libroGuardado);
                libroResponseRest.getLibroResponse().setLibros(crearLibro);
                libroResponseRest.setMetadata("Respuesta Ok", "202", "Respuesta exitosa");
                log.info("Libro creado {}", libro);
            } else {
                log.warn("Libro no creado");
                libroResponseRest.setMetadata("Respuesta Error", "500", "Error al crear libro");
            return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Error al crear libro", e.getMessage());
            libroResponseRest.setMetadata("Respuesta Error", "500", "Error al crear libro");
            return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            log.info("Fin creando libro");
        }
        return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.CREATED);
    }
}
