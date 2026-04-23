package org.appCoches.Config;

import org.appCoches.modelo.Coche;

public interface IDAOCoche {
    boolean insertar(Coche coche);
    boolean borrar(String matricula);
    boolean incremenPrecio(double porcentaje);
    void listar();
    double sumarPrecio(int min, int max);
    void listar(String marca);
}
