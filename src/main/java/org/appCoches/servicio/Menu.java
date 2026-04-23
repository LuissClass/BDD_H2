package org.appCoches.servicio;

import org.appCoches.Config.DAOCoche;
import org.appCoches.modelo.Coche;

import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);
    DAOCoche dao;

    public Menu(DAOCoche dao) {
        this.dao = dao;
    }

    public void menu() {
        int opcion;

        do {
            System.out.println("\n===== MENÚ COCHES =====");
            System.out.println("1. Insertar coche");
            System.out.println("2. Eliminar coche");
            System.out.println("3. Incrementar precio de todos los coches en un porcentaje");
            System.out.println("4. Listar coches");
            System.out.println("5. Suma del precio de todos los coches");
            System.out.println("6. Listar matriculas por marca");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(input.nextLine());

            switch (opcion) {
                case 1 -> insertarCoche();
                case 2 -> eliminarCoche();
                case 3 -> incrementarPrecioEnPorcentaje();
                case 4 -> dao.listar();
                case 5 -> {
                    sumarTodosLosPrecios();
                }
                case 6 -> listarMatriculasPorMarca();
            }

        } while (opcion != 0);
    }

    public void insertarCoche() {
        System.out.println("MATRICULA: ");
        String matricula = input.nextLine();
        System.out.println("MODELO: ");
        String modelo = input.nextLine();
        System.out.println("ANTIGUEDAD: ");
        int antiguedad = Integer.parseInt(input.nextLine());
        System.out.println("PRECIO: ");
        double precio = Double.parseDouble(input.nextLine());

        msgFin(dao.insertar(new Coche(matricula, modelo, antiguedad, precio)));
    }
    public void eliminarCoche() {
        System.out.println("MATRICULA: ");
        String matricula = input.nextLine();

        msgFin(dao.borrar(matricula));
    }
    public void incrementarPrecioEnPorcentaje() {
        System.out.println("PORCENTAJE (100%): ");
        double porcentaje = Double.parseDouble(input.nextLine());

        msgFin(dao.incremenPrecio(porcentaje));
    }

    public void sumarTodosLosPrecios() {
        System.out.println("ANTIGUEDAD MIN: ");
        int min = Integer.parseInt(input.nextLine());
        System.out.println("ANTIGUEDAD MAX: ");
        int max = Integer.parseInt(input.nextLine());

        double suma = dao.sumarPrecio(min, max);

        System.out.println(suma + "$");

        if (suma >= 0) {
            msgFin(true);
        } else {
            msgFin(false);
        }
    }

    public void listarMatriculasPorMarca() {
        System.out.println("MODELO: ");
        String modelo = input.nextLine();

        dao.listar(modelo);
    }

    private void msgFin(boolean resultado) {
        if (resultado) {
            System.out.println(">>PROCESO FINALIZADO");
        } else {
            System.out.println(">>SE HA PRODUCIDO UN ERROR");
        }
    }
}

