package view;

import controller.AlquilerController;
import model.Departamento;

import javax.swing.*;
import java.awt.*;

public class AlquilarDepartamentoFrame extends JFrame {

    private AlquilerController alquilerController;
    private Departamento departamento;

    public AlquilarDepartamentoFrame(JFrame parent, Departamento departamento) {
        this.departamento = departamento;
        this.alquilerController = new AlquilerController();
        configurarVentana(parent);
        crearFormulario(parent);
    }

    private void configurarVentana(JFrame parent) {
        setTitle("Detalles del Departamento");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(0, 2));
    }

    private void crearFormulario(JFrame parent) {
        agregarEtiquetaYCampo("Descripción:", departamento.getDescripcion());
        agregarEtiquetaYCampo("Estado:", departamento.getEstado());
        agregarEtiquetaYCampo("Precio de Alquiler:", String.valueOf(departamento.getPrecioAlquiler()));
        agregarEtiquetaYCampo("Ciudad:", departamento.getCiudad());
        agregarEtiquetaYCampo("Habitaciones:", String.valueOf(departamento.getNumeroHabitaciones()));
        agregarEtiquetaYCampo("Baños:", String.valueOf(departamento.getNumeroBanos()));
        agregarEtiquetaYCampo("Capacidad:", String.valueOf(departamento.getCapacidad()));
        agregarEtiquetaYCampo("Dirección:", departamento.getDireccion());

        JButton alquilarButton = new JButton("Alquilar");
        alquilarButton.addActionListener(e -> alquilerController.alquilarDepartamento(departamento, this, parent));

        JButton regresarButton = new JButton("Regresar");
        regresarButton.addActionListener(e -> alquilerController.regresarAlMainFrame(this, parent));

        add(alquilarButton);
        add(regresarButton);
    }

    private void agregarEtiquetaYCampo(String etiqueta, String valor) {
        add(new JLabel(etiqueta));
        JTextField campo = new JTextField(valor);
        campo.setEditable(false);
        add(campo);
    }
}