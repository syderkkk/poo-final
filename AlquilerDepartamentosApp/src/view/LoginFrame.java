package view;

import controller.LoginController;
import dao.impl.UsuarioDAOImpl;
import dao.interfaces.IUsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    private LoginController loginController;

    public LoginFrame() {
        // Crear el objeto DAO y el controlador
        IUsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        loginController = new LoginController(usuarioDAO);

        // Configurar la ventana principal (JFrame)
        setTitle("Iniciar sesión");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear los componentes
        JLabel emailLabel = new JLabel("Correo:");
        JLabel passwordLabel = new JLabel("Contraseña:");
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Iniciar sesión");
        registerButton = new JButton("Registrar");

        // Crear un panel para organizar los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // GridLayout de 4 filas y 2 columnas

        // Agregar los componentes al panel
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(loginButton);
        panel.add(registerButton); // Botón de registro

        // Agregar el panel a la ventana principal
        add(panel, BorderLayout.CENTER);

        // Configurar la acción del botón "Iniciar sesión"
        loginButton.addActionListener(e -> iniciarSesion());

        // Configurar la acción del botón "Registrar"
        registerButton.addActionListener(e -> {
            dispose(); // Cerrar el LoginFrame
            new RegistroFrame().setVisible(true); // Abrir la ventana de registro
        });
    }

    // Método para iniciar sesión
    private void iniciarSesion() {
        String correo = emailField.getText().trim();
        String contrasena = new String(passwordField.getPassword());

        Usuario usuario = loginController.autenticarUsuario(correo, contrasena);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso, bienvenido " + usuario.getNombre() + "!");
            dispose(); // Cerrar el LoginFrame
            new MainFrame().setVisible(true); // Abrir la ventana principal
        }
    }
}