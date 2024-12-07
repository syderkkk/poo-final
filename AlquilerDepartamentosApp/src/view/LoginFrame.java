package view;


import controller.LoginController;
import dao.impl.UsuarioDAOImpl;
import dao.interfaces.IUsuarioDAO;

import javax.swing.*;

public class LoginFrame extends JFrame {

    public JPanel panel;
    private JTextField emailField;
    private JButton loginButton, registerButton;
    private JPasswordField passwordField;

    private LoginController loginController;

    public LoginFrame() {
        initComponents();

        // Crear el objeto DAO y el controlador
        IUsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        loginController = new LoginController(this, usuarioDAO);



        loginButton.addActionListener(e -> {
            loginController.handleLogin();
        });

        registerButton.addActionListener(e -> {
            loginController.handleRegister();
        });

    }

    private void initComponents() {
        setTitle("Iniciar sesión");
        setContentPane(panel); // Agrega el panel principal al frame
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
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
