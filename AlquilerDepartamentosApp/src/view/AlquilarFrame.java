package view;

import model.Departamento;

import javax.swing.*;
import java.awt.*;

public class AlquilarFrame extends JFrame {
    private Departamento departamento;

    public AlquilarFrame(Departamento departamento) {
        this.departamento = departamento;

        setTitle("Alquilar Departamento");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("ID:"));
        add(new JLabel(String.valueOf(departamento.getIdDepartamento())));

        add(new JLabel("Descripción:"));
        add(new JLabel(departamento.getDescripcion()));

        add(new JLabel("Estado:"));
        add(new JLabel(departamento.getEstado()));

        JButton alquilarButton = new JButton("Confirmar Alquiler");
        alquilarButton.addActionListener(e -> confirmarAlquiler());

        add(alquilarButton);
    }

    private void confirmarAlquiler() {
        JOptionPane.showMessageDialog(this, "Departamento alquilado con éxito.");
        dispose();
    }
}
