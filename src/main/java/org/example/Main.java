package org.example;

import org.example.Configuracion.ConexionBD;
import org.example.Configuracion.DAOPersonaImpl;
import org.example.Configuracion.Persona;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("Prueba conexión BD");
        ConexionBD conexion=new ConexionBD();
        try(Connection con=conexion.getConexionBD()) {
            Statement st = con.createStatement();

            st.execute("CREATE SCHEMA IF NOT EXISTS BDPRUEBA1");

            st.execute("""
        CREATE TABLE IF NOT EXISTS BDPRUEBA1.TABLA1PRUEBA (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nombre VARCHAR(100),
            numero INT
        )
    """);

            System.out.println(con);
            DAOPersonaImpl dao=new DAOPersonaImpl(con);
            MenuBD1 menu = new MenuBD1(dao);
            menu.menu();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
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
