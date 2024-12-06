package utils;

import javax.swing.*;
import java.util.regex.Pattern;

public class Validaciones {

    // Validar que un valor no esté vacío
    public static boolean noVacio(String valor, String mensajeError) {
        if (valor == null || valor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, mensajeError, "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Validar que un valor es numérico
    public static boolean esNumero(String valor, String mensajeError) {
        try {
            Double.parseDouble(valor);  // Para precios que pueden ser decimales
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, mensajeError, "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Validar que un valor entero es válido
    public static boolean esNumeroEntero(String valor, String mensajeError) {
        try {
            Integer.parseInt(valor);  // Para valores enteros como habitaciones, baños, capacidad
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, mensajeError, "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Validar que el teléfono contenga solo números
    public static boolean validarTelefono(String telefono) {
        if (!telefono.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "El teléfono solo debe contener números.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Validar que el correo tenga un formato correcto
    public static boolean validarCorreo(String correo) {
        String correoRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; // Expresión regular para correos válidos
        if (!Pattern.matches(correoRegex, correo)) {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa un correo electrónico válido.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Validar que todos los campos sean válidos
    public static boolean validarCampos(String nombre, String apellidos, String telefono, String direccion, String correo, String contrasena) {
        if (nombre.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validarTelefono(telefono) || !validarCorreo(correo)) {
            return false;
        }
        return true;
    }

    // Validar que el nombre solo contenga letras y espacios
    public static boolean validarNombre(String nombre) {
        // Expresión regular para permitir solo letras (incluyendo acentos) y espacios
        String nombreRegex = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+$";
        if (!Pattern.matches(nombreRegex, nombre)) {
            JOptionPane.showMessageDialog(null, "El nombre solo puede contener letras y espacios.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
