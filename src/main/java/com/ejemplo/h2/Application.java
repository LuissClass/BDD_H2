package com.ejemplo.h2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// http://localhost:8080/h2-console
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner consultasPro(TareaRepository repository) {
        return args -> {
            // 1. Insertamos datos para tener qué consultar
            repository.save(new Tarea("Lavar el coche", false));
            repository.save(new Tarea("Lavar los platos", true));
            repository.save(new Tarea("Hacer ejercicio", false));

            // 2. Consulta: Buscar las que NO están completadas
            System.out.println("--- Tareas pendientes ---");
            repository.findByCompletada(false).forEach(t -> System.out.println("- " + t.getDescripcion()));

            // 3. Consulta: Buscar las que contienen la palabra "Lavar"
            System.out.println("--- Tareas de limpieza ---");
            repository.findByDescripcionContaining("Lavar").forEach(t -> System.out.println("- " + t.getDescripcion()));

            // 4. Consulta: Contar totales
            System.out.println("--- Total de tareas en BD: " + repository.count());
        };
    }
}