package org.example.Configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private String usuario = "sa";
    private String password = "";
    private String urlBD = "jdbc:h2:mem:testdb";
    private String ControladorBD = "org.h2.Driver"; //Será diferente para mysql, oracle, etc.+
    private Connection conexion = null;

    public Connection getConexionBD() throws SQLException, ClassNotFoundException {
//        System.out.println("Conectando a la base de datos");
        Class.forName(ControladorBD);
        if (conexion == null)
            conexion = DriverManager.getConnection(urlBD, usuario, password);
        return conexion;
    }
}
