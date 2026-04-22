package org.example;

import org.example.Configuracion.DAOPersonaImpl;
import org.example.Configuracion.Persona;

import java.util.List;
import java.util.Scanner;

public class MenuBD1 {
    Scanner input = new Scanner(System.in);
    DAOPersonaImpl dao;

    MenuBD1(DAOPersonaImpl dao) {
        this.dao = dao;
    }

    void darAlta() {
        System.out.println("CREANDO NUEVA PERSONA");
        System.out.println("nombre: ");
        String name = input.nextLine();
        System.out.println("edad: ");
        int edad = Integer.parseInt(input.nextLine());

        dao.anadirPersona(new Persona(0, name, edad));
        System.out.println("PROCESO COMPLETADO");
    }

    void darBaja() {
        System.out.println("BORRANDO NUEVA PERSONA");
        System.out.println("id: ");
        int id = Integer.parseInt(input.nextLine());

        dao.borrarPersona(id);
        System.out.println("PROCESO COMPLETADO");
    }

    void modificarEdad() {
        System.out.println("MODIFICANDO EDAD");
        System.out.println("id: ");
        int id = Integer.parseInt(input.nextLine());

        Persona p = dao.buscarPersona(id);

        if (p != null) {
            System.out.println("SE HA ENCONTRADO A LA PERSONA");
            System.out.println("edad: ");
            int edad = Integer.parseInt(input.nextLine());
            dao.modificarPersona(id, new Persona(id, p.getNombre(), edad));
            System.out.println("PROCESO COMPLETADO");
        } else {
            System.out.println("NO SE HA ENCONTRADO A LA PERSONA");
        }
    }

    List<Persona> lisarPersonas() {
        System.out.println("LISTANDO PERSONAS");
        List<Persona> lista = dao.obtenerTodasPersonas();

        for (Persona p: lista) {
            System.out.println(p);
        }
        System.out.println("PROCESO COMPLETADO");

        return lista;
    }

    int calcularEdadMedia() {
        List<Persona> listaP = lisarPersonas();
        int edades = 0;
        int cont = 0;

        for (Persona p: listaP) {
            edades += p.getEdad();
            cont++;
        }

        System.out.println("PROCESO COMPLETADO");
        return edades/cont;
    }

    List<Persona> listarPersonasPorRangoEdad () {
        System.out.println("LISTANDO PERSONAS POR EDAD");

        System.out.println("Edad mínima: ");
        int min = Integer.parseInt(input.nextLine());
        System.out.println("Edad máxima: ");
        int max = Integer.parseInt(input.nextLine());

        List<Persona> lista = dao.obtenerTodasPersonasPorRangoEdad(min, max);

        for (Persona p: lista) {
            System.out.println(p);
        }
        System.out.println("PROCESO COMPLETADO");

        return lista;
    }

    void menu() {
        int opcion;

        do {
            System.out.println("\n===== MENÚ PERSONAS =====");
            System.out.println("1. Dar de alta persona");
            System.out.println("2. Dar de baja persona");
            System.out.println("3. Modificar edad");
            System.out.println("4. Listar personas");
            System.out.println("5. Calcular edad media");
            System.out.println("6. Listar personas por rango de edad");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(input.nextLine());

            switch (opcion) {
                case 1 -> darAlta();
                case 2 -> darBaja();
                case 3 -> modificarEdad();
                case 4 -> lisarPersonas();
                case 5 -> {
                    int media = calcularEdadMedia();
                    System.out.println("Edad media: " + media);
                }
                case 6 -> {
                    listarPersonasPorRangoEdad();
                }
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }

}
