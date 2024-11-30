package controller;

import dao.interfaces.IUsuarioDAO;
import model.Usuario;
import utils.Validaciones;

import javax.swing.*;

public class LoginController {

    private IUsuarioDAO usuarioDAO;

    public LoginController(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    // Validar que las credenciales sean correctas
    public Usuario autenticarUsuario(String correo, String contrasena) {
        try {

            /*
            // Usar la validación de correo desde la clase Validaciones
            if (!Validaciones.validarCorreo(correo)) {
                return null; // No continúa si el correo es inválido
            }
             */

            Usuario usuario = usuarioDAO.obtenerUsuarioPorCorreo(correo); // Buscar al usuario por correo

            if (usuario == null || !usuario.getContrasena().equals(contrasena)) {
                JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            return usuario; // Devuelve el usuario autenticado
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al autenticar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}