package view;

import controller.RegistroController;

import javax.swing.*;

public class RegistroFrame extends JFrame{

    private JTextField nombreField, apellidosField, telefonoField, direccionField, correoField;
    private JButton registrarButton, cancelarButton;
    private JPasswordField contrasenaField;
    public JPanel panel;

    private RegistroController registroController;

    public RegistroFrame() {
        registroController = new RegistroController(this);

        setContentPane(panel);
        setSize(400, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Acción del botón Registrar
        registrarButton.addActionListener(e -> {
            String nombre = nombreField.getText();
            String apellidos = apellidosField.getText();
            String telefono = telefonoField.getText();
            String direccion = direccionField.getText();
            String correo = correoField.getText();
            String contrasena = new String(contrasenaField.getPassword());

            // Delegar lógica al controlador
            registroController.registrarUsuario(nombre, apellidos, telefono, direccion, correo, contrasena);
        });

        // Acción del botón Cancelar
        cancelarButton.addActionListener(e -> registroController.cancelarRegistro());
    }

}
