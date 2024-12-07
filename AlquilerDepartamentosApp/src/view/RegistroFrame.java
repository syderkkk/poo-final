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

        initComponent();

        // Acción del botón Registrar
        registrarButton.addActionListener(e -> {
            String nombres = nombreField.getText();
            String apellidos = apellidosField.getText();
            String telefono = telefonoField.getText();
            String direccion = direccionField.getText();
            String correo = correoField.getText();
            String contrasena = new String(contrasenaField.getPassword());

            // Delegar lógica al controlador
            registroController.registrarUsuario(1,nombres, apellidos, telefono, direccion, correo, contrasena);
        });

        cancelarButton.addActionListener(e -> registroController.cancelarRegistro());
    }

    private void initComponent() {
        setTitle("Registrar cuenta");
        setContentPane(panel);
        setSize(400, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
