package org.visualDB;

import org.appCoches.modelo.Coche;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatosService {
    private final Connection conexion;
    private final String tableName;

    ArrayList<Point> puntos;

    public DatosService(Connection con, String nameT) {
        this.conexion = con;
        this.tableName = nameT;
    }

    public void guardarPuntos(ArrayList<Point> puntos) {
        try {
            for (Point p : puntos) {
                PreparedStatement ps = conexion.prepareStatement("INSERT INTO " + tableName + " (x, y) VALUES (?,?)");
                ps.setDouble(1, p.getX());
                ps.setDouble(2, p.getY());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargarPuntos() {
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM " + tableName);

            ResultSet puntoAux = ps.executeQuery();
            while (puntoAux.next()) {
                    int x = puntoAux.getInt("x");
                    int y = puntoAux.getInt("y");

                if (puntoAux.wasNull()) {
                    Point p = new Point(x, y);
                    puntos.add(p);
                }  else {
                    puntos.add(null);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
