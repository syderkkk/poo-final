package controller;

import dao.impl.DepartamentoDAOImpl;
import dao.interfaces.IDepartamentoDAO;
import model.Departamento;
import view.MainFrame;

import javax.swing.*;
import java.sql.SQLException;

public class AlquilerController {

    private IDepartamentoDAO departamentosDAO;

    public AlquilerController() {
        this.departamentosDAO = new DepartamentoDAOImpl();
    }

    // Método para manejar el proceso de alquiler
    public void alquilarDepartamento(Departamento departamento, JFrame currentFrame, JFrame parentFrame) {
        try {
            departamento.setEstado("Alquilado"); // Cambiar estado
            departamentosDAO.actualizar(departamento); // Actualizar en base de datos

            if (parentFrame instanceof MainFrame) {
                ((MainFrame) parentFrame).cargarTabla(); // Actualizar tabla en MainFrame
            }

            mostrarMensaje(currentFrame, "Departamento alquilado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            regresarAlMainFrame(currentFrame, parentFrame);
        } catch (SQLException ex) {
            mostrarMensaje(currentFrame, "Error al alquilar el departamento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para cerrar el frame actual y regresar al principal
    public void regresarAlMainFrame(JFrame currentFrame, JFrame parentFrame) {
        currentFrame.dispose(); // Cerrar el frame actual
        parentFrame.setVisible(true); // Mostrar el frame principal
    }

    // Método para mostrar mensajes
    private void mostrarMensaje(JFrame frame, String mensaje, String titulo, int tipoMensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, titulo, tipoMensaje);
    }
}
