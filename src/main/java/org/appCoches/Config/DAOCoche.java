package org.appCoches.Config;

import org.appCoches.modelo.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCoche implements IDAOCoche{
    private Connection conexion = null;
    private String tableName;

    public DAOCoche(Connection con, String nameT) {
        this.conexion = con;
        this.tableName = nameT;
    }


    @Override
    public boolean insertar(Coche c) {
        try {
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO " + tableName + " (matricula, modelo, antiguedad, precio) VALUES (?,?,?,?)");
            ps.setString(1, c.getMatricula());
            ps.setString(2, c.getModelo());
            ps.setInt(3, c.getAntiguedad());
            ps.setDouble(4, c.getPrecio());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean borrar(String matricula) {
        try {
            PreparedStatement ps = conexion.prepareStatement("DELETE FROM " + tableName + " WHERE matricula=?");
            ps.setString(1, matricula);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean incremenPrecio(double porcentaje) {
        try {
            PreparedStatement ps = conexion.prepareStatement("UPDATE " + tableName + " SET precio=precio*?");
            ps.setDouble(1, porcentaje / 100.0 + 1.0);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private List<Coche> devolverTodos() {
        List<Coche> coches = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM " + tableName);
            ResultSet cocheAux = ps.executeQuery();
            while (cocheAux.next()) {
                String matricula = cocheAux.getString("matricula");
                String modelo = cocheAux.getString("modelo");
                int antiguedad = cocheAux.getInt("antiguedad");
                double precio = cocheAux.getDouble("precio");

                Coche c = new Coche(matricula, modelo, antiguedad, precio);
                coches.add(c);
            }
            return coches;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public void listar() {
        List<Coche> coches = devolverTodos();

        for (Coche c: coches) {
            System.out.println(c);
        }
    }

    @Override
    public double sumarPrecio(int min, int max) {
        double sumaPrecios = 0;

        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT precio FROM " + tableName + " WHERE antiguedad >= ? and antiguedad <= ?");
            ps.setInt(1, min);
            ps.setInt(2, max);
            ResultSet precios = ps.executeQuery();

            while (precios.next()) {
                double precio = precios.getDouble("precio");
                sumaPrecios = sumaPrecios + precio;
            }

            return sumaPrecios;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -69.0;
        }
    }

    public List<Coche> devolverTodos(String modeloBuscar) {
        List<Coche> coches = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM " + tableName + " WHERE modelo=?");
            ps.setString(1, modeloBuscar);
            ResultSet cocheAux = ps.executeQuery();
            while (cocheAux.next()) {
                String matricula = cocheAux.getString("matricula");
                String modelo = cocheAux.getString("modelo");
                int antiguedad = cocheAux.getInt("antiguedad");
                double precio = cocheAux.getDouble("precio");

                Coche c = new Coche(matricula, modelo, antiguedad, precio);
                coches.add(c);
            }
            return coches;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void listar(String modelo) {
        List<Coche> coches = devolverTodos(modelo);

        for (Coche c: coches) {
            System.out.println(c.getMatricula());
        }
    }
}
