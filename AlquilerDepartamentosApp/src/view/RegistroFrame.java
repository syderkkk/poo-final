package view;

import controller.RegistroController;
import dao.impl.UsuarioDAOImpl;
import dao.interfaces.IUsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class RegistroFrame extends JFrame {

    private JTextField nombreField, apellidosField, telefonoField, direccionField, correoField;
    private JPasswordField contrasenaField;
    private JButton registrarButton, cancelarButton;
    private IUsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    private RegistroController registroController;

    public RegistroFrame() {
        // Crear instancia del controlador
        registroController = new RegistroController();

        setTitle("Registro de Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes
        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel apellidosLabel = new JLabel("Apellidos:");
        JLabel telefonoLabel = new JLabel("Teléfono:");
        JLabel direccionLabel = new JLabel("Dirección:");
        JLabel correoLabel = new JLabel("Correo:");
        JLabel contrasenaLabel = new JLabel("Contraseña:");

        nombreField = new JTextField(20);
        apellidosField = new JTextField(20);
        telefonoField = new JTextField(20);
        direccionField = new JTextField(20);
        correoField = new JTextField(20);
        contrasenaField = new JPasswordField(20);

        registrarButton = new JButton("Registrar");
        cancelarButton = new JButton("Cancelar");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(apellidosLabel);
        panel.add(apellidosField);
        panel.add(telefonoLabel);
        panel.add(telefonoField);
        panel.add(direccionLabel);
        panel.add(direccionField);
        panel.add(correoLabel);
        panel.add(correoField);
        panel.add(contrasenaLabel);
        panel.add(contrasenaField);
        panel.add(registrarButton);
        panel.add(cancelarButton);

        add(panel, BorderLayout.CENTER);

        // Acción del botón Registrar
        registrarButton.addActionListener(e -> {
            String nombre = nombreField.getText();
            String apellidos = apellidosField.getText();
            String telefono = telefonoField.getText();
            String direccion = direccionField.getText();
            String correo = correoField.getText();
            String contrasena = new String(contrasenaField.getPassword());

            // Llamar al controlador para manejar el registro
            boolean registroExitoso = registroController.registrarUsuario(
                    nombre, apellidos, telefono, direccion, correo, contrasena, usuarioDAO
            );

            // Si el registro es exitoso, cerrar el frame y abrir el login
            if (registroExitoso) {
                JOptionPane.showMessageDialog(this, "Registro exitoso. Inicia sesión.");
                dispose(); // Cerrar el RegistroFrame
                new LoginFrame().setVisible(true); // Abrir el LoginFrame
            }
        });

        // Acción del botón Cancelar
        cancelarButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}
