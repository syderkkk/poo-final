package view;

import controller.AlquilerController;
import model.Departamento;
import model.Usuario;
import utils.UsuarioSession;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

public class AlquilerDepartFrame extends JFrame {
    private JPanel panel;
    private JTextField estadoField, precioField, ciudadField, habitacionesField, capacidadField, direccionField, descripcionField;
    private JButton cancelarButton, alquilarButton;
    private JSpinner spinner1;

    private Departamento departamento;
    private AlquilerController alquilerController;

    public AlquilerDepartFrame(JFrame parent, Departamento departamento) {
        this.departamento = departamento;
        this.alquilerController = new AlquilerController();

        Usuario usuarioActual = UsuarioSession.getInstance().getUsuarioActual();
        initComponents();

        precioField.setText(String.valueOf(departamento.getPrecioAlquiler()));
        ciudadField.setText(departamento.getCiudad());
        habitacionesField.setText(String.valueOf(departamento.getNumeroHabitaciones()));
        capacidadField.setText(String.valueOf(departamento.getCapacidad()));
        descripcionField.setText(departamento.getDescripcion());
        estadoField.setText(departamento.getEstado());
        direccionField.setText(departamento.getDireccion());


        alquilarButton.addActionListener(e -> {
            int mesesAlquiler = (Integer) spinner1.getValue();
            alquilerController.alquilarDepartamento(departamento, this, parent, mesesAlquiler);
        });
        cancelarButton.addActionListener(e -> {
            alquilerController.regresarAlMainFrame(this, parent);
        });
    }

    private void initComponents() {
        setTitle("Alquilar departamento");
        setContentPane(panel);
        setSize(500, 516);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        SpinnerNumberModel numberModel = new SpinnerNumberModel(0, 0, 12, 1);
        spinner1 = new JSpinner(numberModel);

    }
}
