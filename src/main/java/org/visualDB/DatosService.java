package org.visualDB;


import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatosService {
    private final Connection conexion;
    private final String tableName;

    ArrayList<Point> puntos = new ArrayList<>();

    public DatosService(Connection con, String nameT) {
        this.conexion = con;
        this.tableName = nameT;
    }

    public void guardarPuntos(ArrayList<Point> puntos, int limit) {
        if (puntos == null || puntos.isEmpty()) {
            return;
        }

        try {
            PreparedStatement psDel = conexion.prepareStatement("DELETE FROM " + tableName);
            psDel.executeUpdate();

            for (int i = 0; i < limit; i++) {
                Point p = puntos.get(i);

                PreparedStatement ps = conexion.prepareStatement("INSERT INTO " + tableName + " (x, y) VALUES (?,?)");
                if (p == null) {
                    ps.setDouble(1, -69);
                    ps.setDouble(2, -69);
                } else {
                    ps.setDouble(1, p.getX());
                    ps.setDouble(2, p.getY());
                }

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar los puntos en la tabla: ERROR: " + e);
        }
    }

    public void cargarPuntos() {
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM " + tableName);

            ResultSet puntoAux = ps.executeQuery();

            if (!puntoAux.next()) {
                return; // No hay datos, no se entra al while
            }

            do {
                int x = puntoAux.getInt("x");
                int y = puntoAux.getInt("y");

                if (x == -69 && y == -69) {
                    puntos.add(null);
                } else {
                    puntos.add(new Point(x, y));
                }

            } while (puntoAux.next());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
