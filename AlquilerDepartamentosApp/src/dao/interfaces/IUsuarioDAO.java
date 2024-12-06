package dao.interfaces;

import model.Usuario;

import java.sql.SQLException;

public interface IUsuarioDAO {
    void insertarUsuario(Usuario usuario) throws SQLException;
    Usuario obtenerUsuarioPorCorreo(String correo) throws SQLException;
    void actualizarSaldo(Usuario usuario) throws SQLException;
    void cargarSaldo(int userId, double monto) throws SQLException;
}
