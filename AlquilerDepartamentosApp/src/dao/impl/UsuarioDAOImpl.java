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
        String sql = "INSERT INTO usuarios (nombres, apellidos, telefono, direccion, correo, contrasena) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombres());
            statement.setString(2, usuario.getApellidos());
            statement.setString(3, usuario.getTelefono());
            statement.setString(4, usuario.getDireccion());
            statement.setString(5, usuario.getCorreo());
            statement.setString(6, usuario.getContrasena());

            statement.executeUpdate();
        }
    }

    @Override
    public Usuario obtenerUsuarioPorCorreo(String correo) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE correo = ?";
        try (Connection conexion = ConexionBD.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idUsuario = rs.getInt("ID_Usuario");
                String nombres = rs.getString("nombres");
                String apellios = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                String contrasena = rs.getString("contrasena");
                double saldo = rs.getDouble("saldo");

                return new Usuario(idUsuario, nombres, apellios, telefono, direccion, correo, contrasena, saldo);
            } else {
                return null; // Si no encuentra el usuario
            }
        }
    }

    @Override
    public void actualizarSaldo(Usuario usuario) throws SQLException {
        Connection conexion = ConexionBD.getConnection();
        String sql = "UPDATE usuarios SET saldo = ? WHERE correo = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setDouble(1, usuario.getSaldo());
            statement.setString(2, usuario.getCorreo());

            statement.executeUpdate();
        }
    }

    @Override
    public void cargarSaldo(int userId, double monto) throws SQLException {
        String sql = "UPDATE usuarios SET saldo = saldo + ? WHERE ID_Usuario = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, monto);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        }
    }

}
