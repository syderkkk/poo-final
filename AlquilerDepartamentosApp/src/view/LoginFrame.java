package view;

import controller.LoginController;
import dao.impl.UsuarioDAOImpl;
import dao.interfaces.IUsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    private LoginController loginController;

    public LoginFrame() {
        // Crear el objeto DAO y el controlador
        IUsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        loginController = new LoginController(this, usuarioDAO);

        // Configurar la ventana principal
        setTitle("Iniciar sesión");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los componentes
        JLabel emailLabel = new JLabel("Correo:");
        JLabel passwordLabel = new JLabel("Contraseña:");
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Iniciar sesión");
        registerButton = new JButton("Registrar");

        // Crear un panel para organizar los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // Agregar los componentes al panel
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(loginButton);
        panel.add(registerButton);

        // Agregar el panel a la ventana principal
        add(panel, BorderLayout.CENTER);

        // Configurar acciones de botones
        loginButton.addActionListener(e -> loginController.handleLogin());
        registerButton.addActionListener(e -> loginController.handleRegister());
    }

    // Getters para los campos de texto y contraseñas
    public String getCorreo() {
        return emailField.getText().trim();
    }

    public String getContrasena() {
        return new String(passwordField.getPassword());
    }

    public void mostrarMensaje(String mensaje, String titulo, int tipoMensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipoMensaje);
    }
}