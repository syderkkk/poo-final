package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/prueba";
    private static final String USER = "root";
    private static final String PASSWORD = "tecsup";

    private static Connection conexion;

    private ConexionBD() {}

    public static Connection getConnection() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conexion;
    }
}
