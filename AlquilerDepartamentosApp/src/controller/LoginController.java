package controller;

import dao.interfaces.IUsuarioDAO;
import model.Usuario;
import utils.UsuarioSession;
import view.LoginFrame;
import view.MainFrame;
import view.RegistroFrame;

import javax.swing.*;

public class LoginController {

    private final LoginFrame loginFrame;
    private final IUsuarioDAO usuarioDAO;

    private Usuario usuarioActual;

    public LoginController(LoginFrame loginFrame, IUsuarioDAO usuarioDAO) {
        this.loginFrame = loginFrame;
        this.usuarioDAO = usuarioDAO;
    }

    public void handleLogin() {
        String correo = loginFrame.getCorreo();
        String contrasena = loginFrame.getContrasena();

        try {
            // Obtener el usuario de la base de datos
            Usuario usuarioActual = usuarioDAO.obtenerUsuarioPorCorreo(correo);

            // Validar credenciales
            if (usuarioActual == null || !usuarioActual.getContrasena().equals(contrasena)) {
                loginFrame.mostrarMensaje("Correo o contraseña incorrectos.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Guardar el usuario autenticado en UsuarioSession
            UsuarioSession.getInstance().setUsuarioActual(usuarioActual);

            // Mostrar mensaje y abrir la ventana principal
            loginFrame.dispose();
            new MainFrame().setVisible(true); // Ya no pasamos el usuario aquí, usamos el Singleton

        } catch (Exception e) {
            e.printStackTrace();
            loginFrame.mostrarMensaje("Error al autenticar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Manejar la transición al registro
    public void handleRegister() {
        loginFrame.dispose();
        new RegistroFrame().setVisible(true);
    }
}