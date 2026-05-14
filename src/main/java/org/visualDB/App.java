package org.visualDB;


import org.visualDB.Configuracion.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        System.out.println("Prueba conexión BD");
        ConexionBD conexion = new ConexionBD();
        try {
            Connection con = conexion.getConexionBD();
            Statement st = con.createStatement();

            st.execute("CREATE SCHEMA IF NOT EXISTS BDPAINT");

            st.execute("""
                        CREATE TABLE IF NOT EXISTS BDPAINT.PUNTOS (
                            x INT,
                            y INT
                        )
                    """);

            System.out.println(con);
            DatosService ds = new DatosService(con, "BDPAINT.PUNTOS");

            Paint p = new Paint(ds);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
