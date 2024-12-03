package controller;

import dao.interfaces.IUsuarioDAO;
import model.Usuario;
import view.LoginFrame;
import view.MainFrame;
import view.RegistroFrame;

import javax.swing.*;

public class LoginController {

    private final LoginFrame loginFrame;
    private final IUsuarioDAO usuarioDAO;

    public LoginController(LoginFrame loginFrame, IUsuarioDAO usuarioDAO) {
        this.loginFrame = loginFrame;
        this.usuarioDAO = usuarioDAO;
    }

    // Manejar el inicio de sesión
    public void handleLogin() {
        String correo = loginFrame.getCorreo();
        String contrasena = loginFrame.getContrasena();


        // Validar que el correo sea válido
        /*
        if (!Validaciones.validarCorreo(correo)) {
            return; // No continuar con la autenticación
        }

         */
        try {
            Usuario usuario = usuarioDAO.obtenerUsuarioPorCorreo(correo);

            if (usuario == null || !usuario.getContrasena().equals(contrasena)) {
                loginFrame.mostrarMensaje("Correo o contraseña incorrectos.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //loginFrame.mostrarMensaje("Inicio de sesión exitoso, bienvenido " + usuario.getNombre() + "!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            loginFrame.dispose();
            new MainFrame().setVisible(true);

        } catch (Exception e) {
            loginFrame.mostrarMensaje("Error al autenticar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Manejar la transición al registro
    public void handleRegister() {
        loginFrame.dispose();
        new RegistroFrame().setVisible(true);
    }
}