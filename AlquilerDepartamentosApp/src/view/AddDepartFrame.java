package view;

import controller.DepartamentoController;

import javax.swing.*;
import java.awt.*;

public class AddDepartFrame extends JFrame {

    private final DepartamentoController controller;  // Controlador
    private final JFrame parent;  // Referencia al padre

    public AddDepartFrame(JFrame parent, DepartamentoController controller) {
        this.parent = parent;
        this.controller = controller;

        setTitle("Agregar Departamento");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(0, 2));

        // Crear campos de texto
        JTextField descripcionField = new JTextField();
        JTextField estadoField = new JTextField("Disponible");
        JTextField precioField = new JTextField();
        JTextField ciudadField = new JTextField();
        JTextField habitacionesField = new JTextField();
        JTextField banosField = new JTextField();
        JTextField capacidadField = new JTextField();
        JTextField direccionField = new JTextField();

        // Etiquetas y campos
        add(new JLabel("Descripción:"));
        add(descripcionField);

        add(new JLabel("Estado:"));
        add(estadoField);

        add(new JLabel("Precio de Alquiler:"));
        add(precioField);

        add(new JLabel("Ciudad:"));
        add(ciudadField);

        add(new JLabel("Habitaciones:"));
        add(habitacionesField);

        add(new JLabel("Baños:"));
        add(banosField);

        add(new JLabel("Capacidad:"));
        add(capacidadField);

        add(new JLabel("Dirección:"));
        add(direccionField);

        // Botones
        JButton guardarButton = new JButton("Guardar");
        JButton regresarButton = new JButton("Regresar");

        guardarButton.addActionListener(e -> {
            try {
                // Recolectar datos
                String descripcion = descripcionField.getText();
                String estado = estadoField.getText();
                String precio = precioField.getText();
                String ciudad = ciudadField.getText();
                String habitaciones = habitacionesField.getText();
                String banos = banosField.getText();
                String capacidad = capacidadField.getText();
                String direccion = direccionField.getText();

                // Llamar al controlador
                controller.agregarDepartamento(this, descripcion, estado, precio, ciudad, habitaciones, banos, capacidad, direccion, parent);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        regresarButton.addActionListener(e -> {
            dispose();
            parent.setVisible(true);
        });

        add(guardarButton);
        add(regresarButton);
    }
}
