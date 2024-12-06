package view;

import controller.DepartamentoController;

import javax.swing.*;

public class AgregarDepartamentoFrame extends JFrame {

    private JTextField descripcionField, estadoField, precioField, ciudadField, habitacionesField, capacidadField, direccionField, banosField;
    private JButton agregarButton, cancelarButton;
    public JPanel panel;

    public AgregarDepartamentoFrame(JFrame parent, DepartamentoController controller) {
        initComponents(parent);
        // Acción del botón "Agregar"
        agregarButton.addActionListener(e -> {
            try {
                // Recolectar datos
                String descripcion = descripcionField.getText();
                String estado = estadoField.getText();
                String precio = precioField.getText();
                String ciudad = ciudadField.getText();
                String habitaciones = habitacionesField.getText();
                String capacidad = capacidadField.getText();
                String direccion = direccionField.getText();
                //String banos = banosField.getText();
                System.out.println("dato recolectado");
                // Llamar al controlador
                controller.agregarDepartamento(this, descripcion, "Disponible", precio, ciudad, habitaciones, "1", capacidad, direccion, parent);
            } catch (Exception ex) {
                ex.printStackTrace(); // Ver el detalle del error
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción del botón "Cancelar"
        cancelarButton.addActionListener(e -> {
            dispose();
            parent.setVisible(true); // Vuelve a mostrar la ventana principal
        });
    }

    private void initComponents(JFrame parent) {
        setTitle("Agregar departamentos");
        // Configurar el diseño generado en IntelliJ
        setContentPane(panel); // Usa el panel generado por el formulario
        setSize(500, 516);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // No cerrar toda la app
    }
}