package dao.impl;

import config.ConexionBD;
import dao.interfaces.IUsuarioDAO;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements IUsuarioDAO {

    @Override
    public void insertarUsuario(Usuario usuario) throws SQLException {
        Connection conexion = ConexionBD.getConnection();
        String sql = "INSERT INTO usuarios (nombre, telefono, direccion, correo, contrasena) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getTelefono());
            statement.setString(3, usuario.getDireccion());
            statement.setString(4, usuario.getCorreo());
            statement.setString(5, usuario.getContrasena());

            statement.executeUpdate();
        }
    }

    @Override
    public Usuario obtenerUsuarioPorCorreo(String correo) throws SQLException {
        Connection conexion = ConexionBD.getConnection();
        String query = "SELECT * FROM usuarios WHERE correo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                String contrasena = rs.getString("contrasena");

                return new Usuario(nombre, telefono, direccion, correo, contrasena);
            } else {
                return null; // Si no encuentra el usuario
            }
        }
    }
}
