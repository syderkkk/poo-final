package view;

import controller.AlquilerController;
import model.Departamento;
import model.Usuario;
import utils.UsuarioSession;

import javax.swing.*;

public class AlquilerDepartFrame extends JFrame {
    private JPanel panel;
    JTextField estadoField, precioField, ciudadField, habitacionesField, capacidadField, direccionField, descripcionField;
    JButton cancelarButton, alquilarButton;

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
        direccionField.setText(departamento.getDireccion());


        alquilarButton.addActionListener(e -> alquilerController.alquilarDepartamento(departamento, this, parent));
        cancelarButton.addActionListener(e -> alquilerController.regresarAlMainFrame(this, parent));
    }

    private void initComponents() {
        setTitle("Alquilar departamento");
        setContentPane(panel);
        setSize(500, 516);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


}
