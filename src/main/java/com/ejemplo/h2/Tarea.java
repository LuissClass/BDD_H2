package com.ejemplo.h2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Esto le dice a Spring: "Crea una tabla basada en esta clase"
public class Tarea {

    @Id // Define la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El ID se incrementa solo (1, 2, 3...)
    private Long id;

    private String descripcion;
    private boolean completada;

    // IMPORTANTE: Necesitas un constructor vacío para JPA
    public Tarea() {}

    public Tarea(String descripcion, boolean completada) {
        this.descripcion = descripcion;
        this.completada = completada;
    }

    // Getters y Setters (obligatorios para que Spring acceda a los datos)
    public Long getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }
}