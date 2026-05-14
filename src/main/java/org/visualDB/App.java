package org.visualDB;

import org.example.Configuracion.ConexionBD;
import org.example.Configuracion.DAOPersonaImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        System.out.println("Prueba conexión BD");
        ConexionBD conexion = new ConexionBD();
        try (Connection con = conexion.getConexionBD()) {
            Statement st = con.createStatement();

            st.execute("CREATE SCHEMA IF NOT EXISTS BDPAINT");

            st.execute("""
                        CREATE TABLE IF NOT EXISTS BDPAINT.PUNTOS (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            x INT,
                            y INT
                        )
                    """);

            System.out.println(con);
            DatosService ds = new DatosService(con, "PUNTOS");

            Paint p = new Paint(ds);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion.getConexionBD() != null) {
                    conexion.getConexionBD().close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
