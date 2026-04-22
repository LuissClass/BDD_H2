package org.example.Configuracion;

import java.util.List;

public interface DAOPersona {
    boolean anadirPersona(Persona p);

    boolean borrarPersona(int id);

    boolean modificarPersona(int id, Persona p);

    Persona buscarPersona(int id);

    List<Persona> obtenerTodasPersonas();
}
