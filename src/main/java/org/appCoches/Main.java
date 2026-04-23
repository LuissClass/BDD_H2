package org.appCoches;

import org.appCoches.Config.ConexionBD;
import org.appCoches.Config.DAOCoche;
import org.appCoches.servicio.Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("Prueba conexión BD");
        ConexionBD conexion = new ConexionBD();
        String nombreDB = conexion.devolverNombreDB();

        try(Connection con=conexion.getConexionBD()) {
            Statement st = con.createStatement();

            st.execute("CREATE SCHEMA IF NOT EXISTS " + nombreDB);

            st.execute("""
        CREATE TABLE IF NOT EXISTS BDPRUEBA1.COCHES(Matricula VARCHAR(10) PRIMARY KEY,
                                MODELO VARCHAR(40) NOT NULL, ANTIGUEDAD INT NOT NULL,
                        PRECIO DOUBLE NOT NULL);
    """);

            System.out.println(con);
            DAOCoche dao = new DAOCoche(con, nombreDB+".COCHES");
            Menu menu = new Menu(dao);
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
