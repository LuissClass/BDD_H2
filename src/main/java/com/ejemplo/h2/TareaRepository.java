package com.ejemplo.h2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Al extender JpaRepository, ya tienes: Save, Delete, FindAll, etc.
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    // Busca tareas por descripción exacta
    List<Tarea> findByDescripcion(String descripcion);

    // Busca tareas que contengan una palabra (como el LIKE de SQL)
    List<Tarea> findByDescripcionContaining(String palabra);

    // Busca tareas por su estado (completada o no)
    List<Tarea> findByCompletada(boolean estado);
}