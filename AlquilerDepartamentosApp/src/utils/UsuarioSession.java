package utils;

import model.Usuario;

public class UsuarioSession {

    private static UsuarioSession instancia;
    private Usuario usuarioActual;

    private UsuarioSession() {}

    public static UsuarioSession getInstance() {
        if (instancia == null) {
            instancia = new UsuarioSession();
        }
        return instancia;
    }

    // Método para establecer el usuario actual
    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    // Método para obtener el usuario actual
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    // Método para cerrar la sesión
    public void cerrarSesion() {
        usuarioActual = null; // Limpia el usuario actual
    }
}

