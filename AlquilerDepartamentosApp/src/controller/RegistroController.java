package controller;

import dao.impl.UsuarioDAOImpl;
import dao.interfaces.IUsuarioDAO;
import model.Usuario;
import utils.Validaciones;
import view.LoginFrame;

import javax.swing.*;

public class RegistroController {

    private final JFrame frame;

    public RegistroController(JFrame frame) {
        this.frame = frame;
    }

    public void registrarUsuario(String nombre, String apellidos, String telefono, String direccion, String correo, String contrasena) {
        IUsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        // Usar Validaciones para validar los campos
        if (Validaciones.validarCampos(nombre, apellidos, telefono, direccion, correo, contrasena)) {
            if (!Validaciones.validarNombre(nombre) || !Validaciones.validarNombre(apellidos)) {
                return;
            }
            try {
                Usuario usuario = new Usuario(nombre, telefono, direccion, correo, contrasena);
                usuarioDAO.insertarUsuario(usuario);

                JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose(); // Cerrar el frame actual
                new LoginFrame().setVisible(true); // Abrir el LoginFrame
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error al registrar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void cancelarRegistro() {
        frame.dispose(); // Cerrar el frame actual
        new LoginFrame().setVisible(true); // Abrir el LoginFrame
    }
}