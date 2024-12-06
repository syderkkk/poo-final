package dao.interfaces;

import model.Usuario;

import java.sql.SQLException;

public interface IUsuarioDAO {

    // CREATE
    void insertarUsuario(Usuario usuario) throws SQLException;

    // READ
    Usuario obtenerUsuarioPorCorreo(String correo) throws SQLException;

    // UPDATE
    void actualizarSaldo(Usuario usuario) throws SQLException;
    void cargarSaldo(int userId, double monto) throws SQLException;
}
