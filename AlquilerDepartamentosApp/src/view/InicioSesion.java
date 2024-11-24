package view;

import javax.swing.*;

public class InicioSesion extends JFrame {
    public InicioSesion() {
        setTitle("Inicio de Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblUsuario = new JLabel("Correo:");
        JLabel lblContrasena = new JLabel("Contraseña:");
        JTextField txtUsuario = new JTextField(20);
        JPasswordField txtContrasena = new JPasswordField(20);
        JButton btnIngresar = new JButton("Ingresar");

        JPanel panel = new JPanel();
        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(btnIngresar);

        add(panel);
    }
}
