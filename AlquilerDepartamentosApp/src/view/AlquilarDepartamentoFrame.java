package view;

import controller.AlquilerController;
import model.Departamento;

import javax.swing.*;
import java.awt.*;

public class AlquilarDepartamentoFrame extends JFrame {

    private Departamento departamento;
    private JTextField descripcionField;
    private JTextField estadoField;
    private JTextField precioField;
    private JTextField ciudadField;
    private JTextField habitacionesField;
    private JTextField banosField;
    private JTextField capacidadField;
    private JTextField direccionField;
    private JButton alquilarButton;
    private JButton regresarButton;
    private AlquilerController alquilerController;

    public AlquilarDepartamentoFrame(JFrame parent, Departamento departamento) {
        this.departamento = departamento;
        this.alquilerController = new AlquilerController(); // Inicializar el controlador

        setTitle("Detalles del Departamento");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(0, 2));

        // Crear campos de texto para mostrar los datos del departamento
        descripcionField = new JTextField(departamento.getDescripcion());
        estadoField = new JTextField(departamento.getEstado());
        precioField = new JTextField(String.valueOf(departamento.getPrecioAlquiler()));
        ciudadField = new JTextField(departamento.getCiudad());
        habitacionesField = new JTextField(String.valueOf(departamento.getNumeroHabitaciones()));
        banosField = new JTextField(String.valueOf(departamento.getNumeroBanos()));
        capacidadField = new JTextField(String.valueOf(departamento.getCapacidad()));
        direccionField = new JTextField(departamento.getDireccion());

        // Los campos de texto son solo lectura
        setFieldsEditable(false);

        // Etiquetas y campos de texto
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

        // Botones para alquilar o regresar
        alquilarButton = new JButton("Alquilar");
        regresarButton = new JButton("Regresar");

        // Acción al hacer clic en el botón de alquilar
        alquilarButton.addActionListener(e -> alquilerController.alquilarDepartamento(this, departamento, parent));

        // Acción al hacer clic en el botón de regresar
        regresarButton.addActionListener(e -> alquilerController.regresar(this, parent));

        // Añadir los botones al formulario
        add(alquilarButton);
        add(regresarButton);
    }

    // Método para hacer los campos de texto solo lectura
    private void setFieldsEditable(boolean editable) {
        descripcionField.setEditable(editable);
        estadoField.setEditable(editable);
        precioField.setEditable(editable);
        ciudadField.setEditable(editable);
        habitacionesField.setEditable(editable);
        banosField.setEditable(editable);
        capacidadField.setEditable(editable);
        direccionField.setEditable(editable);
    }
}
