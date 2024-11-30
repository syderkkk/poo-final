package controller;

import dao.interfaces.IUsuarioDAO;
import model.Usuario;
import utils.Validaciones;

import javax.swing.*;

public class RegistroController {

    // Registrar al usuario después de validar los campos
    public boolean registrarUsuario(String nombre, String apellidos, String telefono, String direccion, String correo, String contrasena, IUsuarioDAO usuarioDAO) {

        // Usar Validaciones para validar los campos
        if (Validaciones.validarCampos(nombre, apellidos, telefono, direccion, correo, contrasena)) {
            if (!Validaciones.validarNombre(nombre) || !Validaciones.validarNombre(apellidos)) {
                return false; // Si alguno de los campos es inválido, detiene el registro
            }
            try {
                Usuario usuario = new Usuario(nombre, telefono, direccion, correo, contrasena);
                usuarioDAO.insertarUsuario(usuario);
                JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al registrar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }
}
